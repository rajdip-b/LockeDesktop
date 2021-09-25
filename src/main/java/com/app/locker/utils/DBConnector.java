package com.app.locker.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection connection;

    //Tries to establish connection to the dearby db if the databse exists
    public static void setConnectionWithoutCreate() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby:database;create=false;");
    }

    // Creates a derby db if the database doesnt exist;
    public static void setConnectionWithCreate() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby:database;create=true;");
    }

}
