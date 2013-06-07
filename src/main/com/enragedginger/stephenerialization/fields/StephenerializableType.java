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

/**
 * Represents a Stephenerializable type.
 * @author Stephen Hopper
 *
 */
public enum StephenerializableType {
	
	BYTE(byte.class, Byte.class),
	SHORT(short.class, Short.class),
	INT(int.class, Integer.class),
	LONG(long.class, Long.class),
	FLOAT(float.class, Float.class),
	DOUBLE(double.class, Double.class),
	CHAR(char.class, Character.class),
	BOOLEAN(boolean.class, Boolean.class),
	OBJECT(Object.class);
	
	private Class<?>[] classes;

	private StephenerializableType(Class<?> ... classes) {
		this.classes = classes;
	}

	/**
	 * @return the classes
	 */
	public Class<?>[] getClasses() {
		return classes;
	}
	
	/**
	 * Finds the {@link StephenerializableType} that matches clazz.
	 * If no match is found, {@link StephenerializableType#OBJECT}.
	 * @param clazz The class to match.
	 * @return The matching type.
	 */
	public static StephenerializableType lookup(Class<?> clazz) {
		for (StephenerializableType type : StephenerializableType.values()) {
			if (type != OBJECT) {
				for (Class<?> typeClass : type.getClasses()) {
					if (typeClass.equals(clazz)) {
						return type;
					}
				}
			}
		}
		return OBJECT;
	}

}
