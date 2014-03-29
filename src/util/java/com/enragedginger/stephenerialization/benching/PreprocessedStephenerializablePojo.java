package com.enragedginger.stephenerialization.benching;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.enragedginger.stephenerialization.annotations.Stephenerializable;
import com.enragedginger.stephenerialization.annotations.Stephenerialize;

/**
 * A Stephenerializable Pojo for benchmarks.
 * @author Stephen Hopper
 *
 */
@Stephenerializable(version=20121211)
public class PreprocessedStephenerializablePojo implements BasicPojo {

    private static final long serialVersionUID = 1L;

    @Stephenerialize(minVersion=20121211, priority=1)
    private boolean someBoolean;
    @Stephenerialize(minVersion=20121211, priority=2)
    private int someInt;
    @Stephenerialize(minVersion=20121211, priority=3)
    private long someLong;
    @Stephenerialize(minVersion=20121211, priority=4)
    private float someFloat;
    @Stephenerialize(minVersion=20121211, priority=5)
    private double someDouble;

    /**
     * @return the someBoolean
     */
    public boolean isSomeBoolean() {
        return someBoolean;
    }
    /**
     * @param someBoolean the someBoolean to set
     */
    public void setSomeBoolean(boolean someBoolean) {
        this.someBoolean = someBoolean;
    }
    /**
     * @return the someInt
     */
    public int getSomeInt() {
        return someInt;
    }
    /**
     * @param someInt the someInt to set
     */
    public void setSomeInt(int someInt) {
        this.someInt = someInt;
    }
    /**
     * @return the someLong
     */
    public long getSomeLong() {
        return someLong;
    }
    /**
     * @param someLong the someLong to set
     */
    public void setSomeLong(long someLong) {
        this.someLong = someLong;
    }
    /**
     * @return the someFloat
     */
    public float getSomeFloat() {
        return someFloat;
    }
    /**
     * @param someFloat the someFloat to set
     */
    public void setSomeFloat(float someFloat) {
        this.someFloat = someFloat;
    }
    /**
     * @return the someDouble
     */
    public double getSomeDouble() {
        return someDouble;
    }
    /**
     * @param someDouble the someDouble to set
     */
    public void setSomeDouble(double someDouble) {
        this.someDouble = someDouble;
    }

    /**
     * Writes this object out to the stream using Stephenerialization.
     * @param streamer The output stream to use.
     */
    private void writeObject(ObjectOutputStream streamer) {
        PreprocessedStephenerializablePojoStephenerializer.stephenerialize(this, streamer);
    }

    /**
     * Reads this object from the stream using Stephenerialization.
     * @param streamer The input stream to use.
     */
    private void readObject(ObjectInputStream streamer) {
        PreprocessedStephenerializablePojoStephenerializer.destephenerialize(this, streamer);
    }

}
