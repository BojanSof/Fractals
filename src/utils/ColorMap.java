package utils;

import java.util.Arrays;

import javafx.scene.paint.Color;

public class ColorMap {
	
	private final int length;
	private int offset;
	private int[] controlPoints;
	private Color[] controlColors;
	private Color[] colors;
	private ColorSpace space;
	private ColormapType type;
	
	private static final String[] colorMapsNames = new String[] {"Ultra Fractal", "Dark Blue", "Rainbow", "Blue Spectrum", "Sky"};
	
	public static ColorMap ULTRA_FRACTAL = new ColorMap(	
															2048, 
															new double[] {0.0, 0.16, 0.42, 0.6425, 0.8575, 1.0}, 
															new Color[] {Color.rgb(0, 7, 100), Color.rgb(32, 107, 203), Color.rgb(237, 255, 255), Color.rgb(255, 170, 0), Color.rgb(0, 2, 0), Color.rgb(0, 0, 0)},
															ColorSpace.RGB, ColormapType.LINEAR_GRADIENT
														);
	
	public static ColorMap DARK_BLUE = ColorMap.linearGradient(	
																256, 
																new double[] {0.0, 0.5, 1.0}, 
																new Color[] {Color.rgb(0, 0, 128), Color.rgb(0, 255, 255), Color.rgb(0, 128, 0)}, 
																ColorSpace.RGB
															);
	
	public static ColorMap RAINBOW = new ColorMap(
														256,
														Color.hsb(0.0, 1.0, 1.0),
														Color.hsb(359.0, 1.0, 1.0),
														ColorSpace.HSB, ColormapType.LINEAR_GRADIENT
													);
	
	public static ColorMap BLUE_SPECTRUM = new ColorMap(
													1000, 0, 
													new double[] {0.0, 0.15, 0.33, 0.67, 0.85, 1.0}, 
													new Color[] {Color.color(1.0, 1.0, 1.0), Color.color(1.0, 0.8, 0.0), Color.color(0.53, 0.12, 0.075), Color.color(0.0, 0.0, 0.6), Color.color(0.0, 0.4, 1.0), Color.color(1.0, 1.0, 1.0)},
													ColorSpace.RGB, ColormapType.LINEAR_GRADIENT
												);
	   
	public static ColorMap SKY = new ColorMap(
													250, 0.90, 
													new double[] {0.0, 0.1667, 0.3333, 0.5, 0.6667, 0.8333, 1.0}, 
													new Color[] {Color.color(0.7909, 0.9961, 0.763), Color.color(0.8974, 0.8953, 0.6565), Color.color(0.9465, 0.3161, 0.1267), Color.color(0.5184, 0.1109, 0.0917), Color.color(0.0198, 0.4563, 0.6839), Color.color(0.5385, 0.8259, 0.8177), Color.color(0.7909, 0.9961, 0.763)},
													ColorSpace.RGB, ColormapType.LINEAR_GRADIENT
												);
	
	private static final ColorMap[] colorMaps = new ColorMap[] {ULTRA_FRACTAL, DARK_BLUE, RAINBOW, BLUE_SPECTRUM, SKY};
	
	public ColorMap(int length, int offset, double[] controlPoints, Color[] controlColors, ColorSpace space, ColormapType type) {
		this.length = length;
		this.offset = offset;
		this.colors = new Color[this.length];
		this.controlPoints = new int[controlPoints.length];
		for(int i = 0; i < controlPoints.length; i++) {
			this.controlPoints[i] = (int)(controlPoints[i] * this.length);
		}
		this.controlColors = Arrays.copyOf(controlColors, controlColors.length);
		this.space = space;
		this.type = type;
		switch(this.type) {
			case LINEAR_GRADIENT:
				this.linearGradient();
			break;
			default: break;
		}
	}
	
	public ColorMap(int length, double offset, double[] controlPoints, Color[] controlColors, ColorSpace space, ColormapType type) {
		this(length, (int)(offset * length), controlPoints, controlColors, space, type);
	}
	
	public ColorMap(int length, double[] controlPoints, Color[] controlColors, ColorSpace space, ColormapType type) {
		this(length, 0, controlPoints, controlColors, space, type);
	}
	
	public ColorMap(int length, int offset, Color startColor, Color endColor, ColorSpace space, ColormapType type) {
		this(length, offset, new double[] {0.0, 1.0}, new Color[] {startColor, endColor}, space, type);
	}
	
	public ColorMap(int length, Color startColor, Color endColor, ColorSpace space, ColormapType type) {
		this(length, new double[] {0.0, 1.0}, new Color[] {startColor, endColor}, space, type);
	}
	
