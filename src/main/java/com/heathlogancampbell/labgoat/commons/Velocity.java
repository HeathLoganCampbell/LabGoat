package com.heathlogancampbell.labgoat.commons;

public class Velocity {
	private double x, y;

	public Velocity(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void increaseX(double amount)
	{
		this.x += amount;
	}
	
	public void increaseY(double amount)
	{
		this.y += amount;
	}

	public void clear()
	{
		this.x = 0;
		this.y = 0;
	}

	public void friction(float xFriction, float yFriction)
	{
		this.x *= (1 - xFriction);
		this.y *= (1 - yFriction);

		if(x < 0.08 && x > -0.08) this.x = 0;
		if(y < 0.08 && y > -0.08) this.y = 0;
	}
}