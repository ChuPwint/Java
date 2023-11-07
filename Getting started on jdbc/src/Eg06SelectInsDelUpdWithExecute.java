import java.sql.*;
import java.util.Scanner;

public class Eg06SelectInsDelUpdWithExecute {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        Statement stmt = conn.createStatement();

                        int affectedRowCnt = 0;
                        boolean isResultSet = false;
                        ResultSet rs = null;

                        // run မဲ့ sql က ဘာလဲ ကြိုမသိဘူးဆိုတာကို ပေါ်လွင်အောင် ဥပမာ အနေနဲ့ပဲပြတာပါ
                        String sql = "";
                        System.out.println("Enter 1 or 2");
                        Scanner sc = new Scanner(System.in);
                        int choice = sc.nextInt();

                        if (choice == 1) {
                                sql = "SELECT * FROM customer WHERE customer_id > 500";
                        } else {
                                sql = "UPDATE customer SET last_update = now() WHERE customer_id = 500 OR customer_id = " +
                                                "599 OR customer_id = 400";
                        }
                        isResultSet = stmt.execute(sql);
                        // SELECT လား၊ INSERT, UPDATE, DELETE လား မသိဘူးဆိုရင် အခုလို စစ်ပြီးမှ
                        // getResultSet() နဲ့ ယူရမယ်
                        if (isResultSet) {
                                rs = stmt.getResultSet();
                                while (rs.next()) {
                                        System.out.println(rs.getInt("customer_id"));
                                }

                        } else {
                                // ResultSet မရစေတဲ့ INSERT, UPDATE, DELETE ဆိုရင် ဒီအပိုင်းက အလုပ်လုပ်မယ်
                                // inserted, updated or deleted row count ကို ထုတ်ကြည့်တာ
                                affectedRowCnt = stmt.getUpdateCount();
                                System.out.println("Affected Row Cnt: " + affectedRowCnt);
                        }
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