	private void linearGradient() {
		if(this.space == ColorSpace.RGB) {
			for(int i = 0; i < this.controlPoints.length - 1; i++) {
				double[] step = new double[3];
				int width = this.controlPoints[i + 1] - this.controlPoints[i];
				step[0] = (this.controlColors[i + 1].getRed() - this.controlColors[i].getRed()) / width;
				step[1] = (this.controlColors[i + 1].getGreen() - this.controlColors[i].getGreen()) / width;
				step[2] = (this.controlColors[i + 1].getBlue() - this.controlColors[i].getBlue()) / width;
				for(int j = 0; j < width; j++) {
					this.colors[this.controlPoints[i] + j] = Color.color(
																			this.controlColors[i].getRed() + j * step[0], 
																			this.controlColors[i].getGreen() + j * step[1], 
																			this.controlColors[i].getBlue() + j * step[2]
																		);
				}
			}
		} else if(this.space == ColorSpace.HSB) {
			for(int i = 0; i < this.controlPoints.length - 1; i++) {
				double[] step = new double[3];
				int width = this.controlPoints[i + 1] - this.controlPoints[i];
				step[0] = (this.controlColors[i + 1].getHue() - this.controlColors[i].getHue()) / width;
				step[1] = (this.controlColors[i + 1].getSaturation() - this.controlColors[i].getSaturation()) / width;
				step[2] = (this.controlColors[i + 1].getBrightness() - this.controlColors[i].getBrightness()) / width;
				for(int j = 0; j < width; j++) {
					this.colors[this.controlPoints[i] + j] = Color.hsb(
																			this.controlColors[i].getHue() + j * step[0], 
																			this.controlColors[i].getSaturation() + j * step[1], 
																			this.controlColors[i].getBrightness() + j * step[2]
																		);
				}
			}
		}
	}
	
	public static ColorMap colorMap(int length, int offset, double[] controlPoints, Color[] controlColors, ColorSpace space, ColormapType type) {
		return new ColorMap(length, offset, controlPoints, controlColors, space, type);
	}
	
	public static ColorMap colorMap(int length, double[] controlPoints, Color[] controlColors, ColorSpace space, ColormapType type) {
		return new ColorMap(length, controlPoints, controlColors, space, type);
	}
	
	public static ColorMap colorMap(int length, int offset, Color startColor, Color endColor, ColorSpace space, ColormapType type) {
		return new ColorMap(length, offset, startColor, endColor, space, type);
	}
	
	public static ColorMap colorMap(int length, Color startColor, Color endColor, ColorSpace space, ColormapType type) {
		return new ColorMap(length, startColor, endColor, space, type);
	}
	
	public static ColorMap linearGradient(int length, int offset, double[] controlPoints, Color[] controlColors, ColorSpace space) {
		return new ColorMap(length, offset, controlPoints, controlColors, space, ColormapType.LINEAR_GRADIENT);
	}
	
	public static ColorMap linearGradient(int length, double[] controlPoints, Color[] controlColors, ColorSpace space) {
		return new ColorMap(length, controlPoints, controlColors, space, ColormapType.LINEAR_GRADIENT);
	}
	
	public static ColorMap linearGradient(int length, int offset, Color startColor, Color endColor, ColorSpace space) {
		return new ColorMap(length, offset, startColor, endColor, space, ColormapType.LINEAR_GRADIENT);
	}
	
	public static ColorMap linearGradient(int length, Color startColor, Color endColor, ColorSpace space) {
		return new ColorMap(length, startColor, endColor, space, ColormapType.LINEAR_GRADIENT);
	}
	
	public Color getColor(int index) {
		return this.colors[(index + this.offset) % this.length];
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getOffset() {
		return this.offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ColorMap) {
			ColorMap colorMap = (ColorMap)obj;
			if(this.length == colorMap.length && this.offset == colorMap.offset && this.space == colorMap.space && this.type == colorMap.type) {
				if(this.controlPoints.length != colorMap.controlPoints.length) return false;
				for(int i = 0; i < this.controlPoints.length; i++) {
					if(this.controlPoints[i] != colorMap.controlPoints[i]) return false;
					if(!this.controlColors[i].equals(colorMap.controlColors[i])) return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public static String[] getColorMapsNames() {
		return ColorMap.colorMapsNames;
	}
	
	public static ColorMap[] getColorMaps() {
		return ColorMap.colorMaps;
	}
	
	public static int indexOfColorMap(ColorMap colorMap) {
		for(int i = 0; i < ColorMap.colorMaps.length; i++)
			if(colorMap == ColorMap.colorMaps[i])
				return i;
		return -1;
	}
}