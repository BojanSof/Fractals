package fractals;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class SierpinskiCarpet extends Fractal {
	
	public SierpinskiCarpet(Canvas canvas) {
		super(canvas);
	}
	
	public SierpinskiCarpet(int order, Canvas canvas) {
		super(order, canvas);
	}
	
	public SierpinskiCarpet(int order, Canvas canvas, int width, int height) {
		super(order, canvas, width, height);
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		return new SierpinskiCarpet(this.order, canvas);
	}
	
	private void draw(int order, Point2D center, double side) {
		gc.fillRect(center.getX() - side / 2, center.getY() - side / 2, side, side);
		gc.clearRect(center.getX() - side / 6, center.getY() - side / 6, side / 3, side / 3);
		if(order <= 0) return;
		draw(order - 1, new Point2D(center.getX() - side / 3, center.getY() - side / 3), side / 3);
		draw(order - 1, new Point2D(center.getX(), center.getY() - side / 3), side / 3);
		draw(order - 1, new Point2D(center.getX() + side / 3, center.getY() - side / 3), side / 3);
		draw(order - 1, new Point2D(center.getX() - side/3, center.getY()), side / 3);
		draw(order - 1, new Point2D(center.getX() + side/3, center.getY()), side / 3);
		draw(order - 1, new Point2D(center.getX() - side / 3, center.getY() + side / 3), side / 3);
		draw(order - 1, new Point2D(center.getX(), center.getY() + side / 3), side / 3);
		draw(order - 1, new Point2D(center.getX() + side / 3, center.getY() + side / 3), side / 3);
	}
	
	@Override
	public void draw() {
		super.draw();
		Point2D center = new Point2D(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
		gc.setFill(Color.BLACK);
		draw(this.order, center, (this.canvas.getWidth() < this.canvas.getHeight()) ? this.canvas.getWidth() : this.canvas.getHeight() - 20);
	}
}