package lsystem;

import java.util.Arrays;

public class LSystem {
	private String axiom;
	private Rule[] rules;
	private int order = 0;
	
	public LSystem(String axiom, Rule...rules) {
		this(0, axiom, rules);
	}
	
	public LSystem(int order, String axiom, Rule... rules) {
		this.axiom = axiom;
		this.rules = Arrays.copyOf(rules, rules.length);
		this.setOrder(order);
	}
	
	public void setOrder(int order) {
		if(order < 0) this.order = 0;
		else this.order = order;
	}
	
	public int getOrder() {
		return this.order;
	}
	
	public String generate() {
		String currentString = this.axiom;
		StringBuilder nextString = new StringBuilder();
		for(int i = 1; i <= this.order; i++) {
			for(int j = 0; j < currentString.length(); j++) {
				boolean found = false;
				for(Rule r : this.rules) {
					if(r.getA().charAt(0) == currentString.charAt(j)) {
						nextString.append(r.getB());
						found = true;
						break;
					}
				}
				if(!found) nextString.append(currentString.charAt(j));
			}
			currentString = nextString.toString();
			nextString = new StringBuilder();
		}
		return currentString;
	}
}