package fractals;

import java.util.Arrays;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Fractal {
	
	private static final String[] fractalsList = new String[] {"Board", "Cantor Set", "Crystal", "Hilbert Curve", "HTree", "Koch Snowflake", "Mandelbrot Set", "Peano Curve", "Sierpinski Carpet", "Sierpinski Triangle", "Tree"};
	
	protected int order = 0;
	protected Canvas canvas;
	protected GraphicsContext gc;
	
	protected Fractal() {}
	
	public Fractal(Canvas canvas) {
		this(0, canvas, (int)canvas.getWidth(), (int)canvas.getHeight());
	}
	
	public Fractal(int order, Canvas canvas) {
		this(order, canvas, (int)canvas.getWidth(), (int)canvas.getHeight());
	}
	
	public Fractal(int order, Canvas canvas, int width, int height) {
		this.canvas = canvas;
		this.canvas.setWidth(width);
		this.canvas.setHeight(height);
		this.gc = this.canvas.getGraphicsContext2D();
		this.setOrder(order);
	}
	
	public Fractal(Fractal fractal, Canvas canvas) {
		this.order = fractal.order;
		this.canvas = canvas;
		this.gc = this.canvas.getGraphicsContext2D();
	}
	
	public void setOrder(int order) {
		this.order = (order < 0) ? 0 : order;
		this.draw();
	}
	
	public int getOrder() {
		return this.order;
	}
	
	public void draw() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public static String[] getFractalsList() {
		Arrays.sort(Fractal.fractalsList);
		return Fractal.fractalsList;
	}
	
	public Fractal copy(Canvas canvas) {
		return new Fractal(this.order, canvas);
	}
}