import java.sql.*;
import static java.lang.System.out;

public class EgInsertNewAddressAndCustomerAndDeleteThem {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String pwd = "12345";

        try{
            Connection conn = DriverManager.getConnection(url, user, pwd);
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);


            String sql = "SELECT address_id, address, district, city_id, phone, location " +
                            "FROM address ORDER BY address_id";
            ResultSet rs = stm.executeQuery(sql);

            rs.last(); //last row
            int lastId = rs.getInt(1); //column 1 is address id
            out.println("Last address id: " + lastId);

            Blob geo = rs.getBlob("location");
            out.println("Last locationL " +  geo);

            /*
            rs.moveToInsertRow();
            rs.updateInt("address_id", lastId + 1);
            rs.updateString("address", "99 Hindhede walk");
            rs.updateString("district", "Zeyene");
            rs.updateInt("city_id", 432);
            rs.updateInt("phone", 88664455);
            rs.updateBlob("location", geo);
            rs.insertRow();

            String sql1 = "SELECT customer_id, store_id, first_name, last_name, address_id " +
                    "FROM customer ORDER BY customer_id";
            ResultSet rs1 = stm.executeQuery(sql1);

            rs1.last(); //last row
            int lastId1 = rs1.getInt(1); //column 1 is customer id
            out.println("Last address id: " + lastId1);

            rs1.moveToInsertRow();
            rs1.updateInt("customer_id", lastId1 + 1);
            rs1.updateInt("store_id", 2);
            rs1.updateString("first_name", "Marianna");
            rs1.updateString("last_name", "Jones");
            rs1.updateInt("address_id", lastId + 1);
            rs1.insertRow();


            //delete row again
            rs.last();
            rs.previous();
            //rs.previous();
            rs.deleteRow();

            //rs.relative(-10);
            */


        }catch (SQLException e) {
            out.println("SQL error code" + e.getErrorCode());
            e.printStackTrace();
        }
    }
}