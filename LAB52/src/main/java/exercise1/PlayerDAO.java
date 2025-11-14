package exercise1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    public int insertPlayer(Player p) {
        String sql = "INSERT INTO player(first_name,last_name,email,country,age) VALUES(?,?,?,?,?) RETURNING player_id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getFirstName());
            stmt.setString(2, p.getLastName());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getCountry());
            stmt.setInt(5, p.getAge());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Player> getAllPlayers() {
        List<Player> list = new ArrayList<>();
        String sql = "SELECT * FROM player ORDER BY player_id";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Player p = new Player();
                p.setPlayerId(rs.getInt("player_id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setCountry(rs.getString("country"));
                p.setAge(rs.getInt("age"));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
