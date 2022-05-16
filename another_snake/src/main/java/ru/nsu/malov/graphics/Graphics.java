package ru.nsu.malov.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ru.nsu.malov.model.GameField;

import java.awt.*;
import java.util.List;

public class Graphics {
    private GameField gameField;

    public Graphics (GameField gameField){
        this.gameField = gameField;
    }

    public void drawBackGround(GraphicsContext graphicsContext){
        for (int i = 0; i < gameField.getROWS(); i++) {
            for (int j = 0; j < gameField.getCOLUMNS(); j++) {
                graphicsContext.setFill(Color.web("000000"));
                graphicsContext.fillRect(i * gameField.getPOINT_SIZE(), j * gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE());
            }
        }
    }

    public void drawFood(GraphicsContext graphicsContext, Point food){
        graphicsContext.setFill(Color.web("ff0000"));
        graphicsContext.fillRoundRect(food.getX() * gameField.getPOINT_SIZE(), food.getY() * gameField.getPOINT_SIZE(),
                gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), 35, 35);
    }

    public void drawPython(GraphicsContext graphicsContext, Point pythonHead, List<Point> python){
        graphicsContext.setFill(Color.web("05f53d"));
        graphicsContext.fillRoundRect(pythonHead.getX() * gameField.getPOINT_SIZE(),
                pythonHead.getY() * gameField.getPOINT_SIZE(),
                gameField.getPOINT_SIZE() - 1,
                gameField.getPOINT_SIZE() - 1,
                25, 25);

        for (int i = 1; i < python.size(); i++) {
            graphicsContext.fillRoundRect(python.get(i).getX() * gameField.getPOINT_SIZE(),
                    python.get(i).getY() * gameField.getPOINT_SIZE(),
                    gameField.getPOINT_SIZE() - 1,
                    gameField.getPOINT_SIZE() - 1,
                    20, 20);
        }
    }

    public void drawCollision(GraphicsContext graphicsContext, Point collisionPoint){
        graphicsContext.setFill(Color.web("ff0000"));
        graphicsContext.fillRoundRect(collisionPoint.getX() * gameField.getPOINT_SIZE(), collisionPoint.getY() * gameField.getPOINT_SIZE(),
                gameField.getPOINT_SIZE(), gameField.getPOINT_SIZE(), 25, 25);
    }

    public void drawScore(GraphicsContext graphicsContext, int score, int scoreForWin){
        graphicsContext.setFill(Color.web("ffffff"));
        graphicsContext.setFont(new Font("Digital-7", 35));
        graphicsContext.fillText("Score: " + score + "/" + scoreForWin, 10, 35);

    }

    public void drawGameOver(GraphicsContext graphicsContext, int score) {
        graphicsContext.setFill(Color.web("05f53d"));
        graphicsContext.setFont(new Font("Digital-7", 70));
        graphicsContext.fillText("Game Over" + "\n" + "Score " + score, gameField.getWIDTH() / 3.5, gameField.getHEIGHT() / 2);
    }

    public void drawWin(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.web("05f53d"));
        graphicsContext.setFont(new Font("Digital-7", 70));
        graphicsContext.fillText("You won", gameField.getWIDTH() / 3.5, gameField.getHEIGHT() / 2);
    }
}
