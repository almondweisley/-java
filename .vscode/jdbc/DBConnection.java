import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    static final String URL =  "jdbc:mysql://localhost:3306/inventory_db";
    static final String USER = "root";
    static final String PASSWORD = "Ouma@218.\\";
public static void main(String[] args) {
    Connection conn = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (Exception e) {
    }
   }
}

