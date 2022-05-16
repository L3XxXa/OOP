package ru.nsu.malov.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Point> python;
    private Point pythonHead;
    private GameField gameField;
    private FoodGenerator foodGenerator;
    private Point collisionPoint;
    private int score;

    public Snake (GameField gameField, FoodGenerator foodGenerator){
        this.gameField = gameField;
        this.foodGenerator = foodGenerator;
        python = new ArrayList<>();
        score = 0;
        initPython();
    }

    private void initPython() {
        python.add(new Point(5, gameField.getROWS()/2));
        pythonHead = python.get(0);
    }

    /**
     * Method to crawl up
     * */
    public void crawlUp(){
        pythonHead.y--;
    }

    /**
     * Method to crawl down
     * */
    public void crawlDown(){
        pythonHead.y++;
    }

    /**
     * Method to crawl left
     * */
    public void crawlLeft(){
        pythonHead.x--;
    }


    /**
     * Method to crawl right
     * */
    public void crawlRight(){
        pythonHead.x++;
    }

    /**
     * Collision with snake itself or with borders of the game field
     * */
    public boolean collision(){
        if (pythonHead.x < 0 || pythonHead.y < 0 ||
                pythonHead.x * gameField.getPOINT_SIZE() >= gameField.getWIDTH() ||
                pythonHead.y * gameField.getPOINT_SIZE() >= gameField.getHEIGHT()) {
            collisionPoint = python.get(0);
            return true;
        }
            for (int i = 3; i < python.size(); i++) {
                if (pythonHead.getX()== python.get(i).getX() && pythonHead.getY() == python.get(i).getY()) {
                    collisionPoint = python.get(i);
                    return true;
            }
        }
        return false;
    }

    /**
     * Method to eat food. Extends body of the snake
     * */
    public void devourFood(){
        if (pythonHead.getX() == foodGenerator.getFood().getX() && pythonHead.getY() == foodGenerator.getFood().getY()) {
            python.add(new Point(-1, -1));
            foodGenerator.generateFood(python);
            score += 1;
        }
    }

    public int getScore() {
        return score;
    }

    public Point getPythonHead() {
        return pythonHead;
    }

    public List<Point> getPython() {
        return python;
    }

    public Point getCollisionPoint() {
        return collisionPoint;
    }
}
