import java.sql.*;

import static java.lang.System.out;

public class Eg06CallableStmt {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "asdfgh";
                try {
                        Connection conn = DriverManager.getConnection(url, user, pwd);
                        // see https://dev.mysql.com/doc/sakila/en/sakila-structure-procedures-film_in_stock.html
                        // example stored proc and try it out in mysql workbench first
                        String prcCall = "{call film_in_stock(?, ?, ?)}";
                        CallableStatement stmt = conn.prepareCall(prcCall);

                        int filmId = 3;
                        int storedId = 2;
                        stmt.setInt(1, filmId);
                        stmt.setInt(2, storedId);

                        stmt.registerOutParameter(3, Types.NUMERIC);
                        boolean isRs = stmt.execute();

                        // the stored proc set the film count in the third param
                        out.println("Count: " + stmt.getInt(3));

                        // process the result set
                        if(isRs) {
                                ResultSet rs = stmt.getResultSet();
                                while(rs.next()) {
                                        out.println("Inventory: " + rs.getInt(1));
                                }
                        }



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
