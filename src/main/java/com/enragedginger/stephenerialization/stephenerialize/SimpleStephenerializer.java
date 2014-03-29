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
package com.enragedginger.stephenerialization.stephenerialize;

import java.lang.reflect.Field;
import java.util.Set;

import com.enragedginger.stephenerialization.StephenerializationException;
import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.fields.SimpleStephenerializableFieldFactory;
import com.enragedginger.stephenerialization.fields.StephenerializableField;
import com.enragedginger.stephenerialization.fields.StephenerializableFieldFactory;
import com.enragedginger.stephenerialization.fields.StephenerializableType;
import com.enragedginger.stephenerialization.streamer.StephenerializationStream;

public class SimpleStephenerializer implements Stephenerializer {
	
	private static final String ERROR_MSG = "An error occurred while performing stepherialization.";

	private StephenerializableFieldFactory fieldFactory;
	
	/**
	 * {@inheritDoc}
	 */
	public void stephenerialize(Object object,
			StephenerializationStream streamer, Class<?> clazz) {
		final Stephenerializable annotation = clazz.getAnnotation(Stephenerializable.class);
		if (annotation == null) {
			throw new StephenerializationException("The class " + clazz.getName() + " cannot be Stephenerialized "
					+ "because it lacks a Stephenerializable annotation.");
		} else {
			final int version = annotation.version();
			final Set<StephenerializableField> fields = getFieldFactory().generateFields(clazz, version);
			try {
				streamer.writeInt(version); // write the version out to the stream
				for (StephenerializableField field : fields) {
					writeField(object, clazz, field.getField(), streamer);
				}
			} catch (Exception e) {
				throw new StephenerializationException(ERROR_MSG, e);
			}
		}
	}
	
	private void writeField(Object object, Class<?> clazz, Field field,
			StephenerializationStream streamer)
			throws IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		final StephenerializableType type = StephenerializableType.lookup(clazz);
		switch (type) {
			case BYTE :
				streamer.writeByte(field.getByte(object));
				break;
			case SHORT :
				streamer.writeShort(field.getShort(object));
				break;
			case INT :
				streamer.writeInt(field.getInt(object));
				break;
			case LONG :
				streamer.writeLong(field.getLong(object));
				break;
			case FLOAT :
				streamer.writeFloat(field.getFloat(object));
				break;
			case DOUBLE :
				streamer.writeDouble(field.getDouble(object));
				break;
			case BOOLEAN :
				streamer.writeBoolean(field.getBoolean(object));
				break;
			case CHAR :
				streamer.writeChar(field.getChar(object));
				break;
			case OBJECT :
				streamer.writeObject(field.get(object));
				break;
			default :
				throw new StephenerializationException("Unsupported stephenerialization type: " + type);
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
