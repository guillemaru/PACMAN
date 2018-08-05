package Projet;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point {
	private int Xposition;
	private int Yposition;
	private Circle circle;
	private int size;
	
	public Point(int xposition, int yposition, Color color, int squarelength) {
		Xposition = xposition;
		Yposition = yposition;
		size = squarelength;

		circle = new Circle(size/2 + xposition*size, size/2 + yposition*size, size/4);
		circle.setFill(color);	
	}
	
	public void setXposition(int xposition) {
		Xposition = xposition;
		circle.setCenterX(Xposition*size + size/2);
	}
	
	public void setYposition(int yposition) {
		Yposition = yposition;
		circle.setCenterY(Yposition*size + size/2);
	}
	public int getXpostion() {
		return Xposition;
	}
	
	public int getYpostion() {
		return Yposition;
	}
	
	public Circle getCircle() {
		return circle;
	}
}