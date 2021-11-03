module com.app.locker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires org.apache.derby.client;
    requires org.apache.derby.commons;
    requires org.apache.derby.engine;
    requires java.desktop;
    exports com.app.locker;
    exports com.app.locker.controller;
    opens com.app.locker.controller;
    exports com.app.locker.utils.interfaces;
    opens com.app.locker.utils.interfaces;
    exports com.app.locker.controller.fragments;
    opens com.app.locker.controller.fragments;
    exports com.app.locker.model;
    opens com.app.locker.model;
    exports com.app.locker.controller.popups;
    opens com.app.locker.controller.popups;
}