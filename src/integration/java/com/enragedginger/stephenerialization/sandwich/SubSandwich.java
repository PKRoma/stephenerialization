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
package com.enragedginger.stephenerialization.sandwich;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.enragedginger.stephenerialization.StephenerializationLookupService;
import com.enragedginger.stephenerialization.StephenerializationService;
import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.annotations.Stephenerialize;

/**
 * A submarine sandwich.  Used for testing purposes.
 * @author Stephen Hopper
 *
 */
@Stephenerializable(version=20121001)
public class SubSandwich extends Sandwich {
	
	private static final long serialVersionUID = 1L;
	
	@Stephenerialize(minVersion = 20120926, priority = 1)
	private float length;
	
	@Stephenerialize(minVersion = 20120927, priority = 1)
	private boolean actuallyFresh;
	
	/**
	 * @return the length
	 */
	public float getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(float length) {
		this.length = length;
	}

	/**
	 * @return the actuallyFresh
	 */
	public boolean isActuallyFresh() {
		return actuallyFresh;
	}

	/**
	 * @param actuallyFresh the actuallyFresh to set
	 */
	public void setActuallyFresh(boolean actuallyFresh) {
		this.actuallyFresh = actuallyFresh;
	}

	/**
	 * Writes this object out to the stream using Stephenerialization.
	 * @param streamer The output stream to use.
	 */
	private void writeObject(ObjectOutputStream streamer) {
		final StephenerializationService service = StephenerializationLookupService.lookup();
		service.stephenerialize(this, streamer, SubSandwich.class);
	}
	
	/**
	 * Reads this object from the stream using Stephenerialization.
	 * @param streamer The input stream to use.
	 */
	private void readObject(ObjectInputStream streamer) {
		final StephenerializationService service = StephenerializationLookupService.lookup();
		service.destephenerialize(this, streamer, SubSandwich.class);
	}

}
