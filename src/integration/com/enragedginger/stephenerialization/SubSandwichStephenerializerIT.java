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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.sandwich.SubSandwich;

public class SubSandwichStephenerializerIT {

	private static final Class<?> sandwichClass = SubSandwich.class;
	private static final String className = sandwichClass.getSimpleName();
	
	private SubSandwich subSandwich;

	/**
	 * Run setup prior to each test.
	 */
	@Before
	public void setup() {
		subSandwich = new SubSandwich();
		subSandwich.setMolecules(8675309L);
		subSandwich.setName("Turkey Club");
		subSandwich.setPrice(5.99);
		subSandwich.setSesameSeeds((byte) 30);
		subSandwich.setSize('M');
		subSandwich.setSlicesOfBread(2);
		subSandwich.setSlicesOfMeat((short) 8);
		subSandwich.setActuallyFresh(true);
		subSandwich.setLength(5.0f);
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
		output.writeObject(subSandwich);
		output.close();
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		SubSandwich actualSandwich = (SubSandwich) input.readObject();
		input.close();
		assertEquals(subSandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(subSandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(subSandwich.getSlicesOfMeat(), actualSandwich.getSlicesOfMeat());
		assertEquals(subSandwich.getMolecules(), actualSandwich.getMolecules());
		assertEquals(subSandwich.getPrice(), actualSandwich.getPrice(), 0.0);
		assertEquals(subSandwich.getSize(), actualSandwich.getSize());
		assertEquals(subSandwich.getName(), actualSandwich.getName());
		assertEquals(subSandwich.getLength(), actualSandwich.getLength(), 0.0f);
		assertEquals(subSandwich.isActuallyFresh(), actualSandwich.isActuallyFresh());
	}
	
	@Test
	public void readVersion20120926() throws Exception {
		String version = "20120926";
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		SubSandwich actualSandwich = (SubSandwich) input.readObject();
		input.close();
		assertEquals(subSandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(subSandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(0, actualSandwich.getSlicesOfMeat());
		assertEquals(0, actualSandwich.getMolecules());
		assertEquals(0, actualSandwich.getPrice(), 0.0);
		assertEquals(0, actualSandwich.getSize());
		assertEquals(null, actualSandwich.getName());
		assertEquals(subSandwich.getLength(), actualSandwich.getLength(), 0.0f);
		assertEquals(false, actualSandwich.isActuallyFresh());
	}
	
	@Test
	public void readVersion20120927() throws Exception {
		String version = "20120927";
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		SubSandwich actualSandwich = (SubSandwich) input.readObject();
		input.close();
		assertEquals(subSandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(subSandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(subSandwich.getSlicesOfMeat(), actualSandwich.getSlicesOfMeat());
		assertEquals(subSandwich.getMolecules(), actualSandwich.getMolecules());
		assertEquals(0, actualSandwich.getPrice(), 0.0);
		assertEquals(0, actualSandwich.getSize());
		assertEquals(null, actualSandwich.getName());
		assertEquals(subSandwich.getLength(), actualSandwich.getLength(), 0.0f);
		assertEquals(subSandwich.isActuallyFresh(), actualSandwich.isActuallyFresh());
	}
	
	@Test
	public void readVersion20120928() throws Exception {
		String version = "20120928";
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		SubSandwich actualSandwich = (SubSandwich) input.readObject();
		input.close();
		assertEquals(subSandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(subSandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(subSandwich.getSlicesOfMeat(), actualSandwich.getSlicesOfMeat());
		assertEquals(subSandwich.getMolecules(), actualSandwich.getMolecules());
		assertEquals(subSandwich.getPrice(), actualSandwich.getPrice(), 0.0);
		assertEquals(subSandwich.getSize(), actualSandwich.getSize());
		assertEquals(null, actualSandwich.getName());
		assertEquals(subSandwich.getLength(), actualSandwich.getLength(), 0.0f);
		assertEquals(subSandwich.isActuallyFresh(), actualSandwich.isActuallyFresh());
	}
	
	@Test
	public void readVersion20121001() throws Exception {
		String version = "20121001";
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		SubSandwich actualSandwich = (SubSandwich) input.readObject();
		input.close();
		assertEquals(subSandwich.getSlicesOfBread(), actualSandwich.getSlicesOfBread());
		assertEquals(subSandwich.getSesameSeeds(), actualSandwich.getSesameSeeds());
		assertEquals(subSandwich.getSlicesOfMeat(), actualSandwich.getSlicesOfMeat());
		assertEquals(subSandwich.getMolecules(), actualSandwich.getMolecules());
		assertEquals(subSandwich.getPrice(), actualSandwich.getPrice(), 0.0);
		assertEquals(subSandwich.getSize(), actualSandwich.getSize());
		assertEquals(subSandwich.getName(), actualSandwich.getName());
		assertEquals(subSandwich.getLength(), actualSandwich.getLength(), 0.0f);
		assertEquals(subSandwich.isActuallyFresh(), actualSandwich.isActuallyFresh());
	}

}
