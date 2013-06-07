package com.enragedginger.stephenerialization.benching;

/**
 * Basic Pojo for serializing.
 * @author Stephen Hopper
 *
 */
public class SimpleSerializablePojo implements BasicPojo {
	
	private static final long serialVersionUID = 1L;

	private boolean someBoolean;
	private int someInt;
	private long someLong;
	private float someFloat;
	private double someDouble;
	
	/**
	 * @return the someBoolean
	 */
	public boolean isSomeBoolean() {
		return someBoolean;
	}
	/**
	 * @param someBoolean the someBoolean to set
	 */
	public void setSomeBoolean(boolean someBoolean) {
		this.someBoolean = someBoolean;
	}
	/**
	 * @return the someInt
	 */
	public int getSomeInt() {
		return someInt;
	}
	/**
	 * @param someInt the someInt to set
	 */
	public void setSomeInt(int someInt) {
		this.someInt = someInt;
	}
	/**
	 * @return the someLong
	 */
	public long getSomeLong() {
		return someLong;
	}
	/**
	 * @param someLong the someLong to set
	 */
	public void setSomeLong(long someLong) {
		this.someLong = someLong;
	}
	/**
	 * @return the someFloat
	 */
	public float getSomeFloat() {
		return someFloat;
	}
	/**
	 * @param someFloat the someFloat to set
	 */
	public void setSomeFloat(float someFloat) {
		this.someFloat = someFloat;
	}
	/**
	 * @return the someDouble
	 */
	public double getSomeDouble() {
		return someDouble;
	}
	/**
	 * @param someDouble the someDouble to set
	 */
	public void setSomeDouble(double someDouble) {
		this.someDouble = someDouble;
	}
	
}
