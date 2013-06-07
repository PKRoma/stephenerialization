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

import com.enragedginger.stephenerialization.streamer.DestephenerializationStream;

/**
 * Destephenerializes object.
 * @author Stephen Hopper
 *
 */
public interface Destephenerializer {
	
	/**
	 * Destephenerializes the parameters object from the streamer.
	 * @param object The target object for the destephenerialization.
	 * @param streamer The stream to destephenerialize from.
	 * @param clazz The class whose fields should be destephenerialized from the parameter object.
	 */
	void destephenerialize(Object object, DestephenerializationStream streamer, Class<?> clazz);
}
