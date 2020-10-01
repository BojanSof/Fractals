package utils;

public class Complex implements Cloneable, Comparable<Complex> {
	private double realPart;
	private double imaginaryPart;

	public Complex() {
		this(0, 0);
	}

	public Complex(double realPart) {
		this(realPart, 0);
	}

	public Complex(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
	}

	public Complex(Complex c) {
		this(c.realPart, c.imaginaryPart);
	}

	public double getRealPart() {
		return this.realPart;
	}

	public double getImaginaryPart() {
		return this.imaginaryPart;
	}

	public Complex add(Complex c) {
		return new Complex(this.realPart + c.realPart, this.imaginaryPart + c.imaginaryPart);
	}

	public Complex subtract(Complex c) {
		return new Complex(this.realPart - c.realPart, this.imaginaryPart - c.imaginaryPart);
	}

	public Complex multiply(Complex c) {
		return new Complex(	
							this.realPart * c.realPart - this.imaginaryPart * c.imaginaryPart, 
							this.realPart * c.imaginaryPart + this.imaginaryPart * c.realPart
						);
	}

	public Complex divide(Complex c) {
		return new Complex(	
							(this.realPart * c.realPart + this.imaginaryPart * c.imaginaryPart) / (Math.pow(c.realPart, 2) + Math.pow(c.imaginaryPart, 2)), 
							(this.imaginaryPart * c.realPart - this.realPart * c.imaginaryPart) / (Math.pow(c.realPart, 2) + Math.pow(c.imaginaryPart, 2)) 
						);
	}

	public double abs() {
		return Math.sqrt(Math.pow(this.realPart, 2) + Math.pow(this.imaginaryPart, 2));
	}

	@Override
	public String toString() {
		return this.realPart + " + " + this.imaginaryPart + "i";
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
		return ((this.realPart == ((Complex)o).realPart) && (this.imaginaryPart == ((Complex)o).imaginaryPart));
	}

	@Override
	public int compareTo(Complex c) {
		if(this.abs() > c.abs())
			return 1;
		else if(this.abs() < c.abs())
			return -1;
		else
			return 0;
	}
}