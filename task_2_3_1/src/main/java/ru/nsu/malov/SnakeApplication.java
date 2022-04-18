package ru.nsu.malov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SnakeApplication extends Application {
    static List<VBox> layout = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderStartScene = new FXMLLoader(SnakeApplication.class.getResource("hello-view.fxml"));
        layout.add((VBox)FXMLLoader.load(getClass().getResource("hello-view.fxml")));
        Scene startScene = new Scene(fxmlLoaderStartScene.load(), 1920, 1080);
        stage.setTitle("SNAKE");
        stage.setFullScreen(true);
        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}