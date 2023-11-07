import java.sql.*;

public class Eg10ReleasingResourcesPreJdk7 {
        public static void main(String[] args) {
                // Before JDK 7
                Connection conn = null;
                String url = "", user = "", pwd = "";
                // setup url, user, pwd
                //...
                try {
                        conn = DriverManager.getConnection(url, user, pwd);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
                        // ... process the results
                        // ...
                        if (rs != null && stmt != null) {
                                rs.close();           // Attempt to close the ResultSet
                                stmt.close();         // Attempt to close the Statement
                        }

                } catch (SQLException e) {
                        // exception handling code

                } finally {
                        try {
                                if (conn != null) {
                                        // conn ကို ပိတ်လိုက်တဲ့အခါ အပေါ်က stmt နဲ့ rs ပါ close ဖြစ်ပါတယ်
                                        conn.close();       // Close the Connection
                                }
                        } catch (SQLException sec) {
                                System.out.println("Exception closing connection!");
                        }
                }
        }
}
