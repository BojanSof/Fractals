package fractals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.ColorMap;
import utils.Complex;

public class MandelbrotSet extends Fractal {
	
// 	DEFAULT
	private double escapeRadius = 2.0;
	private double realMin = -2.5;
	private double realMax = 1.5;
	private double imagMin = -1.5;
	private double imagMax = 1.5;
	private int iterations = 1000;
	private ColorMap colorMap = ColorMap.DARK_BLUE;
	private int[][] iterationCount;
//	EXAMPLE 1
//	private double escapeRadius = 4.0;
//	private double realMin = -1.9963806954442953;
//	private double realMax = -1.996380695443582;
//	private double imagMin = 2.628704923646517E-7;
//	private double imagMax = 2.62871027270105E-7;
//	private int iterations = 1000;
//	private ColorMap colorMap = ColorMap.SKY;
//	private int[][] iterationCount;
//	EXAMPLE 2
//	private double escapeRadius = 4.0;
//	private double realMin = 0.243815448917471504267;
//	private double realMax = 0.243815455275027059823;
//	private double imagMin = 0.555418873694459027708;
//	private double imagMax = 0.555418878462625694376;
//	private int iterations = 5000;
//	private ColorMap colorMap = ColorMap.BLUE_SPECTRUM;
//	private int[][] iterationCount;
//	EXAMPLE 3
//	private double escapeRadius = 4.0;
//	private double realMin = -1.9963806954442953;
//	private double realMax = -1.996380695443582;
//	private double imagMin = 2.628704923646517E-7;
//	private double imagMax = 2.62871027270105E-7;
//	private int iterations = 1000;
//	private ColorMap colorMap = ColorMap.SKY;
//	private int[][] iterationCount;
	
	private BooleanProperty changeProperty = new SimpleBooleanProperty(false);
	private double oldRealMin, oldRealMax, oldImagMin, oldImagMax;
	private Rectangle selection = new Rectangle();
	private Pane paneSelection = new Pane(selection);
	private Point2D startPoint;
	
	public MandelbrotSet(Canvas canvas) {
		super(canvas);
		this.initializeZoom();
	}
	
	public MandelbrotSet(Canvas canvas, int iterations, double escapeRadius, double realMin, double realMax, double imagMin, double imagMax, ColorMap cmap) {
		super(canvas);
		this.setIterations(iterations);
		this.setEscapeRadius(escapeRadius);
		this.realMin = realMin;
		this.realMax = realMax;
		this.imagMin = imagMin;
		this.imagMax = imagMax;
		this.colorMap = cmap;
		this.initializeZoom();
	}
	
	public MandelbrotSet(Canvas canvas, int width, int height) {
		super(0, canvas, width, height);
		this.initializeZoom();
	}
	
//	private void initializeZoom() {
//		
//		this.canvas.setOnMousePressed(e-> {
//			if(e.getButton() == MouseButton.PRIMARY) {
//				this.oldRealMin = this.realMin;
//				this.oldRealMax = this.realMax;
//				this.oldImagMin = this.imagMin;
//				this.oldImagMax = this.imagMax;
//				int width = (int)this.canvas.getWidth();
//				int height = (int)this.canvas.getHeight();
//				this.realMin = oldRealMin + ((oldRealMax - oldRealMin) / (width - 1)) * ((int)e.getX());
//				this.imagMin = oldImagMax - ((oldImagMax - oldImagMin) / (height - 1)) * ((int)e.getY());
//				startPoint = new Point2D(e.getX(), e.getY());
//				selection.setX(e.getX());
//				selection.setY(e.getY());
//				selection.setWidth(0.0);
//				selection.setHeight(0.0);
//				selection.setFill(Color.TRANSPARENT);
//				selection.setStroke(Color.BLACK);
//				Pane paneCanvas = (Pane)this.canvas.getParent();
//				paneSelection.setPrefSize(paneCanvas.getWidth(), paneCanvas.getHeight());
//				paneCanvas.getChildren().add(paneSelection);
//			}
//		});
//		this.canvas.setOnMouseDragged(e-> {
//			if(e.getButton() == MouseButton.PRIMARY) {
//				selection.setWidth(Math.abs(e.getX() - startPoint.getX()));
//				selection.setHeight(selection.getWidth() * (this.canvas.getHeight() / this.canvas.getWidth()));
//				selection.setX(Math.min(selection.getX(), e.getX()));
//				selection.setY(Math.min(selection.getY(), e.getY()));
//			}
//		});
//		this.canvas.setOnMouseReleased(e-> {
//			if(e.getButton() == MouseButton.PRIMARY) {
//				int width = (int)this.canvas.getWidth();
//				int height = (int)this.canvas.getHeight();
//				this.realMax = oldRealMin + ((oldRealMax - oldRealMin) / (width - 1)) * ((int)e.getX());
//				this.imagMax = oldImagMax - ((oldImagMax - oldImagMin) / (height - 1)) * ((int)e.getY());
//				if(this.realMin > this.realMax) {
//					double temp = this.realMin;
//					this.realMin = this.realMax;
//					this.realMax = temp;
//				}
//				if(this.imagMin > this.imagMax) {
//					double temp = this.imagMin;
//					this.imagMin = this.imagMax;
//					this.imagMax = temp;
//				}
//				Pane paneCanvas = (Pane)this.canvas.getParent();
//				paneCanvas.getChildren().remove(paneSelection);
//				this.draw();
//			}
//		});
//	}
	private void initializeZoom() {
		
		this.canvas.setOnMousePressed(e-> {
			if(e.getButton() == MouseButton.PRIMARY) {
				this.oldRealMin = this.realMin;
				this.oldRealMax = this.realMax;
				this.oldImagMin = this.imagMin;
				this.oldImagMax = this.imagMax;
				startPoint = new Point2D(e.getX(), e.getY());
				selection.setX(e.getX());
				selection.setY(e.getY());
				selection.setWidth(0.0);
				selection.setHeight(0.0);
				selection.setFill(Color.TRANSPARENT);
				selection.setStroke(Color.BLACK);
				Pane paneCanvas = (Pane)this.canvas.getParent();
				paneSelection.setPrefSize(paneCanvas.getWidth(), paneCanvas.getHeight());
				paneCanvas.getChildren().add(paneSelection);
			}
		});
		this.canvas.setOnMouseDragged(e-> {
			if(e.getButton() == MouseButton.PRIMARY) {
				selection.setX(Math.min(e.getX(), startPoint.getX()));
				selection.setWidth(Math.abs(e.getX() - startPoint.getX()));
				selection.setY(Math.min(e.getY(), startPoint.getY()));
				selection.setHeight(Math.abs(e.getY() - startPoint.getY()));
			}
		});
		this.canvas.setOnMouseReleased(e-> {
			if(e.getButton() == MouseButton.PRIMARY) {
				int width = (int)this.canvas.getWidth();
				int height = (int)this.canvas.getHeight();
				this.realMin = oldRealMin + ((oldRealMax - oldRealMin) / (width - 1)) * ((int)selection.getX());
				this.realMax = oldRealMin + ((oldRealMax - oldRealMin) / (width - 1)) * ((int)(selection.getX() + selection.getWidth()));
				this.imagMin = oldImagMax - ((oldImagMax - oldImagMin) / (height - 1)) * ((int)(selection.getY() + selection.getHeight()));
				this.imagMax = oldImagMax - ((oldImagMax - oldImagMin) / (height - 1)) * ((int)selection.getY());
				Pane paneCanvas = (Pane)this.canvas.getParent();
				paneCanvas.getChildren().remove(paneSelection);
				this.draw();
			}
		});
	}	

