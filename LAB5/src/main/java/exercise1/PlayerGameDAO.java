package exercise1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerGameDAO {

    public boolean insertPlayerGame(PlayerGame pg) {
        String sql = "INSERT INTO player_game (player_id, game_id, playing_date, score) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pg.getPlayerId());
            ps.setInt(2, pg.getGameId());
            if (pg.getPlayingDate() != null) ps.setDate(3, pg.getPlayingDate()); else ps.setDate(3, new Date(System.currentTimeMillis()));
            if (pg.getScore() != null) ps.setInt(4, pg.getScore()); else ps.setNull(4, Types.INTEGER);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Report rows: player name, game title, playing date, score, player_id */
    public List<ReportRow> getReport() {
        List<ReportRow> out = new ArrayList<>();
        String sql = """
                SELECT p.player_id, p.first_name, p.last_name, g.title, pg.playing_date, pg.score
                FROM player_game pg
                JOIN player p ON pg.player_id = p.player_id
                JOIN game g ON pg.game_id = g.game_id
                ORDER BY p.player_id, pg.playing_date
                """;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ReportRow r = new ReportRow(
                        rs.getInt("player_id"),
                        rs.getString("first_name") + " " + rs.getString("last_name"),
                        rs.getString("title"),
                        rs.getDate("playing_date"),
                        rs.getObject("score") == null ? null : rs.getInt("score")
                );
                out.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }
}
