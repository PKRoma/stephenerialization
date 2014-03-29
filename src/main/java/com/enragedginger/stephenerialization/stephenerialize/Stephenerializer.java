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

import com.enragedginger.stephenerialization.streamer.StephenerializationStream;

/**
 * Describes a class which stephenerializes objects.
 * @author Stephen Hopper
 *
 */
public interface Stephenerializer {
	
	/**
	 * Stephenerializes the parameter object.
	 * @param object The object to stephenerialize.
	 * @param streamer The stream to stephenerialize the object out to.
	 * @param clazz The class whose fields should be stephenerialized on the parameter object.
	 */
	void stephenerialize(Object object, StephenerializationStream streamer, Class<?> clazz);

}
