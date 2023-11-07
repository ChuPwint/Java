import java.sql.*;

public class Eg10ReleasingResourcesJdk7AndAbv {
        public static void main(String[] args) {

                String url = "", user = "", pwd = "";
                // setup url, user, pwd
                //...
                // Jdk 7 နဲ့ အထက်မှာ try with resources ကို အခုလိုရေးလို့ရတယ်
                // အလိုအလျောက်ပိတ်ပေးတယ် conn နဲ့ သူကတဆင့်ရလာတဲ့ resources တွေကို
                try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
                        // ... process the results
                        // ...

                        if (rs != null && stmt != null) {
                                rs.close();       // Attempt to close the ResultSet
                                stmt.close();     // Attempt to close the Statement
                        }

                } catch (SQLException e) {
                        // exception handling code

                }
        }
}
