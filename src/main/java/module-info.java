module com.app.locker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires org.apache.derby.client;
    requires org.apache.derby.commons;
    requires org.apache.derby.engine;
    requires org.apache.derby.runner;
    requires org.apache.derby.tools;
    exports com.app.locker;
    opens com.app.locker.controller.windows;
    opens com.app.locker.model;
    exports com.app.locker.controller.popups;
    opens com.app.locker.controller.popups;
}