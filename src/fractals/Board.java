package fractals;

import javafx.scene.canvas.Canvas;
import lsystem.LSystem;
import lsystem.Rule;

public class Board extends LSystemFractal {
	
	private final static LSystem BoardLSystem = new LSystem("F+F+F+F", new Rule("F", "FF+F+F+F+FF"));
	
	public Board(Canvas canvas) {
		this(0, canvas);
	}
	
	public Board(int order, Canvas canvas) {
		super(order, canvas, BoardLSystem);
		this.setAngle(90);
		this.setStartAngle(0);
	}
	
	public Board(int order, Canvas canvas, int width, int height) {
		super(order, canvas, BoardLSystem, width, height);
		this.setAngle(90);
		this.setStartAngle(0);
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		return new Board(this.order, canvas);
	}
	
	@Override
	public void draw() {
		super.draw();
		double smaller = this.canvas.getWidth() < this.canvas.getHeight() ? this.canvas.getWidth() : this.canvas.getHeight(); 
		double startX = (this.canvas.getWidth() - smaller) / 2 + 20;
		double startY = this.canvas.getHeight() - ((this.canvas.getHeight() - smaller) / 2 + 20);
		this.turtle.setPosition(startX, startY);
		this.turtle.setLineWidth(1.0);
		this.setLength((smaller - 40) / (Math.pow(3, this.order)));
		String str = this.lsystem.generate();
		for(int i = 0; i < str.length(); i++) {
			switch(str.charAt(i)) {
				case 'F':
					this.turtle.drawLine();
				break;
				case '+':
					this.turtle.rotateLeft();
				break;
				case '-':
					this.turtle.rotateRight();
				break;
				default: break;
			}
		}
	}
}