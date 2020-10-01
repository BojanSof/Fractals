package utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ComplexBigDecimal implements Cloneable, Comparable<ComplexBigDecimal> {

	private BigDecimal real;
	private BigDecimal imag;

	private static int scale = 10;

	public ComplexBigDecimal() {
		this(	
				BigDecimal.ZERO.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP), 
				BigDecimal.ZERO.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP)
			);
	}

	public ComplexBigDecimal(double real) {
		this(real, 0.0);
	}

	public ComplexBigDecimal(double real, double imag) {
		this.real = BigDecimal.valueOf(real).setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		this.imag = BigDecimal.valueOf(imag).setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
	}

	public ComplexBigDecimal(BigDecimal real, BigDecimal imag) {
		this.real = real.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		this.imag = imag.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
	}

	public ComplexBigDecimal(ComplexBigDecimal c) {
		this(c.real, c.imag);
	}

	public BigDecimal real() {
		return this.real;
	}

	public BigDecimal imag() {
		return this.imag;
	}

	public ComplexBigDecimal add(ComplexBigDecimal c) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = (this.real.add(c.real)).setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		result.imag = (this.imag.add(c.imag)).setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		return result;
	}

	public ComplexBigDecimal subtract(ComplexBigDecimal c) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = (this.real.subtract(c.real)).setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		result.imag = (this.imag.subtract(c.imag)).setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		return result;
	}

	public ComplexBigDecimal multiply(ComplexBigDecimal c) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = ((this.real.multiply(c.real)).subtract(this.imag.multiply(c.imag)))
						.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		result.imag = ((this.real.multiply(c.imag)).add(this.imag.multiply(c.real)))
						.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		return result;
	}

	public ComplexBigDecimal divide(ComplexBigDecimal c) {
		ComplexBigDecimal result = new ComplexBigDecimal();
		result.real = (((this.real.multiply(c.real)).add(this.imag.multiply(c.imag))).divide((c.real.multiply(c.real)).add(c.imag.multiply(c.imag)), RoundingMode.HALF_UP))
						.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		result.imag = (((this.imag.multiply(c.real)).subtract(this.real.multiply(c.imag))).divide((c.real.multiply(c.real)).add(c.imag.multiply(c.imag)), RoundingMode.HALF_UP))
						.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		return result;
	}

	public BigDecimal absSquared() {
		BigDecimal squared = ((this.real.multiply(this.real)).add(this.imag.multiply(this.imag)))
				.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		return squared;
	}
	
	public BigDecimal abs() {
		BigDecimal squared = ((this.real.multiply(this.real)).add(this.imag.multiply(this.imag)))
								.setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		BigDecimal modulus = squared.sqrt(MathContext.DECIMAL128).setScale(ComplexBigDecimal.scale, RoundingMode.HALF_UP);
		return modulus;
	}

	@Override
	public String toString() {
		return this.real.toString() + " + " + this.imag.toString() + "i";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			return super.clone();
		} catch(CloneNotSupportedException ex) {
			return null;
		}
	}

	@Override
	public boolean equals(Object o) {
		return ((this.real.equals(((ComplexBigDecimal)o).real)) && (this.imag.equals(((ComplexBigDecimal)o).imag)));
	}

	@Override
	public int compareTo(ComplexBigDecimal c) {
		return (this.abs()).compareTo(c.abs());
	}

	public static void setScale(int scale) {
		ComplexBigDecimal.scale = (scale < 2) ? 2 : scale;
	}

	public static int getScale() {
		return ComplexBigDecimal.scale;
	}
}