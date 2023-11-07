import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Eg08CachedOrDisconnectedRowSet {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";
                try {
                        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
                        String query = "SELECT * FROM customer ORDER BY customer_id";
                        String query1 = "SELECT * FROM address ORDER BY address_id";
                        crs.setCommand(query1);
                        crs.setUrl(url);
                        crs.setUsername(user);
                        crs.setPassword(pwd);
                        crs.setPageSize(20);
                        Connection conn = DriverManager.getConnection(url, user, pwd);

                        // *** set the auto commit to false
                        conn.setAutoCommit(false);

                        crs.execute(conn);


                        while (crs.next()) {
                                System.out.println("Address Id: " + crs.getBlob(8));
                        }

                        // nextPage အလုပ်လုပ်ပုံ နားလည်ရုံအတွက် စမ်းကြည့်ဖို့ပါ
                        System.out.println("------------------------------------");
                        crs.beforeFirst();
                        crs.nextPage();
                        crs.next();
                        System.out.println("Id: " + crs.getInt(1));

                        System.out.println("------------------------------------");
                        crs.nextPage();
                        crs.next();
                        System.out.println("Id: " + crs.getInt(1));

                        /*
                        // *** table name is required so that RowSet implementation can generate correct sql
                        crs.setTableName("customer");
                        crs.updateDate("last_update", Date.valueOf("2022-12-12"));
                        crs.updateRow();
                        // sync back to database
                        crs.acceptChanges();
                        */

                } catch (SQLException e) {
                        System.out.println(e.getErrorCode());
                        e.printStackTrace();
                }
        }
}
