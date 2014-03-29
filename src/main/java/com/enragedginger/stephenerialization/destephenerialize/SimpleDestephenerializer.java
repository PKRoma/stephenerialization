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
package com.enragedginger.stephenerialization.destephenerialize;

import java.lang.reflect.Field;
import java.util.Set;

import com.enragedginger.stephenerialization.StephenerializationException;
import com.enragedginger.stephenerialization.fields.SimpleStephenerializableFieldFactory;
import com.enragedginger.stephenerialization.fields.StephenerializableField;
import com.enragedginger.stephenerialization.fields.StephenerializableFieldFactory;
import com.enragedginger.stephenerialization.fields.StephenerializableType;
import com.enragedginger.stephenerialization.streamer.DestephenerializationStream;

/**
 * Simple implementation of {@link Destephenerializer}.
 * @author Stephen Hopper
 *
 */
public class SimpleDestephenerializer implements Destephenerializer {
	
	private static final String ERROR_MSG = "An error occurred while performing destepherialization.";
	
	private StephenerializableFieldFactory fieldFactory;

	/**
	 * {@inheritDoc}
	 */
	public void destephenerialize(Object object,
			DestephenerializationStream streamer,
			Class<?> clazz) {
		try {
			final int version = streamer.readInt(); // read the version from the stream
			final Set<StephenerializableField> fields = getFieldFactory().generateFields(clazz, version);
			for (StephenerializableField field : fields) {
				readField(object, clazz, field.getField(), streamer);
			}
		} catch (Exception e) {
			throw new StephenerializationException(ERROR_MSG, e);
		}
	}
	
	private void readField(Object object, Class<?> clazz, Field field,
			DestephenerializationStream streamer)
			throws IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		final StephenerializableType type = StephenerializableType.lookup(clazz);
		switch (type) {
			case BYTE :
				field.setByte(object, streamer.readByte());
				break;
			case SHORT :
				field.setShort(object, streamer.readShort());
				break;
			case INT :
				field.setInt(object, streamer.readInt());
				break;
			case LONG :
				field.setLong(object, streamer.readLong());
				break;
			case FLOAT :
				field.setFloat(object, streamer.readFloat());
				break;
			case DOUBLE :
				field.setDouble(object, streamer.readFloat());
				break;
			case BOOLEAN :
				field.setBoolean(object, streamer.readBoolean());
				break;
			case CHAR :
				field.setChar(object, streamer.readChar());
				break;
			case OBJECT :
				field.set(object, streamer.readObject());
				break;
			default :
				throw new StephenerializationException("Unsupported destephenerialization type: " + type);
		}
	}

	/**
	 * @param fieldFactory the fieldFactory to set
	 */
	public void setFieldFactory(StephenerializableFieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
	}

	/**
	 * Returns the current {@link StephenerializableFieldFactory} or
	 * creates a new one if it exists.
	 * @return A {@link StephenerializableFieldFactory} instance.
	 */
	public StephenerializableFieldFactory getFieldFactory() {
		if (fieldFactory == null) {
			fieldFactory = new SimpleStephenerializableFieldFactory();
		}
		return fieldFactory;
	}
	
}
