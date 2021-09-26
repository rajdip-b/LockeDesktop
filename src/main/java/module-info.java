module com.app.locker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.app.locker.controller;
    exports com.app.locker;

}