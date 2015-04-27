package de.gymolching.fsb.api;

public class Position
{
	private int length1;
	private int length2;
	private int length3;
	private int length4;
	private int length5;
	private int length6;
	
	public Position(int length1, int length2, int length3, int length4, int length5, int length6)
	{
		this.length1 = length1;
		this.length2 = length2;
		this.length3 = length3;
		this.length4 = length4;
		this.length5 = length5;
		this.length6 = length6;
	}
	
	public int getLength1()
	{
		return this.length1;
	}
	
	public int getLength2()
	{
		return this.length2;
	}
	
	public int getLength3()
	{
		return this.length3;
	}
	
	public int getLength4()
	{
		return this.length4;
	}
	
	public int getLength5()
	{
		return this.length5;
	}
	
	public int getLength6()
	{
		return this.length6;
	}
	
	public String toString()
	{
		return this.length1 + ":" + this.length2 + ":" + this.length3 + ":" + this.length4 + ":" + this.length5 + ":" + this.length6;
	}
}
