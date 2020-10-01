package fractals;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class SierpinskiTriangle extends Fractal {
	
	private boolean filled = false;
	
	public SierpinskiTriangle(Canvas canvas) {
		super(canvas);
	}
	
	public SierpinskiTriangle(int order, Canvas canvas) {
		super(order, canvas);
	}
	
	public SierpinskiTriangle(int order, Canvas canvas, boolean filled) {
		super(order, canvas);
		this.filled = filled;
		this.draw();
	}
	
	public SierpinskiTriangle(int order, Canvas canvas, int width, int height) {
		super(order, canvas, width, height);
	}
	
	public void setFilled(boolean filled) {
		this.filled = filled;
		this.draw();
	}
	
	public boolean getFilled() {
		return this.filled;
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		return new SierpinskiTriangle(this.order, canvas, this.filled);
	}
	
	private void draw(int order, Point2D p1, Point2D p2, Point2D p3) {
		if(order == 0) {
			if(this.filled) {
				gc.setFill(Color.BLACK);
				gc.fillPolygon(new double[] {p1.getX(),  p2.getX(),  p3.getX()}, new double[] {p1.getY(),  p2.getY(),  p3.getY()}, 3);
			} else {
				gc.setStroke(Color.BLACK);
				gc.setLineWidth(1.0);
				gc.strokePolygon(new double[] {p1.getX(),  p2.getX(),  p3.getX()}, new double[] {p1.getY(),  p2.getY(),  p3.getY()}, 3);
			}
		} else {
			Point2D p12 = p1.midpoint(p2);
			Point2D p23 = p2.midpoint(p3);
			Point2D p31 = p3.midpoint(p1);
			draw(order - 1, p1, p12, p31);
			draw(order - 1, p12, p2, p23);
			draw(order - 1, p31, p23, p3);
			//draw(0, p12, p23, p31);
		}
	}
	
	@Override
	public void draw() {
		super.draw();
		Point2D p1 = new Point2D(this.canvas.getWidth() / 2, 10);
		Point2D p2 = new Point2D(10, this.canvas.getHeight() - 10);
		Point2D p3 = new Point2D(this.canvas.getWidth() - 10, this.canvas.getHeight() - 10);
		draw(this.order, p1, p2, p3);
	}
}