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

import static org.easymock.EasyMock.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import com.enragedginger.stephenerialization.destephenerialize.Destephenerializer;
import com.enragedginger.stephenerialization.stephenerialize.Stephenerializer;
import com.enragedginger.stephenerialization.streamer.DestephenerializationStream;
import com.enragedginger.stephenerialization.streamer.StephenerializationStream;

/**
 * Class for testing {@link SimpleStephenerializationService}.
 * @author Stephen Hopper
 *
 */
public class SimpleStephenerializationServiceTest {
	
	private SimpleStephenerializationService service;
	
	private IMocksControl control;
	private Stephenerializer mockStephenerializer;
	private Destephenerializer mockDestephenerializer;
	private ObjectOutputStream mockStephenerializationStream;
	private ObjectInputStream mockDestephenerializationStream;
	
	@Before
	public void initialize() {
		service = new SimpleStephenerializationService();
		control = EasyMock.createControl();
		mockStephenerializer = control.createMock(Stephenerializer.class);
		mockDestephenerializer = control.createMock(Destephenerializer.class);
		mockStephenerializationStream = control.createMock(ObjectOutputStream.class);
		mockDestephenerializationStream = control.createMock(ObjectInputStream.class);
		service.setDestephenerializer(mockDestephenerializer);
		service.setStephenerializer(mockStephenerializer);
	}
	
	@Test
	public void testStephenerialize() {
		Object face = new Object() {
		};
		mockStephenerializer.stephenerialize(isA(face.getClass()), isA(StephenerializationStream.class), isA(Class.class));
		control.replay();
		service.stephenerialize(face, mockStephenerializationStream, face.getClass());
		control.verify();
	}
	
	@Test
	public void testDestephenerialize() {
		Object face = new Object() {
		};
		mockDestephenerializer.destephenerialize(isA(face.getClass()), isA(DestephenerializationStream.class), isA(Class.class));
		control.replay();
		service.destephenerialize(face, mockDestephenerializationStream, face.getClass());
		control.verify();
	}

}
