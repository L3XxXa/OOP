package ru.nsu.malov.another_snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.malov.graphics.Graphics;
import ru.nsu.malov.model.FoodGenerator;
import ru.nsu.malov.model.GameField;
import ru.nsu.malov.model.Snake;

import java.awt.*;

public class SnakeGame extends Application {
    private final int DOWN = 0;
    private final int LEFT = 1;
    private final int UP = 2;
    private final int RIGHT = 3;

    private final int HORIZONTAL_SIZE = 800;
    private final int VERTICAL_SIZE = 800;
    private final int ROWS = 20;
    private final int COLUMNS = 20;
    private final int POINT_SIZE = HORIZONTAL_SIZE / COLUMNS;

    private GameField gameField;
    private FoodGenerator foodGenerator;
    private Snake snake;
    private Graphics graphics;
    private Timeline timeline;
    private int direction;
    private int score;
    private int scoreForWin = 15;

    @Override
    public void start(Stage stage) {
        direction = RIGHT;
        gameField = new GameField(HORIZONTAL_SIZE, VERTICAL_SIZE, COLUMNS, ROWS, POINT_SIZE);
        foodGenerator = new FoodGenerator(gameField);
        snake = new Snake(gameField, foodGenerator);
        graphics = new Graphics(gameField);
        stage.setTitle("PYTHON");
        Group root = new Group();
        Canvas canvas = new Canvas(HORIZONTAL_SIZE, VERTICAL_SIZE);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.UP){
                if (direction != DOWN){
                    direction = UP;
                }
            }
            else if (keyCode == KeyCode.LEFT){
                if (direction != RIGHT){
                    direction = LEFT;
                }
            }
            else if (keyCode == KeyCode.DOWN){
                if (direction != UP){
                    direction = DOWN;
                }
            }
            else if (keyCode == KeyCode.RIGHT){
                if (direction != LEFT){
                    direction = RIGHT;
                }
            }
        });
        foodGenerator.generateFood(snake.getPython());
        snake.collision();
        timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> crawling(graphicsContext)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void crawling(GraphicsContext graphicsContext) {
        if (snake.collision()) {
            timeline.stop();
            graphics.drawCollision(graphicsContext, snake.getCollisionPoint());
            graphics.drawGameOver(graphicsContext, score);
            return;
        }

        if (score == scoreForWin){
            timeline.stop();
            graphics.drawWin(graphicsContext);
            return;
        }
        graphics.drawBackGround(graphicsContext);
        graphics.drawPython(graphicsContext, snake.getPythonHead(), snake.getPython());
        graphics.drawFood(graphicsContext, foodGenerator.getFood());
        snake.devourFood();
        score = snake.getScore();
        graphics.drawScore(graphicsContext, score, scoreForWin);
        if (snake.getPython().size() > 1) {
            Point crawling = snake.getPython().get(snake.getPython().size() - 1);
            crawling.x = snake.getPythonHead().x;
            crawling.y = snake.getPythonHead().y;
            snake.getPython().add(1, crawling);
            snake.getPython().remove(snake.getPython().size() - 1);
        }

        if(direction == RIGHT){
            snake.crawlRight();
        }
        else if(direction == DOWN){
            snake.crawlDown();
        }
        else if(direction == LEFT){
            snake.crawlLeft();
        }
        else{
            snake.crawlUp();
        }
    }



    public static void main(String[] args) {
        launch();
    }
}