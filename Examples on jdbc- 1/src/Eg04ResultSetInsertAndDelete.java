import java.sql.*;
import static java.lang.System.out;

public class Eg04ResultSetInsertAndDelete {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);

                        String sql = "SELECT customer_id, store_id, first_name, last_name, address_id " +
                                        "FROM customer ORDER BY customer_id";
                        ResultSet rs = stmt.executeQuery(sql);
                        rs.last(); //last row

                        int lastId = rs.getInt(1); //column 1 is customer id
                        out.println("Last customer id: " + lastId);

                        rs.moveToInsertRow();
                        rs.updateInt("customer_id", lastId + 1);
                        rs.updateInt("store_id", 2);
                        rs.updateString("first_name", "Michael");
                        rs.updateString("last_name", "Jackson");
                        rs.updateInt("address_id", 5);
                        rs.insertRow();

                        /*
                        //delete row again
                        rs.last();
                        rs.previous();
                        //rs.previous();
                        rs.deleteRow();

                        //rs.relative(-10);



                        /*

                        // If rows has been deleted, you may want to skip processing
                        DatabaseMetaData dbmd = conn.getMetaData();
                        // Check if JDBC driver support deleted rows from result set
                        if (dbmd.deletesAreDetected(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
                                out.println("Driver can detect deleted rows from result set...");
                                while (rs.next()) {
                                        if (rs.rowDeleted()) continue;
                                        else out.println(rs.getInt(1));
                                }
                        } else {
                                // just print last 10 rows
                                out.println("Driver cannot detect deleted rows from result set...");
                                rs = stmt.executeQuery(sql);
                                rs.last();
                                rs.relative(-10);

                                while (rs.next()) {
                                        out.println(rs.getInt(1));
                                }
                        }
                        */

                } catch (SQLException e) {
                        // Proper exception handling should be put here
                        // for now just print error code
                        out.println("SQL error code" + e.getErrorCode());
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

