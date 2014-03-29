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
package com.enragedginger.stephenerialization.streamer;

import java.io.ObjectInputStream;

import com.enragedginger.stephenerialization.StephenerializationException;

/**
 * Simple {@link DestephenerializationStream} wrapper.
 * Wraps an {@link ObjectInputStream} instance.
 * @author Stephen Hopper
 *
 */
public class SimpleDestephenerializationStream implements
		DestephenerializationStream {
	
	private static final String BASIC_MSG = "An error occurred during destephenerialization.";
	
	private ObjectInputStream stream;

	/**
	 * Creates a new {@link SimpleDestephenerializationStream}.
	 * @param stream The stream to wrap.
	 */
	public SimpleDestephenerializationStream(ObjectInputStream stream) {
		this.stream = stream;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public byte readByte() {
		try {
			return stream.readByte();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public short readShort() {
		try {
			return stream.readShort();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int readInt() {
		try {
			return stream.readInt();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public long readLong() {
		try {
			return stream.readLong();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public float readFloat() {
		try {
			return stream.readFloat();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public double readDouble() {
		try {
			return stream.readDouble();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean readBoolean() {
		try {
			return stream.readBoolean();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public char readChar() {
		try {
			return stream.readChar();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Object readObject() {
		try {
			return stream.readObject();
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
}
