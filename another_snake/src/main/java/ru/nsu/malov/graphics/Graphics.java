package ru.nsu.malov.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ru.nsu.malov.model.GameField;

import java.awt.*;
import java.util.List;

public class Graphics {
    private GameField gameField;

    /**
     * Constructor for the Graphics class
     *
     * @param gameField - Game field
     */
    public Graphics(GameField gameField) {
        this.gameField = gameField;
    }

    /**
     * Draws black background
     *
     * @param graphicsContext - graphic context of the stage
     */
    public void drawBackGround(GraphicsContext graphicsContext) {
        for (int i = 0; i < gameField.getROWS(); i++) {
            for (int j = 0; j < gameField.getCOLUMNS(); j++) {
                graphicsContext.setFill(Color.web("000000"));
                graphicsContext.fillRect(i * gameField.getPOINT_SIZE(), j * gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE());
            }
        }
    }

    /**
     * Draws food on the game field
     *
     * @param graphicsContext - graphic context of the stage
     * @param food            - array list with food
     */
    public void drawFood(GraphicsContext graphicsContext, List<Point> food) {
        graphicsContext.setFill(Color.web("ff0000"));
        for (Point point : food) {
            graphicsContext.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(), point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), 45, 45);
        }

    }


    /**
     * Draws python on the game field
     *
     * @param graphicsContext - graphic context of the stage
     * @param python          - array list with python
     */
    public void drawPython(GraphicsContext graphicsContext, List<Point> python) {
        graphicsContext.setFill(Color.web("05f53d"));
        for (Point point : python) {
            graphicsContext.fillRoundRect(point.getX() * gameField.getPOINT_SIZE(),
                    point.getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE() - 1,
                    gameField.getPOINT_SIZE() - 1,
                    25, 25);
        }
    }

    /**
     * Draws walls on the game field
     *
     * @param graphicsContext - graphic context of the stage
     * @param walls           - array list with walls
     */
    public void drawWalls(GraphicsContext graphicsContext, List<Point> walls) {
        graphicsContext.setFill(Color.web("ffffff"));
        for (Point point : walls) {
            graphicsContext.fillRect(point.getX() * gameField.getPOINT_SIZE(), point.getY() * gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE());
        }
    }

    /**
     * Draws collision on the snake
     *
     * @param graphicsContext - graphic context of the stage
     * @param collisionPoint  - point of the collision
     */
    public void drawCollision(GraphicsContext graphicsContext, Point collisionPoint) {
        graphicsContext.setFill(Color.web("ff0000"));
        graphicsContext.fillRoundRect(collisionPoint.getX() * gameField.getPOINT_SIZE(), collisionPoint.getY() * gameField.getPOINT_SIZE(),
                gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), 25, 25);
    }

    /**
     * Draws score
     *
     * @param graphicsContext - graphic context of the stage
     * @param score           - current score
     * @param scoreForWin     - score is needed to win
     */
    public void drawScore(GraphicsContext graphicsContext, int score, int scoreForWin) {
        graphicsContext.setFill(Color.web("ffffff"));
        graphicsContext.setFont(new Font("Digital-7", 35));
        graphicsContext.fillText("Score: " + score + "/" + scoreForWin, 10, 35);

    }

    /**
     * Draws game over screen
     *
     * @param graphicsContext - graphic context of the stage
     * @param score           - current score
     */
    public void drawGameOver(GraphicsContext graphicsContext, int score) {
        drawBackGround(graphicsContext);
        graphicsContext.setFill(Color.web("05f53d"));
        graphicsContext.setFont(new Font("Digital-7", 50));
        graphicsContext.fillText("Game Over" + "\n" + "Score " + score + "\n" + "Press ENTER to " + "\n" + "restart the game" + "\n" + "Press ESC to" + "\n" + "open settings",
                gameField.getWIDTH() / 3.6, gameField.getHEIGHT() / 3.0);
    }

    /**
     * Draws pause screen
     *
     * @param graphicsContext - graphic context of the stage
     */
    public void drawPause(GraphicsContext graphicsContext) {
        drawBackGround(graphicsContext);
        graphicsContext.setFill(Color.web("05f53d"));
        graphicsContext.setFont(new Font("Regular", 50));
        graphicsContext.fillText("""
                Game paused
                Press ENTER to
                restart the game
                Press ESC to
                open settings
                Press SPACE to
                continue""", gameField.getWIDTH() / 3.6, gameField.getHEIGHT() / 3.5);
    }

    /**
     * Draws win screen
     *
     * @param graphicsContext - graphic context of the stage
     */
    public void drawWin(GraphicsContext graphicsContext) {
        drawBackGround(graphicsContext);
        graphicsContext.setFill(Color.web("05f53d"));
        graphicsContext.setFont(new Font("Regular", 50));
        graphicsContext.fillText("""
                You won
                Press ENTER to
                restart the game
                Press ESC to
                open settings""", gameField.getWIDTH() / 4.5, gameField.getHEIGHT() / 2.5);
    }
}
