import java.sql.*;
import javax.sql.rowset.*;
import javax.sql.rowset.spi.*;
import java.io.*;
 
/**
 * ဒီ website ကနေ အခြားတစ်ယောက်ရဲ့ ဥပမာကိုပဲ ကူးထည့်ပေးထားတာပါ
 * RowSet ဥပမာ သိမ်များများစားစား မတွေရဘူး၊ online မှာ
 *
 * This program demonstrates how to use CachedRowSet in JDBC.
 *
 * @author www.codejava.net
 * https://www.codejava.net/java-se/jdbc/how-to-use-cachedrowset-in-jdbc
 */
public class Eg09CachedRowSetExample {
    static Console console = System.console();
    static String answer;
    static boolean quit = false;
 
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college";
        String username = "root";
        String password = "12345";
 
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            conn.setAutoCommit(false);
 
            String sql = "SELECT * FROM student";
 
            Statement statement = conn.createStatement();
 
            ResultSet result = statement.executeQuery(sql);
 
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet();
 
            rowset.setTableName("student");
 
            rowset.populate(result);
 
            while (!quit) {
                if (!readStudent(rowset)) continue;
 
                updateStudent(rowset);
 
                deleteStudent(rowset);
 
                insertStudent(rowset);
 
                saveChanges(rowset, conn);
 
                askToQuit();
 
            }
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
 
    }
 
    static void readStudentInfo(String position, ResultSet result)
            throws SQLException {
        String name = result.getString("name");
        String email = result.getString("email");
        String major = result.getString("major");
 
        String studentInfo = "%s: %s - %s - %s\n";
        System.out.format(studentInfo, position, name, email, major);
    }
 
    static boolean readStudent(ResultSet result) throws SQLException {
        int row = Integer.parseInt(console.readLine("Enter student number: "));
 
        if (result.absolute(row)) {
            readStudentInfo("Student at row " + row + ": ", result);
            return true;
        } else {
            System.out.println("There's no student at row " + row);
            return false;
        }
    }
 
    static void updateStudent(ResultSet result) throws SQLException {
        answer = console.readLine("Do you want to update this student (Y/N)?: ");
 
        if (answer.equalsIgnoreCase("Y")) {
            String name = console.readLine("\tUpdate name: ");
            String email = console.readLine("\tUpdate email: ");
            String major = console.readLine("\tUpdate major: ");
 
            if (!name.equals("")) result.updateString("name", name);
            if (!email.equals("")) result.updateString("email", email);
            if (!major.equals("")) result.updateString("major", major);
 
            result.updateRow();
 
            System.out.println("The student has been updated.");
        }
 
    }
 
    static void deleteStudent(ResultSet result) throws SQLException {
        answer = console.readLine("Do you want to delete this student (Y/N)?: ");
 
        if (answer.equalsIgnoreCase("Y")) {
            result.deleteRow();
 
            System.out.println("The student has been removed.");
        }
 
    }
 
    static void insertStudent(ResultSet result) throws SQLException {
        answer = console.readLine("Do you want to insert a new student (Y/N)?: ");
 
        if (answer.equalsIgnoreCase("Y")) {
            String name = console.readLine("\tEnter name: ");
            String email = console.readLine("\tEnter email: ");
            String major = console.readLine("\tEnter major: ");
 
            result.moveToInsertRow();
 
            result.updateNull("student_id");
            result.updateString("name", name);
            result.updateString("email", email);
            result.updateString("major", major);
 
            result.insertRow();
            result.moveToCurrentRow();
 
            System.out.println("The student has been added.");
        }
 
    }
 
    static void saveChanges(CachedRowSet rowset, Connection conn) {
        answer = console.readLine("Do you want to save changes (Y/N)?: ");
 
        if (answer.equalsIgnoreCase("Y")) {
            try {
                rowset.acceptChanges(conn);
            } catch (SyncProviderException ex) {
                System.out.println("Error commiting changes to the database: " + ex);
            }
        }
    }
 
    static void askToQuit() {
        answer = console.readLine("Do you want to quit (Y/N)?: ");
        quit = answer.equalsIgnoreCase("Y");
    }
}