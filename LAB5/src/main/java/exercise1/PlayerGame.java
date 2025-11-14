package exercise1;

import java.sql.Date;

public class PlayerGame {
    private int playerGameId;
    private int playerId;
    private int gameId;
    private Date playingDate;
    private Integer score;

    public PlayerGame() {}

    // getters / setters
    public int getPlayerGameId() { return playerGameId; }
    public void setPlayerGameId(int playerGameId) { this.playerGameId = playerGameId; }
    public int getPlayerId() { return playerId; }
    public void setPlayerId(int playerId) { this.playerId = playerId; }
    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }
    public Date getPlayingDate() { return playingDate; }
    public void setPlayingDate(Date playingDate) { this.playingDate = playingDate; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}
