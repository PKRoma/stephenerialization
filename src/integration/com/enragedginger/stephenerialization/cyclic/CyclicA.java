package com.enragedginger.stephenerialization.cyclic;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.enragedginger.stephenerialization.StephenerializationLookupService;
import com.enragedginger.stephenerialization.StephenerializationService;
import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.annotations.Stephenerialize;

/**
 * Class for testing Stephenerialization involving cyclical references.
 * @author Stephen Hopper
 *
 */
@Stephenerializable(version = 20121225)
public class CyclicA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Stephenerialize(minVersion = 20121225, priority = 1)
	private int face = 7;

	@Stephenerialize(minVersion = 20121225, priority = 1)
	private CyclicB cyclic;

	/**
	 * @return the face
	 */
	public int getFace() {
		return face;
	}

	/**
	 * @param face
	 *            the face to set
	 */
	public void setFace(int face) {
		this.face = face;
	}

	/**
	 * @return the cyclic
	 */
	public CyclicB getCyclic() {
		return cyclic;
	}

	/**
	 * @param cyclic
	 *            the cyclic to set
	 */
	public void setCyclic(CyclicB cyclic) {
		this.cyclic = cyclic;
	}
	
	/**
	 * Writes this object out to the stream using Stephenerialization.
	 * @param streamer The output stream to use.
	 */
	private void writeObject(ObjectOutputStream streamer) {
		final StephenerializationService service = StephenerializationLookupService.lookup();
		service.stephenerialize(this, streamer, CyclicA.class);
	}
	
	/**
	 * Reads this object from the stream using Stephenerialization.
	 * @param streamer The input stream to use.
	 */
	private void readObject(ObjectInputStream streamer) {
		final StephenerializationService service = StephenerializationLookupService.lookup();
		service.destephenerialize(this, streamer, CyclicA.class);
	}

}
