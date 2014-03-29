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

import java.io.ObjectOutputStream;

import com.enragedginger.stephenerialization.StephenerializationException;

/**
 * Simple implementation of {@link StephenerializationStream}.
 * Wraps an {@link ObjectOutputStream} instance.
 * @author Stephen Hopper
 *
 */
public class SimpleStephenerializationStream implements
		StephenerializationStream {
	
	private static final String BASIC_MSG = "An error occurred during stephenerialization.";

	private ObjectOutputStream stream;

	public SimpleStephenerializationStream(ObjectOutputStream stream) {
		this.stream = stream;
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeByte(byte value) {
		try {
			stream.writeByte(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeShort(short value) {
		try {
			stream.writeShort(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeInt(int value) {
		try {
			stream.writeInt(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeLong(long value) {
		try {
			stream.writeLong(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeFloat(float value) {
		try {
			stream.writeFloat(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeDouble(double value) {
		try {
			stream.writeDouble(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeChar(char value) {
		try {
			stream.writeChar(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeBoolean(boolean value) {
		try {
			stream.writeBoolean(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void writeObject(Object value) {
		try {
			stream.writeObject(value);
		} catch (Exception e) {
			throw new StephenerializationException(BASIC_MSG, e);
		}
	}
	
}
