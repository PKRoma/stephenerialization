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

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.enragedginger.stephenerialization.destephenerialize.Destephenerializer;
import com.enragedginger.stephenerialization.destephenerialize.SimpleDestephenerializer;
import com.enragedginger.stephenerialization.stephenerialize.SimpleStephenerializer;
import com.enragedginger.stephenerialization.stephenerialize.Stephenerializer;
import com.enragedginger.stephenerialization.streamer.DestephenerializationStream;
import com.enragedginger.stephenerialization.streamer.SimpleDestephenerializationStream;
import com.enragedginger.stephenerialization.streamer.SimpleStephenerializationStream;
import com.enragedginger.stephenerialization.streamer.StephenerializationStream;

/**
 * A simple implementation of {@link StephenerializationService}.
 * @author Stephen Hopper
 *
 */
public class SimpleStephenerializationService implements
		StephenerializationService {
	
	private Stephenerializer stephenerializer;
	
	private Destephenerializer destephenerializer;

	/**
	 * {@inheritDoc}
	 */
	public void stephenerialize(Object object, ObjectOutputStream streamer, Class<?> clazz) {
		getStephenerializer().stephenerialize(object, createStephenerializationStream(streamer), clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	public void destephenerialize(Object object, ObjectInputStream streamer, Class<?> clazz) {
		getDestephenerializer().destephenerialize(object, createDestephenerializationStream(streamer), clazz);
	}

	/**
	 * @param stephenerializer The stephenerializer to use.
	 */
	public void setStephenerializer(Stephenerializer stephenerializer) {
		this.stephenerializer = stephenerializer;
	}
	
	/**
	 * Returns the current {@link Stephenerializer} or creates a new one
	 * if one does not already exists.
	 * @return A stephenerializer instance.
	 */
	public Stephenerializer getStephenerializer() {
		if (stephenerializer == null) {
			stephenerializer = new SimpleStephenerializer();
		}
		return stephenerializer;
	}

	/**
	 * @param destephenerializer The destephenerializer to use.
	 */
	public void setDestephenerializer(Destephenerializer destephenerializer) {
		this.destephenerializer = destephenerializer;
	}
	
	/**
	 * Returns the current {@link Destephenerializer} or creates a new one
	 * if one does not already exist.
	 * @return A destephenerializer instance.
	 */
	public Destephenerializer getDestephenerializer() {
		if (destephenerializer == null) {
			destephenerializer = new SimpleDestephenerializer();
		}
		return destephenerializer;
	}
	
	/**
	 * Creates a new {@link StephenerializationStream}.
	 * @param streamer The stream to wrap.
	 * @return A new {@link StephenerializationStream}.
	 */
	private StephenerializationStream createStephenerializationStream(ObjectOutputStream streamer) {
		return new SimpleStephenerializationStream(streamer);
	}
	
	/**
	 * Creates a new {@link DestephenerializationStream}.
	 * @param streamer The stream to wrap.
	 * @return A new {@link DestephenerializationStream}.
	 */
	private DestephenerializationStream createDestephenerializationStream(ObjectInputStream streamer) {
		return new SimpleDestephenerializationStream(streamer);
	}

}
