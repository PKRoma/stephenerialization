package com.enragedginger.stephenerialization.cyclic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import com.enragedginger.stephenerialization.TestStreamBuilder;
import com.enragedginger.stephenerialization.annotations.Stephenerializable;

/**
 * Class for testing cyclical Stephenerialization.
 * @author Stephen Hopper
 *
 */
public class CyclicStephenerializerTest {
	
	private CyclicA cyclicA;
	private CyclicB cyclicB;
	
	@Before
	public void setUp() {
		cyclicA = new CyclicA();
		cyclicB = new CyclicB();
		cyclicA.setCyclic(cyclicB);
		cyclicB.setCyclic(cyclicA);
	}
	
	@Test
	public void testReadWrite() throws IOException, ClassNotFoundException {
		Class<?> cyclicAClass = CyclicA.class;
		String className = cyclicAClass.getSimpleName();
		Stephenerializable annotation = cyclicAClass.getAnnotation(Stephenerializable.class);
		String version = String.valueOf(annotation.version());
		ObjectOutputStream output = TestStreamBuilder.createOutputStream(className, version);
		output.writeObject(cyclicA);
		output.close();
		ObjectInputStream input = TestStreamBuilder.createInputStream(className, version);
		CyclicA actualCyclicA = (CyclicA) input.readObject();
		input.close();
	}

}
