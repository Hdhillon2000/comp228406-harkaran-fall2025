package exercise1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:postgresql://aws-1-ca-central-1.pooler.supabase.com:5432/postgres";
    private static final String USER = "postgres.kjzvogxdspsqpmrbvdfj";
    private static final String PASSWORD = "Harkaran1";

    static {
        try {
            Class.forName("org.postgresql.Driver"); // optional in modern JDBC
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
