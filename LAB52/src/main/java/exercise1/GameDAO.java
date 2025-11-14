package exercise1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {

    public List<Game> getAllGames() {
        List<Game> list = new ArrayList<>();
        String sql = "SELECT * FROM game ORDER BY game_id";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Game g = new Game();
                g.setGameId(rs.getInt("game_id"));
                g.setTitle(rs.getString("title"));
                list.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
