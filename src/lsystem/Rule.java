package lsystem;

public class Rule {
	private String a;
	private String b;
	
	public Rule(String a, String b) {
		this.a = a;
		this.b = b;
	}
	
	public void setA(String a) {
		this.a = a;
	}
	
	public void setB(String b) {
		this.b = b;
	}
	
	public String getA() {
		return this.a;
	}
	
	public String getB() {
		return this.b;
	}
}