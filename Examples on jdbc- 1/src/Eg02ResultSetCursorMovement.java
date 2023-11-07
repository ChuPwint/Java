import java.sql.*;
import static java.lang.System.out;

public class Eg02ResultSetCursorMovement {
        public static void main(String[] args) {

                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";

                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);


                        // Change the cursor type to TYPE_SCROLL_INSENSITIVE (default is TYPE_FORWARD_ONLY)
                        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);


                        String sql = "SELECT customer_id, store_id, first_name, last_name " +
                                        "FROM customer ORDER BY customer_id";
                        ResultSet rs = stmt.executeQuery(sql);

                        // note that 1st row is 1
                        rs.absolute(10);

                        String[] colNames = {"ID", "STORE_ID", "FIRST_NAME", "LAST_NAME"};
                        int[] colWidths = {5, 8, 10, 10};
                        printRow(rs, colNames, colWidths);

                        rs.previous();
                        printRow(rs, colNames, colWidths);

                        rs.relative(6); // move cursor to 15
                        printRow(rs, colNames, colWidths);

                        rs.relative(-10);
                        printRow(rs, colNames, colWidths);

                        rs.last();
                        printRow(rs, colNames, colWidths);

                        rs.first();
                        printRow(rs, colNames, colWidths);

                        rs.beforeFirst();
                        out.println("Cur row num: " + rs.getRow());

                        rs.afterLast();
                        out.println("Cur row num: " + rs.getRow());

                        rs.previous();
                        out.println("Is last row: " + rs.isLast());



                } catch (SQLException e) {
                        // Proper exception handling should be put here
                        // for now just print error code
                        out.println("SQL error code " + e.getErrorCode());
                        e.printStackTrace();
                }
        }

        // Very basic methods for printing a row with column names
        public static void printRow(ResultSet rs, String[] colNames, int[] colWidths) throws SQLException{
                for(int i = 0; i < colNames.length; i++) {
                        String col = leftJustify(colNames[i], colWidths[i]);
                        out.print(col);
                }
                out.println();
                String colData;
                for(int i = 1; i <= colNames.length; i++) {
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

