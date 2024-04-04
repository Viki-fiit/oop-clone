module com.example.starhike {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.starhike to javafx.fxml, javafx.base;
    opens Hike to javafx.fxml;
    exports com.example.starhike;
    exports Hike;
    exports FxControllers;
    exports Monitoring;
    exports User;
    opens FxControllers to javafx.fxml;
}