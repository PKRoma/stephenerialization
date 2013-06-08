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
package com.enragedginger.stephenerialization;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.sandwich.Sandwich;

/**
 * Class for testing Stephenerializing and Destephenerializing of 
 * the {@link Sandwich} class.
 * @author Stephen Hopper
 *
 */
public class SandwichStephenerializerIT {
	
	private static final Class<?> sandwichClass = Sandwich.class;
	private static final String className = sandwichClass.getSimpleName();
	
	private Sandwich sandwich;

	/**
	 * Run setup prior to each test.
	 */
	@Before
	public void setup() {
		sandwich = new Sandwich();
		sandwich.setMolecules(8675309L);
		sandwich.setName("Turkey Club");
		sandwich.setPrice(5.99);
		sandwich.setSesameSeeds((byte) 30);
		sandwich.setSize('M');
		sandwich.setSlicesOfBread(2);
		sandwich.setSlicesOfMeat((short) 8);
	}
	
	/**
	 * Test the stephenerializing and destephenerializing of the current version.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void readWriteCurrentVersion() throws IOException, ClassNotFoundException {
		Stephenerializable annotation = sandwichClass.getAnnotation(Stephenerializable.class);
		String version = String.valueOf(annotation.version());
		ObjectOutputStream output = TestStreamBuilder.createOutputStream(className, version);
		output.writeObject(sandwich);
		output.close();
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		Sandwich actualSandwich = (Sandwich) input.readObject();
		input.close();
		assertEquals(sandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(sandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(sandwich.getSlicesOfMeat(), actualSandwich.getSlicesOfMeat());
		assertEquals(sandwich.getMolecules(), actualSandwich.getMolecules());
		assertEquals(sandwich.getPrice(), actualSandwich.getPrice(), 0.0);
		assertEquals(sandwich.getSize(), actualSandwich.getSize());
		assertEquals(sandwich.getName(), actualSandwich.getName());
	}
	
	@Test
	public void readVersion20120926() throws Exception {
		String version = "20120926";
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		Sandwich actualSandwich = (Sandwich) input.readObject();
		input.close();
		assertEquals(sandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(sandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(0, actualSandwich.getSlicesOfMeat());
		assertEquals(0, actualSandwich.getMolecules());
		assertEquals(0, actualSandwich.getPrice(), 0.0);
		assertEquals(0, actualSandwich.getSize());
		assertEquals(null, actualSandwich.getName());
	}
	
	@Test
	public void readVersion20120927() throws Exception {
		String version = "20120927";
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		Sandwich actualSandwich = (Sandwich) input.readObject();
		input.close();
		assertEquals(sandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(sandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(sandwich.getSlicesOfMeat(), actualSandwich.getSlicesOfMeat());
		assertEquals(sandwich.getMolecules(), actualSandwich.getMolecules());
		assertEquals(0, actualSandwich.getPrice(), 0.0);
		assertEquals(0, actualSandwich.getSize());
		assertEquals(null, actualSandwich.getName());
	}
	
	@Test
	public void readVersion20120928() throws Exception {
		String version = "20120928";
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		Sandwich actualSandwich = (Sandwich) input.readObject();
		input.close();
		assertEquals(sandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(sandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(sandwich.getSlicesOfMeat(), actualSandwich.getSlicesOfMeat());
		assertEquals(sandwich.getMolecules(), actualSandwich.getMolecules());
		assertEquals(sandwich.getPrice(), actualSandwich.getPrice(), 0.0);
		assertEquals(sandwich.getSize(), actualSandwich.getSize());
		assertEquals(null, actualSandwich.getName());
	}
	
	@Test
	public void readVersion20121001() throws Exception {
		String version = "20121001";
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		Sandwich actualSandwich = (Sandwich) input.readObject();
		input.close();
		assertEquals(sandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(sandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(sandwich.getSlicesOfMeat(), actualSandwich.getSlicesOfMeat());
		assertEquals(sandwich.getMolecules(), actualSandwich.getMolecules());
		assertEquals(sandwich.getPrice(), actualSandwich.getPrice(), 0.0);
		assertEquals(sandwich.getSize(), actualSandwich.getSize());
		assertEquals(sandwich.getName(), actualSandwich.getName());
	}

}
