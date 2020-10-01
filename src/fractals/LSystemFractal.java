package fractals;

import java.util.Stack;

import javafx.scene.canvas.Canvas;
import lsystem.LSystem;
import lsystem.Turtle;

public class LSystemFractal extends Fractal {
	
	protected LSystem lsystem;
	protected Turtle turtle;
	private Stack<Double> state = new Stack<>();
	
	public LSystemFractal(Canvas canvas, LSystem lsystem) {
		this(0, canvas, lsystem);
	}
	
	public LSystemFractal(int order, Canvas canvas, LSystem lsystem) {
		this(order, canvas, lsystem, (int)canvas.getWidth(), (int)canvas.getHeight());
	}
	
	public LSystemFractal(Canvas canvas, LSystem lsystem, double length, double angle, double startAngle, double startX, double startY) {
		this(0, canvas, lsystem, length, angle, startAngle, startX, startY);
	}
	
	public LSystemFractal(int order, Canvas canvas, LSystem lsystem, double length, double angle, double startAngle, double startX, double startY) {
		this(order, canvas, lsystem, (int)canvas.getWidth(), (int)canvas.getHeight(), length, angle, startAngle, startX, startY);
	}

	public LSystemFractal(int order, Canvas canvas, LSystem lsystem, int width, int height) {
		this.canvas = canvas;
		this.canvas.setWidth(width);
		this.canvas.setHeight(height);
		this.gc = this.canvas.getGraphicsContext2D();
		this.lsystem = lsystem;
		this.turtle = new Turtle(canvas);
		this.setOrder(order);
	}
	
	public LSystemFractal(int order, Canvas canvas, LSystem lsystem, int width, int height, double length, double angle, double startAngle, double startX, double startY) {
		this.canvas = canvas;
		this.canvas.setWidth(width);
		this.canvas.setHeight(height);
		this.gc = this.canvas.getGraphicsContext2D();
		this.lsystem = lsystem;
		this.turtle = new Turtle(this.canvas, length, angle, startAngle, startX, startY);
		this.setOrder(order);
	}
	
	@Override
	public void setOrder(int order) {
		this.order = (order < 0) ? 0 : order;
		this.lsystem.setOrder(this.order);
		this.draw();
	}
	
	public void setAngle(double angle) {
		this.turtle.setAngle(angle);
	}
	
	public void setStartAngle(double startAngle) {
		this.turtle.setStartAngle(startAngle);
	}
	
	public void setLength(double length) {
		this.turtle.setLength(length);
	}
	
	public double getAngle() {
		return this.turtle.getAngle();
	}
	
	public double getStartAngle() {
		return this.turtle.getStartAngle();
	}
	
	public double getLength() {
		return this.turtle.getLength();
	}
	
	public void saveState() {
		state.push(this.turtle.getX());
		state.push(this.turtle.getY());
		state.push(this.turtle.getCurrentAngle());
		state.push(this.turtle.getLength());
	}
	
	public void restoreState() {
		if(state.size() < 4) return;
		this.turtle.setLength(state.pop());
		this.turtle.setCurrentAngle(state.pop());
		this.turtle.setY(state.pop());
		this.turtle.setX(state.pop());
	}
	
	@Override
	public void draw() {
		this.turtle.reset();
		super.draw();
	}
}