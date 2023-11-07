import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Eg05InsDelUptWithExecuteUpdate {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        Statement stmt = conn.createStatement();

                        int affectedRowCnt = 0;
                        /*
                        // customer id ကို အခုလို အသေထည့်မထားပဲ လက်ရှိ အကြီးဆုံးကို အရင်ထုတ်ယူ
                        // အကြီးဆုံးကိုမှ တစ်ပေါင်းပြီး ထည့်တာက ပိုသင့်တော့်မယ်


                         affectedRowCnt = stmt.executeUpdate("INSERT INTO customer " +
                                        " (customer_id, store_id, first_name, last_name, address_id) " +
                                        " VALUES (604, 2, 'James', 'Hetfield', 3);");


                        // မှတ်ချက် -> customer table မှာ record တစ်ကြောင်း ဝင်သွားမယ်။ mysql workbench နဲ့ စစ်ကြည့်လို့ရမယ်။

                        // တစ်ကြောင်းပဲ အပေါ်မှာ insert ထည့်ထားတော့ ၁ ပဲထွက်မယ်
                        System.out.println(affectedRowCnt);
                        */

                        // update customer id 500 and 599
                        affectedRowCnt = stmt.executeUpdate("UPDATE customer SET last_update = now() WHERE customer_id = " +
                                        "500 OR customer_id = 599");
                        System.out.println(affectedRowCnt);

                        // ဖျက်မယ်ဆိုရင်လဲ ဒီလိုလုပ်လို့ရတယ်
                        // affectedRowCnt = stmt.executeUpdate("DELETE FROM customer WHERE customer_id = 602");
                        // System.out.println(affectedRowCnt);



                } catch (SQLException e) {
                        System.out.println("Cannot connect to `sakila` database...");
                        System.out.println("1. MySQL db server is running...");
                        System.out.println("2. Check your connection string...");
                        System.out.println("3. Check the following error code for details: ");
                        System.out.println(e.getErrorCode());
                        e.printStackTrace();
                }
        }
}
