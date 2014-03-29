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
package com.enragedginger.stephenerialization;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Service for stephenerializing objects.
 * @author Stephen Hopper
 *
 */
public interface StephenerializationService {
	
	/**
	 * Stephenerializes an object.
	 * @param object The object to stephenerialize.
	 * @param streamer The output stream.
	 * @param type The class whose fields should be stephenerialized using the parameter object.
	 */
	void stephenerialize(Object object, ObjectOutputStream streamer, Class<?> type);

	/**
	 * Destephenerializes an object.
	 * @param object The object to destephenerialize.
	 * @param streamer The input stream.
	 * @param type The class whose fields should be destephenerialized using the parameter object.
	 */
	void destephenerialize(Object object, ObjectInputStream streamer, Class<?> type);

}
