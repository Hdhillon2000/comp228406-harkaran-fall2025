package exercise1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    public int insertPlayer(Player p) {
        String sql = "INSERT INTO player (first_name, last_name, email, country, age) VALUES (?, ?, ?, ?, ?) RETURNING player_id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getCountry());
            if (p.getAge() != null) ps.setInt(5, p.getAge()); else ps.setNull(5, Types.INTEGER);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("player_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updatePlayer(Player p) {
        String sql = "UPDATE player SET first_name = ?, last_name = ?, email = ?, country = ?, age = ? WHERE player_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getCountry());
            if (p.getAge() != null) ps.setInt(5, p.getAge()); else ps.setNull(5, Types.INTEGER);
            ps.setInt(6, p.getPlayerId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Player> getAllPlayers() {
        List<Player> out = new ArrayList<>();
        String sql = "SELECT player_id, first_name, last_name, email, country, age FROM player ORDER BY player_id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Player p = new Player(
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("country"),
                        rs.getObject("age") == null ? null : rs.getInt("age")
                );
                out.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    public Player getPlayerById(int id) {
        String sql = "SELECT player_id, first_name, last_name, email, country, age FROM player WHERE player_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Player(
                            rs.getInt("player_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("country"),
                            rs.getObject("age") == null ? null : rs.getInt("age")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
