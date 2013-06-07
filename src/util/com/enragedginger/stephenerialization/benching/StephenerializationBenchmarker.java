package com.enragedginger.stephenerialization.benching;

import java.io.IOException;

/**
 * Class for benchmarking Stephenerialization performance.
 * @author Stephen Hopper
 *
 */
public class StephenerializationBenchmarker extends BaseStephenerializationBenchmarker {
	
	/**
	 * {@inheritDoc}
	 */
	protected BasicPojo createNewPojo() {
		return new StephenerializablePojo();
	}

	/**
	 * {@inheritDoc}
	 */
	protected String getTempObjectOutputStreamFile() {
		return this.getClass().getSimpleName() + "tempbench";
	}
	
	public static void main(String[] args) throws IOException {
		StephenerializationBenchmarker benchmarker = new StephenerializationBenchmarker();
		benchmarker.runTest(args);
	}
}
