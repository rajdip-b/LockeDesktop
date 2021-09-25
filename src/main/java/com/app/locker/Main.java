package com.app.locker;

import com.app.locker.utils.DBConnector;
import com.app.locker.view.LoginGUI;
import com.app.locker.view.SignupGUI;

import java.sql.SQLException;

public class Main {

    // Tries to connect to the existing derby database. If it exists, prompts for login.
    // Else, creates a new database and asks the user for a new signup.
    // For any corruption in the db, the app doesnt work.
    public static void main(String[] args) {
        try{
            DBConnector.setConnectionWithoutCreate();
            launchLoginGUI();
        }catch (SQLException e){
            try {
                DBConnector.setConnectionWithCreate();
                launchSignupGUI();
            }catch (SQLException ignored){
                System.exit(1);
            }
        }
    }

    private static void launchLoginGUI(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                javafx.application.Application.launch(LoginGUI.class);
            }
        }.start();
    }

    private static void launchSignupGUI(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                javafx.application.Application.launch(SignupGUI.class);
            }
        }.start();
    }

}
