package ru.nsu.malov.game.snake;

import ru.nsu.malov.game.field.Point;

import java.util.List;

public class Snake {
    private int pythonLength;
    private Point pythonHead;
    private List<Point> python;
    private int velocityX;
    private int velocityY;
    private boolean trafficCollision;

    Snake(Point initialPoint){
        this.pythonHead = initialPoint;
        python.add(initialPoint);
        pythonLength = 1;
        velocityX = 0;
        velocityY = 0;
        trafficCollision = false;
    }

    private void extend(Point point){
        pythonLength += 1;
        python.add(point);
        pythonHead = point;
    }

    private void updating(Point point){
        if(python.contains(point)){
            trafficCollision = true;
        }
        python.add(point);
        pythonHead = point;
        python.remove(0);
    }

    public void movePython(Point point){
        if(!(velocityX == 0 & velocityY == 0)){
            updating(point);
        }
    }

    public void devour(){
        if(!(velocityY == 0 & velocityY == 0)){
            extend(pythonHead.movePoint(velocityX, velocityY));
        }
    }

    public void crawlUp(){
        if (velocityY == 1){
            return;
        }
        velocityY = 1;
        velocityX = 0;
    }

    public void crawlDown(){
        if(velocityY == -1){
            return;
        }
        velocityY = -1;
        velocityX = 0;
    }

    public void crawlRight(){
        if(velocityX == 1){
            return;
        }
        velocityX = 1;
        velocityY = 0;
    }


    public void crawlLeft(){
        if(velocityX == -1){
            return;
        }
        velocityX = -1;
        velocityY = 0;
    }



}
