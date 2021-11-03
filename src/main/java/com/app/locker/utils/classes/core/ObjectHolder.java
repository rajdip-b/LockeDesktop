package com.app.locker.utils.classes.core;

import com.app.locker.utils.classes.UIManager;
import com.app.locker.utils.classes.logic.DBConnector;

public class ObjectHolder {

    private static ImageCache imageCache;
    private static UIManager uiManager;
    private static DBConnector dbConnector;

    public static void init(){
        imageCache = new ImageCache();
        uiManager = new UIManager();
        dbConnector = new DBConnector();
    }

    public static DBConnector getDbConnector() {
        return dbConnector;
    }

    public static ImageCache getImageCache() {
        return imageCache;
    }

    public static UIManager getUiManager() {
        return uiManager;
    }
}
