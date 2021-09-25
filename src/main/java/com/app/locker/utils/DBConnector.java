package com.app.locker.utils;

import java.sql.*;

public class DBConnector {

    private static Connection connection;

    //Tries to establish connection to the dearby db if the databse exists
    public static void setConnectionWithoutCreate() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby:database;create=false;");
    }

    // Creates a derby db if the database doesnt exist;
    public static void setConnectionWithCreate() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby:database;create=true;");
        createTables();
    }

    // Creates the default tables in the DB
    private static void createTables() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("create schema locker");
        statement.execute("create table locker.user_details (username varchar(60) not null, password varchar(66) not null)");
        statement.execute("create table locker.user_passwords (appname varchar(60) not null unique, password varchar(66) not null)");
    }

    public static void createNewUser(String username, String password) throws  SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into locker.user_details values (?,?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
    }

}
