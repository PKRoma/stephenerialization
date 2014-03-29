package com.enragedginger.stephenerialization.benching;

import java.io.Serializable;

/**
 * Describes a basic Java object for benchmarking Stephenerialization.
 * @author Stephen Hopper
 *
 */
public interface BasicPojo extends Serializable {
	
	/**
	 * @param someBoolean the someBoolean to set
	 */
	void setSomeBoolean(boolean someBoolean);
	
	/**
	 * @param someInt the someInt to set
	 */
	void setSomeInt(int someInt);
	
	/**
	 * @param someLong the someLong to set
	 */
	void setSomeLong(long someLong);
	
	/**
	 * @param someFloat the someFloat to set
	 */
	void setSomeFloat(float someFloat);
	
	/**
	 * @param someDouble the someDouble to set
	 */
	void setSomeDouble(double someDouble);

}
