package exercise1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // GridPane for form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Labels and text fields
        TextField tfName = new TextField();
        TextField tfAddress = new TextField();
        TextField tfCity = new TextField();
        TextField tfProvince = new TextField();
        TextField tfPostal = new TextField();
        TextField tfPhone = new TextField();
        TextField tfEmail = new TextField();

        grid.addRow(0, new Label("Full Name:"), tfName);
        grid.addRow(1, new Label("Address:"), tfAddress);
        grid.addRow(2, new Label("City:"), tfCity);
        grid.addRow(3, new Label("Province:"), tfProvince);
        grid.addRow(4, new Label("Postal Code:"), tfPostal);
        grid.addRow(5, new Label("Phone:"), tfPhone);
        grid.addRow(6, new Label("Email:"), tfEmail);

        // Major radio buttons
        ToggleGroup majorGroup = new ToggleGroup();
        RadioButton rbCS = new RadioButton("Computer Science");
        RadioButton rbBusiness = new RadioButton("Business");
        rbCS.setToggleGroup(majorGroup);
        rbBusiness.setToggleGroup(majorGroup);
        HBox majorBox = new HBox(10, rbCS, rbBusiness);
        grid.addRow(7, new Label("Major:"), majorBox);

        // Courses combo box
        ComboBox<String> cbCourses = new ComboBox<>();
        ListView<String> lvCourses = new ListView<>();
        grid.addRow(8, new Label("Select Course:"), cbCourses);
        grid.addRow(9, new Label("Selected Courses:"), lvCourses);

        // CheckBoxes for activities
        CheckBox cbSports = new CheckBox("Sports");
        CheckBox cbMusic = new CheckBox("Music");
        CheckBox cbArt = new CheckBox("Art");
        HBox activityBox = new HBox(10, cbSports, cbMusic, cbArt);
        grid.addRow(10, new Label("Activities:"), activityBox);

        // Text area for output
        TextArea taOutput = new TextArea();
        taOutput.setPrefRowCount(10);
        grid.addRow(11, new Label("Student Info:"), taOutput);

        // Update courses when major is selected
        majorGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            cbCourses.getItems().clear();
            lvCourses.getItems().clear();
            if (newToggle == rbCS) {
                cbCourses.getItems().addAll("Java", "Python", "C++");
            } else if (newToggle == rbBusiness) {
                cbCourses.getItems().addAll("Accounting", "Marketing", "Economics");
            }
        });

        // Add course to list when selected
        cbCourses.setOnAction(e -> {
            String selected = cbCourses.getValue();
            if (selected != null && !lvCourses.getItems().contains(selected)) {
                lvCourses.getItems().add(selected);
            }
        });

        // Show student info on selection change
        Button btnShow = new Button("Show Info");
        grid.addRow(12, btnShow);
        btnShow.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: ").append(tfName.getText()).append("\n");
            sb.append("Address: ").append(tfAddress.getText()).append("\n");
            sb.append("City: ").append(tfCity.getText()).append("\n");
            sb.append("Province: ").append(tfProvince.getText()).append("\n");
            sb.append("Postal: ").append(tfPostal.getText()).append("\n");
            sb.append("Phone: ").append(tfPhone.getText()).append("\n");
            sb.append("Email: ").append(tfEmail.getText()).append("\n");
            sb.append("Major: ").append(
                    rbCS.isSelected() ? "Computer Science" :
                            rbBusiness.isSelected() ? "Business" : "N/A"
            ).append("\n");
            sb.append("Courses: ").append(String.join(", ", lvCourses.getItems())).append("\n");
            sb.append("Activities: ");
            if(cbSports.isSelected()) sb.append("Sports ");
            if(cbMusic.isSelected()) sb.append("Music ");
            if(cbArt.isSelected()) sb.append("Art ");
            taOutput.setText(sb.toString());
        });

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Information");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
