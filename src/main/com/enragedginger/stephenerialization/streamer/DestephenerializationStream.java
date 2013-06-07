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
package com.enragedginger.stephenerialization.streamer;

/**
 * Destephenerializes objects.
 * @author Stephen Hopper
 *
 */
public interface DestephenerializationStream {

	/**
	 * @return Reads a byte from the stream.
	 */
	byte readByte();
	
	/**
	 * @return Reads a short from the stream.
	 */
	short readShort();
	
	/**
	 * @return Reads an int from the stream.
	 */
	int readInt();
	
	/**
	 * @return Reads a long form the stream.
	 */
	long readLong();
	
	/**
	 * @return Reads a float from the stream.
	 */
	float readFloat();
	
	/**
	 * @return Reads a double from the stream.
	 */
	double readDouble();
	
	/**
	 * @return Reads a boolean from the stream.
	 */
	boolean readBoolean();
	
	/**
	 * @return Reads a char from the stream.
	 */
	char readChar();
	
	/**
	 * @return Reads an object from the stream.
	 */
	Object readObject();
}
