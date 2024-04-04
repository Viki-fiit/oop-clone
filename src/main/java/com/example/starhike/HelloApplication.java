package com.example.starhike;


import Datasets.UserDataset;
import Hike.BaseHike;
import Monitoring.Check;
import Monitoring.Serialize;
import Other.GlobalVariables;

import User.BaseUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import FxControllers.Message;

import Serialize.*;

import Thread.*;
import Datasets.*;
/**
 * The main class for the StarHike application that extends the JavaFX {@link Application} class.
 */
public class HelloApplication extends Application implements Message  {
    /**
     * The start method is called after the init method has returned and after the system is ready for the application
     * to begin running. It loads the FXML file for the welcome screen and displays it.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcomeScreen.fxml"));
        Parent root =  fxmlLoader.load();
        Scene welcome = new Scene(root);
        stage.setTitle("Welcome to StarHike!");
        stage.setScene(welcome);
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            try {
                logout(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
    /**
     * Logs out the user and saves the user dataset to file.
     * Calls upon a check, which is observable, and initializes serialization.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while saving the user dataset to file.
     */
    public void logout(Stage stage)throws IOException{


        Alert alert = loggingOut();
        new Check(GlobalVariables.observer, "all");
        UserDataset temp = UserDataset.getInstance();
        temp.writeData();

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    } /**
     * The main method for the application.
     *
     * @param args The command line arguments.
     * @throws IOException            If an error occurs while loading the serialized hikes or users.
     * @throws InterruptedException   If a thread is interrupted.
     * @throws ClassNotFoundException If a class cannot be found during deserialization.
     */

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        new WeatherThread();
        /*new Initialize();
        SerializeHikes.serialize(GlobalVariables.allHikes);
        SerializeUsers.serialize(GlobalVariables.allUsers);*/


        GlobalVariables.allHikes =SerializeHikes.deserialize();
        GlobalVariables.allUsers = SerializeUsers.deserialize();




        Thread.sleep(1000);

        launch();

    }
}