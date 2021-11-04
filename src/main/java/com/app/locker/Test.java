package com.app.locker;

import com.app.locker.utils.classes.core.ObjectHolder;
import com.app.locker.utils.classes.UIManager;
import com.app.locker.utils.interfaces.EventListener;
import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application implements EventListener {

    private Stage currentStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObjectHolder.init();
        currentStage = new UIManager().getPasswordTableStage();
        currentStage.show();
    }

}
