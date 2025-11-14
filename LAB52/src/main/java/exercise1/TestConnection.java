package exercise1;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Connection successful!");
            } else {
                System.out.println("❌ Connection failed.");
            }
        } catch (SQLException e) {
            System.out.println("❌ SQLException: Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}
