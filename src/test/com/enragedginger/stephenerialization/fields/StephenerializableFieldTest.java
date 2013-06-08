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
package com.enragedginger.stephenerialization.fields;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the {@link StephenerializableField} class.
 * @author Stephen Hopper
 *
 */
public class StephenerializableFieldTest {
	
	private StephenerializableField field1;
	private StephenerializableField field2;
	
	private Field testField1;
	private Field testField2;
	
	private Set<StephenerializableField> fields;
	
	private class TestFaceClass {
		private int face1;
		private float face2;
	}
	
	/**
	 * Run setup prior to each test.
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 */
	@Before
	public void setup() throws SecurityException, NoSuchFieldException {
		field1 = new StephenerializableField();
		field2 = new StephenerializableField();
		testField1 = TestFaceClass.class.getDeclaredField("face2");
		testField2 = TestFaceClass.class.getDeclaredField("face1");
		fields = new TreeSet<StephenerializableField>();
	}
	
	/**
	 * An instance should never be equal to a null.
	 */
	@Test
	public void testNotEqualsNull() {
		assertFalse(field1.equals(null));
	}
	
	/**
	 * An instance should never be equal to an object
	 * that is not a {@link StephenerializableField}.
	 */
	@Test
	public void testNotEqualsToObject() {
		assertFalse(field1.equals("face"));
	}
	
	/**
	 * Two blank {@link StephenerializableField} instances
	 * should be equal.
	 */
	@Test
	public void testEqualsToBlankObject() {
		assertTrue(field1.equals(field2));
		assertTrue(field2.equals(field1));
	}
	
	/**
	 * Two fields with different order values should not be equal.
	 */
	@Test
	public void testNotEqualsDifferentOrder() {
		field1.setOrder(3);
		field2.setOrder(5);
		assertFalse(field1.equals(field2));
		assertFalse(field2.equals(field1));
	}
	
	/**
	 * Two fields with different version values should not be equal.
	 */
	@Test
	public void testNotEqualsDifferentVersion() {
		field1.setVersion(20120926);
		field2.setVersion(20120925);
		assertFalse(field1.equals(field2));
		assertFalse(field2.equals(field1));
	}
	
	/**
	 * Test sorting by order value.
	 */
	@Test
	public void testSortByOrder() {
		field1.setOrder(5);
		field2.setOrder(3);
		assertTrue(field1.compareTo(field2) > 0);
		assertTrue(field2.compareTo(field1) < 0);
	}
	
	/**
	 * Test sorting by version value.
	 */
	@Test
	public void testSortByVersion() {
		field1.setVersion(20120926);
		field2.setVersion(20120925);
		assertTrue(field1.compareTo(field2) > 0);
		assertTrue(field2.compareTo(field1) < 0);
	}
	
	/**
	 * Test sorting by field name.
	 */
	@Test
	public void testSortByFieldName() {
		field1.setField(testField1);
		field2.setField(testField2);
		assertTrue(field1.compareTo(field2) > 0);
		assertTrue(field2.compareTo(field1) < 0);
	}
	
	/**
	 * Sort first by version, then by order.
	 */
	@Test
	public void testSortByVersionThenOrderThenName() {
		final int earlyVersion = 20120925;
		final int laterVersion = 20120926;
		final int lowerOrder = 5;
		final int higherOrder = 7;
		
		StephenerializableField field3 = new StephenerializableField();
		
		field1.setVersion(earlyVersion);
		field2.setVersion(laterVersion);
		field3.setVersion(laterVersion);
		
		field1.setOrder(higherOrder);
		field2.setOrder(lowerOrder);
		field3.setOrder(higherOrder);
		
		field1.setField(testField2);
		field2.setField(testField2);
		field3.setField(testField1);
		
		fields.add(field3);
		fields.add(field2);
		fields.add(field1);
		
		List<StephenerializableField> fieldList = new ArrayList<StephenerializableField>(fields);
		assertEquals(field1, fieldList.get(0));
		assertEquals(field2, fieldList.get(1));
		assertEquals(field3, fieldList.get(2));
	}

}
