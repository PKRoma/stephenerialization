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
 * Represents an output stream for stephenerialization.
 * @author Stephen Hopper
 *
 */
public interface StephenerializationStream {
	
	/**
	 * Writes a byte to the stream.
	 * @param value The byte to write.
	 */
	void writeByte(byte value);
	
	/**
	 * Writes a short to the stream.
	 * @param value The short to write.
	 */
	void writeShort(short value);
	
	/**
	 * Writes an int to the stream.
	 * @param value The int to write.
	 */
	void writeInt(int value);
	
	/**
	 * Writes a long to the stream.
	 * @param value The long to write.
	 */
	void writeLong(long value);
	
	/**
	 * Writes a float to the stream.
	 * @param value The float to write.
	 */
	void writeFloat(float value);
	
	/**
	 * Writes a double to the stream.
	 * @param value The double to write.
	 */
	void writeDouble(double value);
	
	/**
	 * Writes a char to the stream.
	 * @param value The char to write.
	 */
	void writeChar(char value);
	
	/**
	 * Writes a boolean to the stream.
	 * @param value The boolean to write.
	 */
	void writeBoolean(boolean value);

	/**
	 * Writes an object to the stream.
	 * @param value The object to write.
	 */
	void writeObject(Object value);
}
