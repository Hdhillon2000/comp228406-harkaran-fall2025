module comp228.lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens comp228.lab5 to javafx.fxml;
    exports comp228.lab5;
}