package fractals;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Tree extends Fractal {
	
	private double angle = 30; //angle of branching in degrees
	private double branchRatio = 0.67;
	
	public Tree(Canvas canvas) {
		super(canvas);
	}
	
	public Tree(int order, Canvas canvas) {
		super(order, canvas);
	}
	
	public Tree(int order, Canvas canvas, int width, int height) {
		super(order, canvas, width, height);
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
		this.draw();
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		Tree treeCopy = new Tree(this.order, canvas);
		treeCopy.setAngle(this.angle);
		return treeCopy;
	}
	
	private void draw(int order, Point2D startPoint, double length, double angle) {
		Point2D newStartPoint = new Point2D(	
												startPoint.getX() - Math.sin(Math.toRadians(angle)) * length, 
												startPoint.getY() - Math.cos(Math.toRadians(angle)) * length
											);
		gc.strokeLine(startPoint.getX(), startPoint.getY(), newStartPoint.getX(), newStartPoint.getY());
		if(order <= 0) return;
		draw(order - 1, newStartPoint, length * branchRatio, angle + this.angle);
		draw(order - 1, newStartPoint, length * branchRatio, angle - this.angle);
	}
	
	@Override
	public void draw() {
		super.draw();
		Point2D startPoint = new Point2D(this.canvas.getWidth() / 2, this.canvas.getHeight() - 20.0);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1.5);
		draw(this.order, startPoint, (this.canvas.getHeight() < this.canvas.getWidth() ? this.canvas.getHeight() : this.canvas.getWidth()) / 3.5, 0);
	}
}