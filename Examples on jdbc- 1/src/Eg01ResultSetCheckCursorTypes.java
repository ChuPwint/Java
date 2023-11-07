import java.sql.*;
import static java.lang.System.out;

public class Eg01ResultSetCheckCursorTypes {
        /*
        Database Driver တွေက concurrency နဲ့ cursor type support လုပ်တဲ့
        feature တွေ မတူကြပါဘူး

        JDBC standard အရ driver ရေးတဲ့သူတွေအနေနဲ့ တစ်ချို့ standard မှာပါတဲ့ optional
        feature တွေကို implement မလုပ်ပေးပဲ ချန်ထားနိုင်ပါတယ်
         */
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        DatabaseMetaData dbmd = conn.getMetaData();
                        if (dbmd.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
                                out.print("Supports TYPE_FORWARD_ONLY");
                                if (dbmd.supportsResultSetConcurrency(
                                                ResultSet.TYPE_FORWARD_ONLY,
                                                ResultSet.CONCUR_UPDATABLE)) {
                                        out.println(" and supports CONCUR_UPDATABLE");
                                }
                        }
                        if (dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
                                out.print("Supports TYPE_SCROLL_INSENSITIVE");
                                if (dbmd.supportsResultSetConcurrency(
                                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_UPDATABLE)) {
                                        out.println(" and supports CONCUR_UPDATABLE");
                                }
                        }
                        if (dbmd.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
                                out.print("Supports TYPE_SCROLL_SENSITIVE");
                                if (dbmd.supportsResultSetConcurrency(
                                                ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_UPDATABLE)) {
                                        out.println("Supports CONCUR_UPDATABLE");
                                }
                        }

                } catch (SQLException e) {
                        out.println("SQL error code" + e.getErrorCode());
                }
        }
}
