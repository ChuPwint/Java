package dvdrental.db;

import dvdrental.services.CustomerCrudService;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DbUtil {
        private static final String URL = "jdbc:mysql://localhost:3306/sakila";
        private static final String USR = "root";
        private static final String PWD = "12345";
        public static CachedRowSet createRowSet() throws SQLException{
                RowSetFactory factory = RowSetProvider.newFactory();
                CachedRowSet rowset = factory.createCachedRowSet();
                setConnInfo(rowset);
                return rowset;
        }

        public static void setConnInfo(RowSet rs) throws SQLException {
                rs.setUrl(URL);
                rs.setUsername(USR);
                rs.setPassword(PWD);
        }

        // params are values in order to set in prepared statement positional ? ? ?
        public static void runUpdateSql(String sql, Object[] params) throws SQLException{
                try (Connection conn = DriverManager.getConnection(URL, USR, PWD);
                        PreparedStatement stmt = conn.prepareStatement(sql)) {
                        for (int i = 0; i < params.length; i++) {
                                stmt.setObject(i+1, params[i]);
                        }
                        int row = stmt.executeUpdate();
                        System.out.println("Updated row: " + row);
                        conn.setAutoCommit(false);
                } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("PreparedStatement error!");
                }
        }

        public static void addNewCustomer(Map<String, Object> params) throws SQLException {
                Map<String, Object> newAddressParams = new LinkedHashMap<>();
                Map<String, Object> newCustomerParams = new LinkedHashMap<>();
                System.out.print("Customer id before adding: " + CustomerCrudService.getCustomerId());
                int cId = CustomerCrudService.getCustomerId() + 1;
                System.out.println("Customer id after adding: " + cId);
                Random rand = new Random();
                int sId = rand.nextInt(1,3);
                int aId = CustomerCrudService.getAddressId() + 1;
                Blob geo = DbUtil.getBlob_From();

                newCustomerParams.put("customer_id", cId);
                newCustomerParams.put("store_id", sId);
                newCustomerParams.put("first_name", params.get("first_name"));
                newCustomerParams.put("last_name", params.get("last_name"));
                newCustomerParams.put("email", params.get("email"));
                newCustomerParams.put("address_id", aId);

                newAddressParams.put("address_id", aId);
                newAddressParams.put("address", params.get("address"));
                newAddressParams.put("address2", params.get("address_2"));
                newAddressParams.put("district", params.get("district"));
                newAddressParams.put("city_id", params.get("city_id"));
                newAddressParams.put("phone", params.get("phone"));
                newAddressParams.put("location", geo);

                String sql = "INSERT INTO address (address_id, address, address2, district, city_id, phone, location)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?)";
                String sql2 = "INSERT INTO customer (customer_id, store_id, first_name, last_name, email, address_id)" +
                        " VALUES (?, ?, ?, ?, ?, ?)";

                Object[] b;
                b = newAddressParams.values().toArray();
                runUpdateSql(sql, b);
                b = newCustomerParams.values().toArray();
                runUpdateSql(sql2, b);
        }

        public static Blob getBlob_From() throws SQLException {
                Connection conn = DriverManager.getConnection(URL, USR, PWD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                ResultSet bRs = stmt.executeQuery("SELECT location FROM address");
                bRs.last();
                Blob geo = bRs.getBlob(1);
                return geo;
        }
}
