package fractals;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class KochSnowflake extends Fractal {
	
	public KochSnowflake(Canvas canvas) {
		super(canvas);
	}
	
	public KochSnowflake(int order, Canvas canvas) {
		super(order, canvas);
	}
	
	public KochSnowflake(int order, Canvas canvas, int width, int height) {
		super(order, canvas, width, height);
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		return new KochSnowflake(this.order, canvas);
	}
	
	private void draw(int order, Point2D p1, Point2D p2, Point2D p3) {
		draw(order, p1, p2);
		draw(order, p2, p3);
		draw(order, p3, p1);
	}
	
	private void draw(int order, Point2D p1, Point2D p2) {
		if(order == 0) {
			gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		} else {
			//dividing segment
			Point2D p12_1 = new Point2D((2 * p1.getX() + p2.getX())/3, (2 * p1.getY() + p2.getY())/3);
			Point2D p12_2 = new Point2D((p1.getX() + 2 * p2.getX())/3, (p1.getY() + 2 * p2.getY())/3);
			//finding equilateral triangle point
			double x = p12_2.getX() - p12_1.getX(), y = p12_2.getY() - p12_1.getY(), rotationAngle = Math.toRadians(60.0);
			Point2D p12 = new Point2D(	
										p12_1.getX() + x * Math.cos(rotationAngle) - y * Math.sin(rotationAngle), 
										p12_1.getY() + y * Math.cos(rotationAngle) + x * Math.sin(rotationAngle)
									);
			
			draw(order - 1, p1, p12_1);
			draw(order - 1, p12_1, p12);
			draw(order - 1, p12, p12_2);
			draw(order - 1, p12_2, p2);
		}
	}
	
	@Override
	public void draw() {
		super.draw();
		double altitude = (this.canvas.getWidth() < this.canvas.getHeight() ? this.canvas.getWidth() : this.canvas.getHeight()) / 2 - 5.0;
		double side = 2.0 / Math.sqrt(3.0) * altitude;
		Point2D p = new Point2D(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2 + 1.0/3.0 * altitude);
		Point2D p1 = new Point2D(p.getX(), p.getY() - altitude);
		Point2D p2 = new Point2D(p.getX() - side / 2, p.getY());
		Point2D p3 = new Point2D(p.getX() + side / 2, p.getY());
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1.0);
		draw(order, p1, p2, p3);
	}
}