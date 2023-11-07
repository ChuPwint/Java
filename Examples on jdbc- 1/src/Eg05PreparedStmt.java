import java.sql.*;
import static java.lang.System.out;

public class Eg05PreparedStmt {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "12345";
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        String sqlFNameUpt = "UPDATE customer SET first_name = ? WHERE customer_id = ?";
                        PreparedStatement stmtFNameUpdt = conn.prepareStatement(sqlFNameUpt);

                        // assume that the value is read from user input
                        int cust_id = 10;
                        String first_name = "DOROTHY_1";

                        stmtFNameUpdt.setString(1, first_name);
                        stmtFNameUpdt.setInt(2, cust_id);
                        stmtFNameUpdt.executeUpdate();

                        stmtFNameUpdt.close();

                        String sqlSelByCustId = "SELECT customer_id, store_id, first_name, last_name " +
                                                        "FROM customer WHERE customer_id = ?";
                        PreparedStatement stmtSelByCustId = conn.prepareStatement(sqlSelByCustId);
                        stmtSelByCustId.setInt(1, cust_id);
                        ResultSet rs = stmtSelByCustId.executeQuery();
                        String[] colNames = {"ID", "STORE_ID", "FIRST_NAME", "LAST_NAME"};
                        int[] colWidths = {5, 8, 10, 10};
                        rs.next();
                        printRow(rs, colNames, colWidths);

                }catch (SQLException e) {
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
