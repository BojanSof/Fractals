package fractals;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import lsystem.LSystem;
import lsystem.Rule;

public class HilbertCurve extends LSystemFractal {
	
	private final static LSystem HilbertCurveLSystem = new LSystem("X", new Rule("X", "-YF+XFX+FY-"), new Rule("Y", "+XF-YFY-FX+"));
	
	public HilbertCurve(Canvas canvas) {
		this(0, canvas);
	}
	
	public HilbertCurve(int order, Canvas canvas) {
		super(order, canvas, HilbertCurveLSystem);
		this.setAngle(90);
		this.setStartAngle(0);
	}
	
	public HilbertCurve(int order, Canvas canvas, int width, int height) {
		super(order, canvas, HilbertCurveLSystem, width, height);
		this.setAngle(90);
		this.setStartAngle(0);
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		return new HilbertCurve(this.order, canvas);
	}
	
	@Override
	public void draw() {
		super.draw();
		double smaller = this.canvas.getWidth() < this.canvas.getHeight() ? this.canvas.getWidth() : this.canvas.getHeight(); 
		double startX = (this.canvas.getWidth() - smaller) / 2 + 20;
		double startY = (this.canvas.getHeight() - smaller) / 2 + 20;
		this.turtle.setPosition(startX, startY);
		this.turtle.setStroke(Color.BLACK);
		this.turtle.setLineWidth(1.0);
		this.setLength((smaller - 40) / (Math.pow(2, this.order) - 1));
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