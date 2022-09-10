package com.lucascram.tilegraphicsgame.math;

public class Vector2D {
	
	private double 		xComponent;
	private double		yComponent;
	
	public Vector2D() {
		this.xComponent = 0.0;
		this.yComponent = 0.0;
	}
	
	public Vector2D(int startX, int startY, int endX, int endY) {
		this.xComponent = endX - startX;
		this.yComponent = endY - startY;
	}
	
	public Vector2D(double xComponent, double yComponent) {
		this.xComponent = xComponent;
		this.yComponent = yComponent;
	}
	
	public Vector2D(Vector2D v2d) {
		this.xComponent = v2d.getXComponent();
		this.yComponent = v2d.getYComponent();
	}
	
	public double getMagnitude() {
		double xsq = xComponent * xComponent;
		double ysq = yComponent * yComponent;
		
		return Math.sqrt(xsq + ysq);
	}
	
	public Vector2D normalize() {
		double magnitude = this.getMagnitude();
		double xn = xComponent / magnitude;
		double yn = yComponent / magnitude;
		
		return new Vector2D(xn, yn);
	}
	
	public void add(double x, double y) {
		xComponent += x;
		yComponent += y;
	}
	
	public void add(Vector2D v2d) {
		xComponent += v2d.getXComponent();
		yComponent += v2d.getYComponent();
	}
	
	public void scalarMult(double scalar) {
		xComponent *= scalar;
		yComponent *= scalar;
	}
	
	public String toString() {
		return "[" + xComponent + ", " + yComponent + "]";
	}
	
	
	/*
	 * Accessors and Mutators 
	 */
	public double getXComponent() {
		return xComponent;
	}
	
	public double getYComponent() {
		return yComponent;
	}
	
	public void setXComponent(double newX) {
		xComponent = newX;
	}
	
	public void setYComponent(double newY) {
		yComponent = newY;
	}
}
