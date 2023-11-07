import java.sql.*;
import static java.lang.System.out;

public class Eg07UsingResultSetMetaData {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";  // BookSellerDB user name
                String pwd = "12345";    // BookSellerDB password
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        Statement stmt = conn.createStatement();
                        System.out.println("Successfully connected to `sakila`...");
                        String sqlQuery = "SELECT * FROM customer";
                        ResultSet rs = stmt.executeQuery(sqlQuery);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int cols = rsmd.getColumnCount();
                        String col, colData;

                        for (int i = 1; i <= cols; i++) {
                                col = leftJustify(rsmd.getColumnName(i), rsmd.getColumnDisplaySize(i));
                                out.print(col);
                        }
                        out.println(); // Print a linefeed
                        while (rs.next()) {
                                for (int i = 1; i <= cols; i++) {
                                        if (rs.getObject(i) != null) {
                                                colData = rs.getObject(i).toString(); // Get the data in the
                                                // column as a String
                                        } else {
                                                colData = "NULL";
                                        }
                                        col = leftJustify(colData, rsmd.getColumnDisplaySize(i));
                                        out.print(col);
                                }
                                out.println();
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

        public static String leftJustify(String s, int n) {
                if (s.length() <= n) n++;  // Add an extra space if the length of
                // the String s is less than or equal to
                // the length of the column n
                return String.format("%1$-" + n + "s", s);  // Pad to the right of
                // the String by n
                // spaces
        }
}
