package com.app.locker.utils.classes.logic;

import com.app.locker.model.Entry;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DBConnector {

    private static final int INDEX_SERVICE = 1;
    private static final int INDEX_USERNAME = 2;
    private static final int INDEX_PASSWORD = 3;
    private static final int INDEX_EMAIL = 4;
    private static final int INDEX_CREATED = 5;

    public Connection connection;

    //Tries to establish connection to the dearby db if the databse exists
    public void setConnectionWithoutCreate() throws SQLException{
        connection = DriverManager.getConnection("jdbc:derby:database;create=false;");
    }

    // Creates a derby db if the database doesnt exist;
    public void setConnectionWithCreate() throws SQLException{
        connection = DriverManager.getConnection("jdbc:derby:database;create=true;");
        createTables();
    }

    // Creates the default tables in the DB
    private void createTables() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("create schema locker");
        statement.execute("create table locker.user_details (username varchar(60) not null, password varchar(66) not null)");
        statement.execute("create table locker.user_services (service varchar(60) not null unique, username varchar(66), password varchar(700) not null, email varchar(30), created varchar(60))");
    }

    public void createNewUser(String username, String password) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into locker.user_details values (?,?)");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
    }

    public ArrayList<Entry> getExistingData() throws SQLException{
        ArrayList<Entry> tempList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from locker.user_services");
        while(resultSet.next()){
            Entry entry = new Entry();
            entry.setService(resultSet.getString(INDEX_SERVICE));
            entry.setUsername(resultSet.getString(INDEX_USERNAME));
            entry.setPassword(resultSet.getString(INDEX_PASSWORD));
            entry.setEmail(resultSet.getString(INDEX_EMAIL));
            entry.setCreated(resultSet.getString(INDEX_CREATED));
            tempList.add(entry);
        }
        return tempList;
    }

    public void addData(Entry entry) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("insert into locker.user_services values(?,?,?,?,?)");
        preparedStatement.setString(INDEX_SERVICE, entry.getService());
        preparedStatement.setString(INDEX_USERNAME, entry.getUsername());
        preparedStatement.setString(INDEX_PASSWORD, entry.getPassword());
        preparedStatement.setString(INDEX_EMAIL, entry.getEmail());
        preparedStatement.setString(INDEX_CREATED, entry.getCreated());
        preparedStatement.executeUpdate();
    }

    public void deleteAllEntries() throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("delete from locker.user_services");
    }

    public void deleteEntry(Entry entry) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("delete from locker.user_services where service = ?");
        preparedStatement.setString(1, entry.getService());
        preparedStatement.executeUpdate();
    }

    public void updateEntry(Entry entry) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("update locker.user_services set username = ?, password = ?, email = ? where service = ?");
        preparedStatement.setString(1, entry.getUsername());
        preparedStatement.setString(2, entry.getPassword());
        preparedStatement.setString(3, entry.getEmail());
        preparedStatement.setString(4, entry.getService());
        preparedStatement.executeUpdate();
    }

    public String getName() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select username from locker.user_details");
        String username = null;
        while(rs.next()){
            username = rs.getString(1);
        }
        return  username;
    }

    public String getPassword() throws SQLException{
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select password from locker.user_details");
        String password = null;
        while(rs.next()){
            password = rs.getString(1);
        }
        return  password;
    }

}
