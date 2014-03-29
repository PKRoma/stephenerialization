/**
 * Copyright (C) 2012 Stephen M. Hopper
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.enragedginger.stephenerialization.sandwich;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.enragedginger.stephenerialization.StephenerializationLookupService;
import com.enragedginger.stephenerialization.StephenerializationService;
import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.annotations.Stephenerialize;

/**
 * Test class. Represents a Sandwich.
 * @author Stephen Hopper
 *
 */
@Stephenerializable(version=20121001)
public class Sandwich implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Stephenerialize(minVersion=20120926, priority=1)
	private int slicesOfBread;
	
	@Stephenerialize(minVersion=20120926, priority=2)
	private byte sesameSeeds;
	
	@Stephenerialize(minVersion=20120927, priority=1)
	private short slicesOfMeat;
	
	@Stephenerialize(minVersion=20120927, priority=2)
	private long molecules;
	
	@Stephenerialize(minVersion=20120928, priority=1)
	private double price;
	
	@Stephenerialize(minVersion=20120928, priority=2)
	private char size;
	
	@Stephenerialize(minVersion=20121001, priority=1)
	private String name;
	
	/**
	 * @return the slicesOfBread
	 */
	public int getSlicesOfBread() {
		return slicesOfBread;
	}

	/**
	 * @param slicesOfBread the slicesOfBread to set
	 */
	public void setSlicesOfBread(int slicesOfBread) {
		this.slicesOfBread = slicesOfBread;
	}

	/**
	 * @return the sesameSeeds
	 */
	public byte getSesameSeeds() {
		return sesameSeeds;
	}

	/**
	 * @param sesameSeeds the sesameSeeds to set
	 */
	public void setSesameSeeds(byte sesameSeeds) {
		this.sesameSeeds = sesameSeeds;
	}

	/**
	 * @return the slicesOfMeat
	 */
	public short getSlicesOfMeat() {
		return slicesOfMeat;
	}

	/**
	 * @param slicesOfMeat the slicesOfMeat to set
	 */
	public void setSlicesOfMeat(short slicesOfMeat) {
		this.slicesOfMeat = slicesOfMeat;
	}

	/**
	 * @return the molecules
	 */
	public long getMolecules() {
		return molecules;
	}

	/**
	 * @param molecules the molecules to set
	 */
	public void setMolecules(long molecules) {
		this.molecules = molecules;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the size
	 */
	public char getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(char size) {
		this.size = size;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Writes this object out to the stream using Stephenerialization.
	 * @param streamer The output stream to use.
	 */
	private void writeObject(ObjectOutputStream streamer) {
        SandwichStephenerializer.stephenerialize(this, streamer);
//		final StephenerializationService service = StephenerializationLookupService.lookup();
//		service.stephenerialize(this, streamer, Sandwich.class);
	}
	
	/**
	 * Reads this object from the stream using Stephenerialization.
	 * @param streamer The input stream to use.
	 */
	private void readObject(ObjectInputStream streamer) throws Exception {
        SandwichStephenerializer.destephenerialize(this, streamer);
//		final StephenerializationService service = StephenerializationLookupService.lookup();
//		service.destephenerialize(this, streamer, Sandwich.class);
	}
	
}
