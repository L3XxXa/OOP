package ru.nsu.malov.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final int STARTING_X;
    private final int STARTING_Y;

    private List<Point> python;
    private Point pythonHead;
    private GameField gameField;
    private FoodGenerator foodGenerator;
    private WallsGenerator wallsGenerator;
    private Point collisionPoint;
    private int score;

    public Snake (GameField gameField, FoodGenerator foodGenerator, WallsGenerator wallsGenerator){
        this.gameField = gameField;
        this.foodGenerator = foodGenerator;
        this.wallsGenerator = wallsGenerator;
        STARTING_X = gameField.getCOLUMNS() / 2;
        STARTING_Y = gameField.getROWS() / 2;
        python = new ArrayList<>();
        score = 0;
        initPython();
    }

    private void initPython() {
        python.add(new Point(STARTING_X, STARTING_Y));
        pythonHead = python.get(0);
    }

    public void remove(){
        python.removeAll(python);
        score = 0;
        initPython();
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
            if (pythonHead.getX() == python.get(i).getX() && pythonHead.getY() == python.get(i).getY()) {
                collisionPoint = python.get(i);
                return true;
            }
        }
        for (Point point:wallsGenerator.getWalls()) {
            if (point.getX() == pythonHead.getX() && point.getY() == pythonHead.getY()){
                collisionPoint = python.get(0);
                return true;
            }
        }
        return false;
    }

    /**
     * Method to eat food. Extends body of the snake
     * */
    public void devourFood(){
        for (int i = 0; i < foodGenerator.getFood().size(); i++) {
            if (pythonHead.getX() == foodGenerator.getFood().get(i).getX()
                    && pythonHead.getY() == foodGenerator.getFood().get(i).getY()) {
                foodGenerator.getFood().remove(i);
                foodGenerator.generateFood(wallsGenerator, this);
                python.add(new Point(-1, -1));
                score += 1;
            }
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

    public int getSTARTING_X() {
        return STARTING_X;
    }

    public int getSTARTING_Y() {
        return STARTING_Y;
    }
}
