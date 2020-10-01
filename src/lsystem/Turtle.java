package lsystem;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Turtle {
	
	private double angle;
	private double startAngle;
	private double currentAngle;
	private double length;
	private Canvas canvas;
	private GraphicsContext gc;
	private double x = 0.0;
	private double y = 0.0;
	
	public Turtle(Canvas canvas) {
		this(canvas, 50, 90, 0, 0, 0);
	}
	
	public Turtle(Canvas canvas, double length, double angle, double startAngle, double startX, double startY) {
		this.canvas = canvas;
		this.gc = this.canvas.getGraphicsContext2D();
		this.setAngle(angle);
		this.setStartAngle(startAngle);
		this.setLength(length);
		this.x = startX;
		this.y = startY;
	}
	
	public void setStroke(Color color) {
		gc.setStroke(color);
	}
	
	public void setLineWidth(double lineWidth) {
		gc.setLineWidth(lineWidth);
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setStartAngle(double startAngle) {
		this.startAngle = startAngle;
	}
	
	public void setCurrentAngle(double currentAngle) {
		this.currentAngle = currentAngle;
	}
	
	public void setLength(double length) {
		if(length < 0) this.length = 0;
		else this.length = length;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public double getStartAngle() {
		return this.startAngle;
	}
	
	public double getCurrentAngle() {
		return this.currentAngle;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void drawLine() {
		double newX = this.x + this.length * Math.cos(Math.toRadians(this.currentAngle));
		double newY = this.y - this.length * Math.sin(Math.toRadians(this.currentAngle));
		//gc.setLineWidth(1.0);
		//gc.setStroke(Color.BLACK);
		gc.strokeLine(this.x, this.y, newX, newY);
		this.x = newX;
		this.y = newY;
	}
	
	public void rotateLeft() {
		this.currentAngle += this.angle;
	}
	
	public void rotateRight() {
		this.currentAngle -= this.angle;
	}
	
	public void rotate(double angle) {
		this.currentAngle += this.angle;
	}
	
	public void reset() {
		this.x = 0.0;
		this.y = 0.0;
		this.currentAngle = 0.0;
		this.length = 0.0;
	}
}