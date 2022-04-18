package ru.nsu.malov;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SnakeController implements Initializable {
    @FXML
    public ImageView logo;

    @FXML
    private Label welcomeText;

    private GameController gameController;

    @FXML
    protected void onStartButtonClick() {
;
    }

    public void onExitButtonClick(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameController = new GameController();
        File logoFile = new File("src/main/resources/ru/nsu/malov/images/logo.png");
        if(!logoFile.exists()){
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Image image = new Image(logoFile.toURI().toString());
        logo.setImage(image);
    }
}