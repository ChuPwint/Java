import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class Eg07ConnectedRowSet {
        public static void main(String[] args) {
                String url = "jdbc:mysql://localhost:3306/sakila";
                String user = "root";
                String pwd = "asdfgh";
                try {
                        JdbcRowSet jrs = RowSetProvider.newFactory().createJdbcRowSet();
                        String query = "SELECT * FROM customer";
                        jrs.setCommand(query);
                        jrs.setUrl(url);
                        jrs.setUsername(user);
                        jrs.setPassword(pwd);
                        jrs.execute();

                        // experiment code

                        while (jrs.next()) {
                                System.out.println("Id: " + jrs.getInt(1));
                        }

                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
}
