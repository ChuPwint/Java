import java.sql.*;

public class Eg03ExecutingSql {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";  // BookSellerDB user name
                String pwd = "12345";    // BookSellerDB password
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        Statement stmt = conn.createStatement();
                        String sqlQuery = "SELECT * FROM customer";

                        /* Executing Sql */
                        ResultSet rs = stmt.executeQuery(sqlQuery);

                }catch (SQLException e) {
                        // Exception handling code here
                        System.out.println(e.getErrorCode());
                        e.printStackTrace();
                }
        }
}
