package exercise1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayedDAO {

    public boolean insertPlayed(Played pl) {
        String sql = "INSERT INTO played(player_id,game_id,hours_played,score) VALUES(?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pl.getPlayerId());
            stmt.setInt(2, pl.getGameId());
            stmt.setDouble(3, pl.getHoursPlayed());
            stmt.setInt(4, pl.getScore());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String[]> getPlayedReport() {
        List<String[]> report = new ArrayList<>();
        String sql = "SELECT p.player_id, p.first_name || ' ' || p.last_name AS player_name, g.title, pl.hours_played, pl.score " +
                "FROM played pl " +
                "JOIN player p ON pl.player_id = p.player_id " +
                "JOIN game g ON pl.game_id = g.game_id " +
                "ORDER BY pl.played_id";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                report.add(new String[]{
                        String.valueOf(rs.getInt("player_id")),
                        rs.getString("player_name"),
                        rs.getString("title"),
                        String.valueOf(rs.getDouble("hours_played")),
                        String.valueOf(rs.getInt("score"))
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
}
