package ru.nsu.malov.another_snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.malov.graphics.Graphics;
import ru.nsu.malov.model.*;

import java.awt.*;

public class SnakeGame extends Application {
    private final int DOWN = 0;
    private final int LEFT = 1;
    private final int UP = 2;
    private final int RIGHT = 3;

    private final int EASY = 1;
    private final int MEDIUM = 2;
    private final int HARD = 3;
    private final int GOD = 4;

    private final int HORIZONTAL_SIZE;
    private final int VERTICAL_SIZE;
    private final int ROWS;
    private final int COLUMNS;
    private final int SCORE_FOR_WIN;
    private final int POINT_SIZE;
    private final int MAX_FOOD;
    private final int MAX_WALLS;
    private final int LEVEL;

    private GameField gameField;
    private FoodGenerator foodGenerator;
    private WallsGenerator wallsGenerator;
    private Snake snake;
    private Graphics graphics;
    private Timeline timeline;
    private int direction;
    private int score;
    private boolean gameOver;

    public SnakeGame(ru.nsu.malov.another_snake.Parameters parameters) {
        HORIZONTAL_SIZE = parameters.getHORIZONTAL_SIZE();
        VERTICAL_SIZE = parameters.getVERTICAL_SIZE();
        ROWS = parameters.getMAX_ROWS();
        COLUMNS = parameters.getMAX_COLUMNS();
        SCORE_FOR_WIN = parameters.getSCORE_FOR_WIN();
        POINT_SIZE = parameters.getPOINT_SIZE();
        MAX_WALLS = parameters.getMAX_WALLS();
        MAX_FOOD = parameters.getMAX_FOOD();
        LEVEL = parameters.getLEVEL();
        score = 0;
        gameOver = false;
    }

    @Override
    public void start(Stage stage) {
        direction = RIGHT;
        gameField = new GameField(HORIZONTAL_SIZE, VERTICAL_SIZE, COLUMNS, ROWS, POINT_SIZE);
        foodGenerator = new FoodGenerator(gameField, MAX_FOOD);
        wallsGenerator = new WallsGenerator(gameField, MAX_WALLS);
        snake = new Snake(gameField, foodGenerator, wallsGenerator);
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
            else if (keyCode == KeyCode.ENTER){
                if (gameOver){
                    restart();
                }
            }
            else if (keyCode == KeyCode.ESCAPE){
                if (gameOver){
                    settings(stage);
                }
            }
        });

        wallsGenerator.generateWalls(snake);
        foodGenerator.generateFood(wallsGenerator, snake);
        snake.collision();
        switch (LEVEL){
            case EASY -> timeline = new Timeline(new KeyFrame(Duration.millis(220), e -> crawling(graphicsContext)));
            case MEDIUM -> timeline = new Timeline(new KeyFrame(Duration.millis(160), e -> crawling(graphicsContext)));
            case HARD -> timeline = new Timeline(new KeyFrame(Duration.millis(120), e -> crawling(graphicsContext)));
            case GOD ->  timeline = new Timeline(new KeyFrame(Duration.millis(80), e -> crawling(graphicsContext)));

        }
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void settings(Stage primaryStage) {
        primaryStage.getScene().getWindow().hide();
        SettingsScreen settingsScreen = new SettingsScreen();
        Stage stage = new Stage();
        try {
            settingsScreen.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void restart(){
        timeline.play();
        gameOver = false;
        wallsGenerator.remove();
        foodGenerator.remove();
        snake.remove();
        direction = RIGHT;
        wallsGenerator.generateWalls(snake);
        foodGenerator.generateFood(wallsGenerator, snake);
        score = snake.getScore();
    }


    private void crawling(GraphicsContext graphicsContext) {
        if (snake.collision()) {
            timeline.stop();
            graphics.drawCollision(graphicsContext, snake.getCollisionPoint());
            graphics.drawGameOver(graphicsContext, score);
            gameOver = true;
            return;
        }
        if (score == SCORE_FOR_WIN){
            timeline.stop();
            graphics.drawWin(graphicsContext);
            gameOver = true;
            return;
        }
        graphics.drawBackGround(graphicsContext);
        graphics.drawWalls(graphicsContext, wallsGenerator.getWalls());
        graphics.drawPython(graphicsContext, snake.getPythonHead(), snake.getPython());
        snake.devourFood();
        graphics.drawFood(graphicsContext, foodGenerator.getFood());
        score = snake.getScore();
        graphics.drawScore(graphicsContext, score, SCORE_FOR_WIN);
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



}