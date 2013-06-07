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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.annotations.Stephenerialize;

/**
 * Simple implementation of {@link StephenerializableFieldFactory}. 
 * @author Stephen Hopper
 *
 */
public class SimpleStephenerializableFieldFactory implements StephenerializableFieldFactory {
	
	private Map<StephenerializableFieldSetKey, Set<StephenerializableField>> fieldSetCache =
			new HashMap<StephenerializableFieldSetKey, Set<StephenerializableField>>();

	/**
	 * {@inheritDoc}
	 */
	public Set<StephenerializableField> generateFields(Class<?> clazz, int maxVersion) {
		Set<StephenerializableField> fields;
		StephenerializableFieldSetKey fieldSetKey = createKey(clazz, maxVersion);
		if (!fieldSetCache.containsKey(fieldSetKey)) {
			fields = new TreeSet<StephenerializableField>();
			fields.addAll(generateLocalFields(clazz, maxVersion));
			fieldSetCache.put(fieldSetKey, fields);
		} else {
			fields = fieldSetCache.get(fieldSetKey);
		}
		
		return fields;
	}
	
	/**
	 * Creates a new {@link StephenerializableFieldSetKey}
	 * @param clazz The clazz for the key.
	 * @param maxVersion The maxVersion value for the key.
	 * @return A new {@link StephenerializableFieldSetKey} instance.
	 */
	private StephenerializableFieldSetKey createKey(Class<?> clazz, int maxVersion) {
		StephenerializableFieldSetKey key = new StephenerializableFieldSetKey();
		key.setClazz(clazz);
		key.setMaxVersion(maxVersion);
		return key;
	}
	
	/**
	 * Generates the fields declared by clazz.
	 * @param clazz The clazz.
	 * @param version The max version.
	 * @return A set of fields representing the declared fields in clazz.
	 */
	private Set<StephenerializableField> generateLocalFields(Class<?> clazz, int maxVersion) {
		final Set<StephenerializableField> fields = new TreeSet<StephenerializableField>();
		final Stephenerializable annotation = clazz.getAnnotation(Stephenerializable.class);
		if (annotation != null) {
			for(Field field : clazz.getDeclaredFields()) {
				final Stephenerialize stephenerialize = field.getAnnotation(Stephenerialize.class);
				if (stephenerialize != null && stephenerialize.minVersion() <= maxVersion) {
					final StephenerializableField stephenField = new StephenerializableField();
					stephenField.setField(field);
					stephenField.setVersion(stephenerialize.minVersion());
					stephenField.setOrder(stephenerialize.priority());
					fields.add(stephenField);
				}
			}
		}
		return fields;
	}

}
