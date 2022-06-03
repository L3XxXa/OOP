package ru.nsu.malov.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    private FoodGenerator foodGenerator;
    private WallsGenerator wallsGenerator;
    private GameField gameField;
    private Snake snake;
    private List<Point> python;
    private Point pythonHead;

    @BeforeEach
    private void init(){
        gameField  = new GameField(900, 900, 20, 20, 900/20);
        foodGenerator = new FoodGenerator(gameField, 1);
        wallsGenerator = new WallsGenerator(gameField, 1);
        snake = new Snake(gameField, foodGenerator, wallsGenerator);
        pythonHead = snake.getPythonHead();
        python = snake.getPython();
    }

    @Test
    public void removePython(){
        snake.remove();
        Assertions.assertEquals(1, python.size());
    }

    @Test
    public void crawlUp(){
        int posY = (int)pythonHead.getY();
        snake.crawlUp();
        Assertions.assertEquals(posY - 1, pythonHead.getY());
    }

    @Test
    public void crawlDown(){
        int posY = (int)pythonHead.getY();
        snake.crawlDown();
        Assertions.assertEquals(posY + 1, pythonHead.getY());
    }

    @Test
    public void crawlRight(){
        int posX = (int)pythonHead.getX();
        snake.crawlRight();
        Assertions.assertEquals(posX + 1, pythonHead.getX());
    }

    @Test
    public void crawlLeft(){
        int posX = (int)pythonHead.getX();
        snake.crawlLeft();
        Assertions.assertEquals(posX - 1, pythonHead.getX());
    }

    @Test
    public void collisionWall(){
        wallsGenerator.generateWalls(snake);
        wallsGenerator.getWalls().remove(wallsGenerator.getWalls().size() - 1);
        wallsGenerator.getWalls().add(new Point(15, 15));
        snake.setPythonHead(new Point(15, 15));
        Assertions.assertTrue(snake.collision());
    }

    @Test
    public void collisionBorder(){
        snake.setPythonHead(new Point(gameField.getCOLUMNS() + 1, 19));
        Assertions.assertTrue(snake.collision());
    }

    @Test
    public void collisionItself(){
        for (int i = 11; i < 15; i++) {
            snake.getPython().add(new Point(i, 10));
        }
        snake.getPython().add(new Point(10, 10));
        System.out.println(snake.getPython());
        Assertions.assertTrue(snake.collision());
    }

    @Test
    public void devour(){
        foodGenerator.getFood().add(new Point(10, 10));
        Assertions.assertEquals(1, snake.getPython().size());
        snake.devourFood();
        Assertions.assertEquals(2, snake.getPython().size());
        Assertions.assertEquals(1, snake.getScore());
    }


}