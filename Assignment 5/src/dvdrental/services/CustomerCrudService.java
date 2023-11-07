package dvdrental.services;

import dvdrental.db.DbUtil;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class CustomerCrudService {
        /*public static final int PAGE_SZ = 10;
        public static void addNewCustomer(Map<String, String> params) throws SQLException {

                String sql = "INSERT INTO customer (address_id, address, district, city_id, location)" +
                        " VALUES (?, ?, ?, ?, ?)";
                CachedRowSet rs = DbUtil.createRowSet();
                rs.setCommand(sql);
                rs.moveToInsertRow();
                rs.updateInt(1, lastAddressId + 1);
                rs.updateString(2, params.get("address"));
                rs.updateString(3, params.get("district"));
                int city_id = Integer.parseInt(params.get("city_id"));
                rs.updateInt(4, city_id);
                rs.updateBlob(5, geo);
                rs.insertRow();
                rs.acceptChanges();

                String sql3 = "SELECT * FROM customer";
                CachedRowSet rs = DbUtil.createRowSet();
                rs.setCommand(sql3);
                rs.moveToInsertRow();
                rs.updateInt("customer_id", lastCId);
                rs.updateInt("store_id", 2);
                rs.updateString("first_name", params.get("first_name"));
                rs.updateString("last_name", params.get("last_name"));
                rs.updateString("email", params.get("email"));
                rs.updateInt("address_id", 600);
                rs.insertRow();
                rs.acceptChanges();
        }*/

        public static int getCustomerId() throws SQLException {
                CachedRowSet rs = DbUtil.createRowSet();
                rs.setCommand("SELECT customer_id FROM customer ORDER BY customer_id");
                rs.execute();
                int cId = 0;
                while(rs.next()){
                        cId = rs.getInt(1);
                }
                return cId;
        }

        public static int getAddressId() throws SQLException {
                CachedRowSet rs = DbUtil.createRowSet();
                rs.setCommand("SELECT address_id FROM address ORDER BY address_id");
                rs.execute();
                int aId = 0;
                while(rs.next()){
                        aId = rs.getInt(1);
                }
                return aId;
        }

        public static boolean getEmail(String e) throws SQLException {
                CachedRowSet rs = DbUtil.createRowSet();
                rs.setCommand("SELECT email FROM customer ORDER BY customer_id");
                rs.execute();
                boolean sameEmail = false;
                while(rs.next()){
                        String existingEmail = rs.getString("email");
                        if(existingEmail == e) {
                                sameEmail = true;
                        }
                }
                return sameEmail;
        }

        public static CachedRowSet getCountries() throws SQLException{
                CachedRowSet rs = DbUtil.createRowSet();
                rs.setCommand("SELECT country_id, country FROM country");
                rs.execute();
                return rs;
        }

        public static CachedRowSet getCities() throws SQLException{
                CachedRowSet rs = DbUtil.createRowSet();
                rs.setCommand("SELECT city_id, city, country_id FROM city");
                rs.execute();
                return rs;
        }

}
