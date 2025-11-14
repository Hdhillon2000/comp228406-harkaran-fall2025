package exercise1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {
    public int insertGame(String title) {
        String sql = "INSERT INTO game (title) VALUES (?) RETURNING game_id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("game_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Game> getAllGames() {
        List<Game> out = new ArrayList<>();
        String sql = "SELECT game_id, title FROM game ORDER BY title";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(new Game(rs.getInt("game_id"), rs.getString("title")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }
}
