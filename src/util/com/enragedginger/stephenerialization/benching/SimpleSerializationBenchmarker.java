package com.enragedginger.stephenerialization.benching;

import java.io.IOException;

public class SimpleSerializationBenchmarker extends BaseStephenerializationBenchmarker {

	/**
	 * {@inheritDoc}
	 */
	protected BasicPojo createNewPojo() {
		return new SimpleSerializablePojo();
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected String getTempObjectOutputStreamFile() {
		return this.getClass().getSimpleName() + "tempbench";
	}
	
	public static void main(String[] args) throws IOException {
		SimpleSerializationBenchmarker benchmarker = new SimpleSerializationBenchmarker();
		benchmarker.runTest(args);
	}

}
