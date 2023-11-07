import java.sql.*;
import static java.lang.System.out;

public class Eg04ProcessingResultSet {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";  // BookSellerDB user name
                String pwd = "12345";    // BookSellerDB password
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        Statement stmt = conn.createStatement();
                        String sqlQuery = "SELECT * FROM customer";
                        ResultSet rs = stmt.executeQuery(sqlQuery);

                        /* Iterating the result set */
                        while (rs.next()) {
                                out.print(rs.getInt("customer_id"));
                                out.print(" ");
                                out.print(rs.getString("first_name"));
                                out.print(" ");
                                out.println(rs.getString("last_name"));
                        }

                }catch (SQLException e) {
                        System.out.println("Cannot connect to `sakila` database...");
                        System.out.println("1. MySQL db server is running...");
                        System.out.println("2. Check your connection string...");
                        System.out.println("3. Check the following error code for details: ");
                        System.out.println(e.getErrorCode());
                        e.printStackTrace();
                }
        }
}
