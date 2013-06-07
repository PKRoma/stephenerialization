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
 * This annotation denotes a class which can be Stephenerialized.
 * Each field in the class that should be stephenerialized should
 * be annotated as {@link Stephenerialize}.
 * @author Stephen Hopper
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Stephenerializable {
	
	/**
	 * The version of the class.  Older versions of a class must
	 * have lower version values than newer versions of a class.
	 * It's suggested to use dates in yyyyMMdd format.
	 * @return The version of the class.
	 */
	int version();
}
