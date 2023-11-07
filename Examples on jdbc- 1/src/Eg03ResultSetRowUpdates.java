import java.sql.*;
import static java.lang.System.out;

public class Eg03ResultSetRowUpdates {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);

                        // *** CONCUR_UPDATABLE ထားရင် ResultSet ကို update လုပ်လို့ရပြီး
                        // database နဲ့ ပြန် sync လုပ်လို့ရပါတယ်
                        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);


                        String sql = "SELECT customer_id, store_id, first_name, last_name" +
                                        " FROM customer ORDER BY customer_id";
                        System.out.println(sql);
                        ResultSet rs = stmt.executeQuery(sql);

                        // *** set cursor at 10th row
                        rs.absolute(10);

                        String[] colNames = {"ID", "STORE_ID", "FIRST_NAME", "LAST_NAME"};
                        int[] colWidths = {5, 8, 10, 10};
                        printRow(rs, colNames, colWidths);

                        // update the row by executing UPDATE sql
                        stmt.executeUpdate("UPDATE customer SET last_name = 'TAYLOR' WHERE customer_id = 10");

                        /*
                        // Without using UPDATE sql, update the ResultSet
                        rs.updateString(4, "TAYLOR_123");
                        // updateRow() ခေါ်လိုက်တော့မှာ database ကို သွားပြီး affect ဖြစ်မှာပါ
                        rs.updateRow();
                        printRow(rs, colNames, colWidths);

                        /*---*/

                        //
                        int type = ResultSet.TYPE_SCROLL_INSENSITIVE;
                        DatabaseMetaData dbmd = conn.getMetaData();
                        if (dbmd.updatesAreDetected(type)) {
                                if (rs.rowUpdated()) {             // Has this row (current row at cursor position) been modified?
                                        out.println("Row: " + rs.getRow() + " updated.");
                                }
                        } else{
                                out.println("Row updates on ResultSet are not detected.");
                        }


                } catch (SQLException e) {
                        // Proper exception handling should be put here
                        // for now just print error code
                        out.println("SQL error code" + e.getErrorCode());
                        e.printStackTrace();
                }
        }

        // Very basic methods for printing a row with column names
        public static void printRow(ResultSet rs, String[] colNames, int[] colWidths) throws SQLException {
                for (int i = 0; i < colNames.length; i++) {
                        String col = leftJustify(colNames[i], colWidths[i]);
                        out.print(col);
                }
                out.println();
                String colData;
                for (int i = 1; i <= colNames.length; i++) {
                        if (rs.getObject(i) != null) {
                                colData = rs.getObject(i).toString(); // Get the data in the
                                // column as a String
                        } else {
                                colData = "NULL";
                        }
                        String fmtStr = leftJustify(colData, colWidths[i - 1]);
                        out.print(fmtStr);
                }
                out.println();
        }

        public static String leftJustify(String s, int n) {
                if (s.length() <= n) n++;  // Add an extra space if the length of
                // the String s is less than or equal to
                // the length of the column n
                return String.format("%1$-" + n + "s", s);  // Pad to the right of
                // the String by n
                // spaces
        }
}

