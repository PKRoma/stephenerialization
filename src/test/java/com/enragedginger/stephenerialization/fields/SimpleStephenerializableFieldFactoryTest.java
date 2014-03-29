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

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.annotations.Stephenerialize;

/**
 * Class for testing {@link SimpleStephenerializableFieldFactory}.
 * @author Stephen Hopper
 */
public class SimpleStephenerializableFieldFactoryTest {
	
	private SimpleStephenerializableFieldFactory factory;
	
	@Before
	public void setup() {
		factory = new SimpleStephenerializableFieldFactory();
	}
	
	/**
	 * Test the generate method.
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 */
	@Test
	public void testGenerate() throws SecurityException, NoSuchFieldException {
		final int superVersion = 20120928;
		final int subVersion = 20120928;
		final int subPriority = 1;
		final int superPriority = 2;
		
		@Stephenerializable(version=superVersion)
		class TestFaceSuperClass {
			@Stephenerialize(minVersion=superVersion, priority=superPriority)
			private int superFace1;
		}
		
		@Stephenerializable(version=subVersion)
		class TestFaceClass extends TestFaceSuperClass {
			@Stephenerialize(minVersion=subVersion-1, priority=subPriority+1)
			private int face1;
			@Stephenerialize(minVersion=subVersion, priority=subPriority)
			private int face2;
			@Stephenerialize(minVersion=subVersion+1, priority=subPriority-1)
			private int face3;
		}

		final Set<StephenerializableField> fields = factory.generateFields(TestFaceClass.class, subVersion);
		assertEquals(2, fields.size());
		final List<StephenerializableField> fieldList = new ArrayList<StephenerializableField>(fields);
		final Field face1 = TestFaceClass.class.getDeclaredField("face1");
		final Field face2 = TestFaceClass.class.getDeclaredField("face2");
		
		final StephenerializableField expectedField1 = fieldList.get(0);
		assertEquals(face1, expectedField1.getField());
		assertEquals(subPriority + 1, expectedField1.getOrder());
		assertEquals(subVersion - 1, expectedField1.getVersion());
		final StephenerializableField subField = fieldList.get(1);
		assertEquals(face2, subField.getField());
		assertEquals(subPriority, subField.getOrder());
		assertEquals(subVersion, subField.getVersion());
	}

}
