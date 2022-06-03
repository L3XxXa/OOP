package ru.nsu.malov.another_snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainScreen extends Application {
    public Button start;
    public Button controllersScreen;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 900);
        primaryStage.setTitle("PYTHON");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * On button start click listener
     * Starts the stage with the settings screen
     *
     * @param actionEvent
     */
    public void onStartButtonClick(ActionEvent actionEvent) {
        start.getScene().getWindow().hide();
        SettingsScreen settingsScreen = new SettingsScreen();
        Stage stage = new Stage();
        try {
            settingsScreen.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * On button exit click listener
     * Closes the game
     *
     * @param actionEvent
     */
    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * On button controllers click listener
     * Starts new stage with controllers screen
     *
     * @param actionEvent
     */
    public void onControllersClick(ActionEvent actionEvent) {
        start.getScene().getWindow().hide();
        Stage stage = new Stage();
        ControllersScreen controllersScreen = new ControllersScreen();
        try {
            controllersScreen.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
