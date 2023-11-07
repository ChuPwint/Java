import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Eg02CreatingStmt {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";  // BookSellerDB user name
                String pwd = "12345";    // BookSellerDB password
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);

                        /* Creating Statement instance from conn */
                        Statement stmt = conn.createStatement();

                        System.out.println("Successfully connected to `sakila`...");
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
