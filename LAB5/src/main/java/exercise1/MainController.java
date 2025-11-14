package exercise1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class MainController {

    @FXML private TextField tfFirstName, tfLastName, tfEmail, tfCountry, tfAge;
    @FXML private TextField tfNewGameTitle;
    @FXML private ComboBox<Player> cbPlayers;
    @FXML private ComboBox<Game> cbGames;
    @FXML private DatePicker dpPlayingDate;
    @FXML private TextField tfScore;

    @FXML private TableView<ReportRow> tableReport;
    @FXML private TableColumn<ReportRow, Integer> colPlayerId;
    @FXML private TableColumn<ReportRow, String> colPlayerName;
    @FXML private TableColumn<ReportRow, String> colGame;
    @FXML private TableColumn<ReportRow, Date> colDate;
    @FXML private TableColumn<ReportRow, Integer> colScore;

    private final PlayerDAO playerDAO = new PlayerDAO();
    private final GameDAO gameDAO = new GameDAO();
    private final PlayerGameDAO pgDAO = new PlayerGameDAO();

    @FXML
    public void initialize() {

        // LOAD combo boxes
        refreshPlayers();
        refreshGames();

        // ==== TABLE COLUMNS FIXED ====

        colPlayerId.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleIntegerProperty(
                        cell.getValue().getPlayerId()
                ).asObject()
        );

        colPlayerName.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getPlayerName()
                )
        );

        colGame.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getGameTitle()
                )
        );

        colDate.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        cell.getValue().getPlayingDate()
                )
        );

        colScore.setCellValueFactory(cell -> {
            Integer score = cell.getValue().getScore();
            return new javafx.beans.property.SimpleIntegerProperty(
                    score == null ? 0 : score
            ).asObject();
        });
    }


    private void refreshPlayers() {
        List<Player> players = playerDAO.getAllPlayers();
        cbPlayers.setItems(FXCollections.observableArrayList(players));
    }

    private void refreshGames() {
        List<Game> games = gameDAO.getAllGames();
        cbGames.setItems(FXCollections.observableArrayList(games));
    }



    @FXML
    private void handleAddPlayer() {
        Player p = new Player();
        p.setFirstName(tfFirstName.getText().trim());
        p.setLastName(tfLastName.getText().trim());
        p.setEmail(tfEmail.getText().trim());
        p.setCountry(tfCountry.getText().trim());

        try {
            String a = tfAge.getText().trim();
            p.setAge(a.isEmpty() ? null : Integer.parseInt(a));
        } catch (NumberFormatException e) {
            showAlert("Age must be an integer.");
            return;
        }

        int id = playerDAO.insertPlayer(p);
        if (id > 0) {
            showAlert("Player inserted with ID: " + id);
            refreshPlayers();
            clearPlayerForm();
        } else {
            showAlert("Failed to insert player.");
        }
    }

    @FXML
    private void handleUpdatePlayer() {
        Player selected = cbPlayers.getValue();
        if (selected == null) {
            showAlert("Select a player first.");
            return;
        }

        selected.setFirstName(tfFirstName.getText().trim());
        selected.setLastName(tfLastName.getText().trim());
        selected.setEmail(tfEmail.getText().trim());
        selected.setCountry(tfCountry.getText().trim());

        try {
            String a = tfAge.getText().trim();
            selected.setAge(a.isEmpty() ? null : Integer.parseInt(a));
        } catch (NumberFormatException e) {
            showAlert("Age must be an integer.");
            return;
        }

        boolean ok = playerDAO.updatePlayer(selected);
        showAlert(ok ? "Player updated." : "Update failed.");
        refreshPlayers();
    }

    @FXML
    private void handleSelectPlayerCombo() {
        Player p = cbPlayers.getValue();
        if (p != null) {
            tfFirstName.setText(p.getFirstName());
            tfLastName.setText(p.getLastName());
            tfEmail.setText(p.getEmail());
            tfCountry.setText(p.getCountry());
            tfAge.setText(p.getAge() == null ? "" : p.getAge().toString());
        }
    }

    private void clearPlayerForm() {
        tfFirstName.clear();
        tfLastName.clear();
        tfEmail.clear();
        tfCountry.clear();
        tfAge.clear();
    }



    @FXML
    private void handleAddGame() {
        String title = tfNewGameTitle.getText().trim();
        if (title.isEmpty()) {
            showAlert("Enter a game title.");
            return;
        }

        int id = gameDAO.insertGame(title);
        if (id > 0) {
            showAlert("Game added with ID: " + id);
            tfNewGameTitle.clear();
            refreshGames();
        } else {
            showAlert("Failed to add game.");
        }
    }


    @FXML
    private void handleAddPlayed() {
        Player player = cbPlayers.getValue();
        Game game = cbGames.getValue();
        if (player == null) {
            showAlert("Select a player.");
            return;
        }
        if (game == null) {
            showAlert("Select a game.");
            return;
        }

        PlayerGame pg = new PlayerGame();
        pg.setPlayerId(player.getPlayerId());
        pg.setGameId(game.getGameId());

        LocalDate local = dpPlayingDate.getValue();
        pg.setPlayingDate(local == null ? new Date(System.currentTimeMillis()) : Date.valueOf(local));

        try {
            String s = tfScore.getText().trim();
            pg.setScore(s.isEmpty() ? null : Integer.parseInt(s));
        } catch (NumberFormatException e) {
            showAlert("Score must be integer.");
            return;
        }

        boolean ok = pgDAO.insertPlayerGame(pg);
        showAlert(ok ? "Played record added." : "Failed to add played record.");
    }

    @FXML
    private void handleLoadReport() {
        List<ReportRow> rows = pgDAO.getReport();
        tableReport.setItems(FXCollections.observableArrayList(rows));
    }

    private void showAlert(String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        a.showAndWait();
    }
}
