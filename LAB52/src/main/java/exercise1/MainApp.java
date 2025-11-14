package exercise1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @FXML private TextField firstNameField, lastNameField, emailField, countryField, ageField, hoursField, scoreField;
    @FXML private ComboBox<Game> gameCombo;
    @FXML private TableView<String[]> tableView;

    private final PlayerDAO playerDAO = new PlayerDAO();
    private final GameDAO gameDAO = new GameDAO();
    private final PlayedDAO playedDAO = new PlayedDAO();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        loader.setController(this);
        Scene scene = new Scene(loader.load());
        stage.setTitle("COMP228 Lab 5 - JavaFX Supabase");
        stage.setScene(scene);
        stage.show();

        gameCombo.getItems().setAll(gameDAO.getAllGames());
    }

    @FXML
    private void addPlayer() {
        Player p = new Player();
        p.setFirstName(firstNameField.getText());
        p.setLastName(lastNameField.getText());
        p.setEmail(emailField.getText());
        p.setCountry(countryField.getText());
        if (!ageField.getText().isEmpty())
            p.setAge(Integer.parseInt(ageField.getText()));

        int newId = playerDAO.insertPlayer(p);
        showAlert(newId > 0 ? "Player added (ID: " + newId + ")" : "Failed to add player");
    }

    @FXML
    private void addPlayed() {
        var players = playerDAO.getAllPlayers();
        if (players.isEmpty()) { showAlert("Add a player first!"); return; }

        Game g = gameCombo.getValue();
        if (g == null) { showAlert("Select a game."); return; }

        Played pl = new Played();
        pl.setPlayerId(players.get(players.size()-1).getPlayerId());
        pl.setGameId(g.getGameId());
        if (!hoursField.getText().isEmpty())
            pl.setHoursPlayed(Double.parseDouble(hoursField.getText()));
        if (!scoreField.getText().isEmpty())
            pl.setScore(Integer.parseInt(scoreField.getText()));

        boolean ok = playedDAO.insertPlayed(pl);
        showAlert(ok ? "Played record added!" : "Failed to add played record.");
    }

    @FXML
    private void loadReport() {
        tableView.getItems().setAll(FXCollections.observableArrayList(playedDAO.getPlayedReport()));
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
