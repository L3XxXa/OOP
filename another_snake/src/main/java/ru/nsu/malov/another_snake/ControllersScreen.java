package ru.nsu.malov.another_snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;

public class ControllersScreen extends Application {
    public Button toMainScreen;
    public Button startGame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("keys-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 900);
        primaryStage.setTitle("PYTHON");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onToMainScreenPressed(ActionEvent actionEvent) {
        Stage stage = new Stage();
        toMainScreen.getScene().getWindow().hide();
        MainScreen mainScreen = new MainScreen();
        try {
            mainScreen.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStartPressed(ActionEvent actionEvent) {
        startGame.getScene().getWindow().hide();
        SettingsScreen settingsScreen = new SettingsScreen();
        Stage stage = new Stage();
        try {
            settingsScreen.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
