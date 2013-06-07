package com.enragedginger.stephenerialization.fields;

/**
 * A key which describes a set of {@link StephenerializableField} instances.
 * 
 * @author Stephen Hopper
 * 
 */
public class StephenerializableFieldSetKey {

	private Class<?> clazz;
	private int maxVersion;

	/**
	 * @return the clazz
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz
	 *            the clazz to set
	 */
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * @return the maxVersion
	 */
	public int getMaxVersion() {
		return maxVersion;
	}

	/**
	 * @param maxVersion
	 *            the maxVersion to set
	 */
	public void setMaxVersion(int maxVersion) {
		this.maxVersion = maxVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return maxVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StephenerializableFieldSetKey other = (StephenerializableFieldSetKey) obj;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		if (maxVersion != other.maxVersion)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StephenerializableFieldSetKey [clazz=");
		builder.append(clazz);
		builder.append(", maxVersion=");
		builder.append(maxVersion);
		builder.append("]");
		return builder.toString();
	}
	
}
