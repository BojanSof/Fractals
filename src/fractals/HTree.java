package fractals;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class HTree extends Fractal {
	
	public HTree(Canvas canvas) {
		super(canvas);
	}
	
	public HTree(int order, Canvas canvas) {
		super(order, canvas);
	}
	
	public HTree(int order, Canvas canvas, int width, int height) {
		super(order, canvas, width, height);
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		return new HTree(this.order, canvas);
	}
	
	private void draw(int order, Point2D center, double length) {
		gc.strokeLine(center.getX() - length / 2, center.getY() - length / 2, center.getX() - length / 2, center.getY() + length / 2);
		gc.strokeLine(center.getX() + length / 2, center.getY() - length / 2, center.getX() + length / 2, center.getY() + length / 2);
		gc.strokeLine(center.getX() - length / 2, center.getY(), center.getX() + length / 2, center.getY());
		if(order <= 0) return;
		draw(order - 1, new Point2D(center.getX() - length / 2, center.getY() - length / 2), length / 2);
		draw(order - 1, new Point2D(center.getX() - length / 2, center.getY() + length / 2), length / 2);
		draw(order - 1, new Point2D(center.getX() + length / 2, center.getY() - length / 2), length / 2);
		draw(order - 1, new Point2D(center.getX() + length / 2, center.getY() + length / 2), length / 2);
	}
	
	@Override
	public void draw() {
		super.draw();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1.5);
		draw(	this.order, new Point2D(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2), 
				((this.canvas.getWidth() < this.canvas.getHeight()) ? this.canvas.getWidth()/3 : this.canvas.getHeight()/3)
			);
	}
}