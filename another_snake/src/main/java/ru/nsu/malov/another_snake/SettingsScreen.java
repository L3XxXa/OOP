package ru.nsu.malov.another_snake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingsScreen extends Application {
    public ChoiceBox level;
    public TextField foodAmount;
    public TextField fieldSize;
    public TextField wallsAmount;
    public TextField scoreForWin;
    public Label error;
    public Button startGame;

    private int foodAmountInt;
    private int fieldSizeInt;
    private int wallsAmountInt;
    private int scoreForWinInt;
    private int levelInt;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("settings-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 900);
        primaryStage.setTitle("PYTHON");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onStartPressed(ActionEvent actionEvent) {
        if (level.getValue().equals("Easy")){
            levelInt = 1;
        }
        else if (level.getValue().equals("Medium")){
            levelInt = 2;
        }
        else if (level.getValue().equals("Hard")){
            levelInt = 3;
        }
        else {
            levelInt = 4;
        }
        if (foodAmount.getText().equals("")) {
            foodAmountInt = 3;
        } else {
            foodAmountInt = Integer.parseInt(foodAmount.getText());
        }
        if (fieldSize.getText().equals("")) {
            fieldSizeInt = 20;
        } else {
            fieldSizeInt = Integer.parseInt(fieldSize.getText());
        }
        if (wallsAmount.getText().equals("")) {
            wallsAmountInt = 0;
        } else {
            wallsAmountInt = Integer.parseInt(wallsAmount.getText());
        }
        if (scoreForWin.getText().equals("")) {
            scoreForWinInt = 10;
        } else {
            scoreForWinInt = Integer.parseInt(scoreForWin.getText());
        }
        if (fieldSizeInt <= 7 || foodAmountInt <= 0 || wallsAmountInt < 0 || scoreForWinInt < 0) {
            error.setVisible(true);
        } else {
            startGame.getScene().getWindow().hide();
            ru.nsu.malov.another_snake.Parameters parameters = new ru.nsu.malov.another_snake.Parameters(foodAmountInt, wallsAmountInt, fieldSizeInt, fieldSizeInt, scoreForWinInt, levelInt);
            SnakeGame snakeGame = new SnakeGame(parameters);
            Stage stage = new Stage();
            snakeGame.start(stage);
        }
    }
}
