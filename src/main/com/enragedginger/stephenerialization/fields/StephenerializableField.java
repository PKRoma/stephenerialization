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

/**
 * Represents a field which is stephenerializable.
 * @author Stephen Hopper
 *
 */
public class StephenerializableField implements Comparable<StephenerializableField>{
	
	private Field field;
	private int version;
	private int order;
	
	/**
	 * @return the field
	 */
	public Field getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(Field field) {
		this.field = field;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object other) {
		boolean equals = false;
		if (other instanceof StephenerializableField) {
			StephenerializableField that = (StephenerializableField) other;
			equals = this.compareTo(that) == 0;
		}
		return equals;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int compareTo(StephenerializableField other) {
		int value = 0;
		if (other != null) {
			if (this.version != other.version) {
				value = ((Integer) this.version).compareTo(other.version);
			} else if (this.order != other.order) {
				value = ((Integer) this.order).compareTo(other.order);
			} else if (this.field != other.field) {
				if (this.field != null) {
					value = this.field.getName().compareTo(other.field.getName());
				} else {
					value = 1;
				}
			}
		}
		return value;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("StephenerializableField [field=");
		builder.append(field);
		builder.append(", version=");
		builder.append(version);
		builder.append(", order=");
		builder.append(order);
		builder.append("]");
		return builder.toString();
	}
	
}
