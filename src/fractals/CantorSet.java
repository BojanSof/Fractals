package fractals;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CantorSet extends Fractal {
	
	private final int thickness = 30;
	private final int spacing = 30;
	
	public CantorSet(Canvas canvas) {
		super(canvas);
	}
	
	public CantorSet(int order, Canvas canvas) {
		super(order, canvas);
	}
	
	public CantorSet(int order, Canvas canvas, int width, int height) {
		super(order, canvas, width, height);
	}
	
	private void draw(int order, Point2D startPoint, double length) {
		gc.fillRect(startPoint.getX(), startPoint.getY(), length, thickness);
		if(order <= 0) return;
		draw(order - 1, new Point2D(startPoint.getX(), startPoint.getY() + thickness + spacing), length / 3);
		draw(order - 1, new Point2D(startPoint.getX() + 2 * length / 3, startPoint.getY() + thickness + spacing), length / 3);
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		return new CantorSet(this.order, canvas);
	}
	
	@Override
	public void draw() {
		super.draw();
		Point2D startPoint = new Point2D(20, 50);
		double length = this.canvas.getWidth() - 40;
		gc.setFill(Color.BLACK);
		draw(this.order, startPoint, length);
	}
}