	public int getIterations() {
		return this.iterations;
	}
	
	public void setIterations(int iterations) {
		this.iterations = (iterations < 5) ? 5 : iterations;
	}
	
	public double getEscapeRadius() {
		return this.escapeRadius;
	}
	
	public void setEscapeRadius(double escapeRadius) {
		this.escapeRadius = (escapeRadius <= 0) ? 2.0 : escapeRadius;
	}
	
	public double getRealMin() {
		return this.realMin;
	}
	
	public double getRealMax() {
		return this.realMax;
	}
	
	public void setRealMin(double realMin) {
		this.realMin = realMin;
	}
	
	public void setRealMax(double realMax) {
		this.realMax = realMax;
	}
	
	public void setRealBounds(double realMin, double realMax) {
		this.realMin = realMin;
		this.realMax = realMax;
	}
	
	public double getImagMin() {
		return this.imagMin;
	}
	
	public double getImagMax() {
		return this.imagMax;
	}
	
	public void setImagMin(double imagMin) {
		this.imagMin = imagMin;
	}
	
	public void setImagMax(double imagMax) {
		this.imagMax = imagMax;
	}
	
	public void setImagBounds(double imagMin, double imagMax) {
		this.imagMin = imagMin;
		this.imagMax = imagMax;
	}
	
	public BooleanProperty getChangeProperty() {
		return this.changeProperty;
	}
	
	public ColorMap getColorMap() {
		return this.colorMap;
	}
	
	public void setColorMap(ColorMap colorMap) {
		this.colorMap = colorMap;
		this.color();
	}
	
	@Override
	public void setOrder(int order) {
		return;
	}
	
	public void color() {
		if(this.iterationCount == null) return;
		PixelWriter pixelWriter = this.gc.getPixelWriter();
		int width = (int)this.canvas.getWidth();
		int height = (int)this.canvas.getHeight();
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
//				//Smooth coloring
//				double ONE_OVER_LOG2 = 1.0 / Math.log(2);
//				double smoothed = Math.log(Math.log(z.abs()) * ONE_OVER_LOG2) * ONE_OVER_LOG2;
//				int colorIndex = (int)(Math.sqrt(n + 1 - smoothed) * 256 + 0) % this.colorMap.getLength();
//				int colorIndex = n % this.colorMap.getLength();
				int colorIndex = this.iterationCount[y][x] % this.colorMap.getLength();
				if(this.iterationCount[y][x] < this.iterations)
					pixelWriter.setColor(x, y, this.colorMap.getColor(colorIndex));
				else
					pixelWriter.setColor(x, y, Color.BLACK);
			}
		}
	}
	
	@Override
	public Fractal copy(Canvas canvas) {
		MandelbrotSet mandelbrotCopy = new MandelbrotSet(canvas, this.iterations, this.escapeRadius, this.realMin, this.realMax, this.imagMin, this.imagMax, this.colorMap);
		mandelbrotCopy.draw();
		return mandelbrotCopy;
	}
	
	@Override
	public void draw() {
		super.draw();
		int width = (int)this.canvas.getWidth();
		int height = (int)this.canvas.getHeight();
		this.iterationCount = new int[height][width];
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Complex c = new Complex(
											realMin + ((realMax - realMin) / (width - 1)) * x, 
											imagMax - ((imagMax - imagMin) / (height - 1)) * y
										);
				Complex z = new Complex(0.0, 0.0);
				int n;
				for(n = 0; n < this.iterations; n++) {
					z = (z.multiply(z)).add(c);
					if(z.abs() >= this.escapeRadius) break;
				}
				this.iterationCount[y][x] = n;
			}
		}
		this.color();
		this.changeProperty.set(true);
	}
}