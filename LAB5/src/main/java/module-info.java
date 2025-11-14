module comp228.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // <- this is needed for JDBC

    opens exercise1 to javafx.fxml; // <- your package name
    exports exercise1;
}
