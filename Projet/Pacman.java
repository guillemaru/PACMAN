package Projet;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pacman {
    private int Xposition;
    private int Yposition;
    private int movementdirection; //0 -> stop | 1 -> top | 2 -> right | 3 -> bottom | 4 -> left
    private int size;
    private DoubleProperty sizeDP = new SimpleDoubleProperty((double) size);
    private DoubleProperty sceneWDP = new SimpleDoubleProperty(800);
    private DoubleProperty sceneHDP = new SimpleDoubleProperty(400);
    private Circle circle;
    private Image imgpac;
    private ImageView imgvpac;
    private int Time; // New code
    private int wantedDirection; //New code
    
    
    public Pacman(int xposition, int yposition, int squarelength) {
        Xposition = xposition;
        Yposition = yposition;
        movementdirection = 0;
        size = squarelength;
        sizeDP.set(size);
        circle = new Circle(sizeDP.get()/2 + xposition*sizeDP.get(), sizeDP.get()/2 + yposition*sizeDP.get(), sizeDP.get()/2);
        circle.setFill(Color.YELLOW);
        imgpac = new Image("pacman.png");
        imgvpac = new ImageView(imgpac);
        Time = 0; //New code
        wantedDirection = 0;
        /*imgvpac.setFitWidth(20);
        imgvpac.setFitHeight(20);*/
    }
    
    public void setMovement(int i) {
        if (i < 5) {
            movementdirection = i;
        }
    }
    
    public void setPosition(int xposition, int yposition) {
        if ((xposition < Math.round(sceneWDP.get()/sizeDP.get())) && (yposition <  Math.round(sceneHDP.get()/sizeDP.get()))) {
            Xposition = xposition;
            Yposition = yposition;
        }
        circle.setCenterX(sizeDP.get()/2 + Yposition*sizeDP.get());
        circle.setCenterY(sizeDP.get()/2 + Xposition*sizeDP.get());
    }
    
    public void move() {
        if (movementdirection == 1) {
            if (Yposition > 1) {Yposition = Yposition - 1;}
        }
        if (movementdirection == 2) {
             if (Xposition < Math.round(sceneWDP.get()/sizeDP.get()) - 2) {Xposition = Xposition + 1;}
        }
        if (movementdirection == 3) {
            if (Yposition < Math.round(sceneHDP.get()/sizeDP.get()) - 2) {Yposition = Yposition + 1;}
        }
        if (movementdirection == 4) {
            if (Xposition > 1) {Xposition = Xposition -1;}
        }
        
        circle.setCenterX(sizeDP.get()/2 + Xposition*sizeDP.get());
        circle.setCenterY(sizeDP.get()/2 + Yposition*sizeDP.get());
    }
    
    public int getXPosition() {
        return Xposition;
    }
    
    public int getYPosition() {
        return Yposition;
    }
    
    public int getMovement() {
        return movementdirection;
    }
    
    public int getsize() {
        return size;
    }
    
    public Circle getCircle() {
        return circle;
    }
    
    public ImageView getImg() {
        return imgvpac;
    }
    
    public DoubleProperty getsizeDP() {
        return sizeDP;
    }
    
    public DoubleProperty getsceneWDP() {
        return sceneWDP;
    }
    
    public DoubleProperty getsceneHDP() {
        return sceneHDP;
    }
    
    //Beginning of new code
    public void addTime() {
    	Time = Time +1;
    }
    
    public int getTime() {
    	return Time;
    }
    
    public void setWantedDirection(int i) {
    	wantedDirection = i;
    }
    
    public int getWantedDirection() {
    	return wantedDirection;
    }
    //End of new code
}
 
