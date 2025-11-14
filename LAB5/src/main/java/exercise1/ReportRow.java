package exercise1;

import java.sql.Date;

public class ReportRow {
    private final int playerId;
    private final String playerName;
    private final String gameTitle;
    private final Date playingDate;
    private final Integer score;

    public ReportRow(int playerId, String playerName, String gameTitle, Date playingDate, Integer score) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.gameTitle = gameTitle;
        this.playingDate = playingDate;
        this.score = score;
    }

    public int getPlayerId() { return playerId; }
    public String getPlayerName() { return playerName; }
    public String getGameTitle() { return gameTitle; }
    public Date getPlayingDate() { return playingDate; }
    public Integer getScore() { return score; }
}
