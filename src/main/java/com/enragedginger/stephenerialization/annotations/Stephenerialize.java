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
package com.enragedginger.stephenerialization.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a field that can be Stephenerialized.
 * @author Stephen Hopper
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Stephenerialize {
	
	/**
	 * The minimum version required for Stephenerializing this field.
	 * The value of this field should be equal to the version number
	 * of the first version of the class to include this field.
	 * @return The minimum version required for Stephenerializing this field. 
	 */
	int minVersion();
	
	/**
	 * The sort priority of this field.  This values ensures that fields with
	 * the same {@link Stephenerialize#minVersion()} are always sorted in the
	 * same order.
	 * @return The sort priority of this field. 
	 */
	int priority();
}
