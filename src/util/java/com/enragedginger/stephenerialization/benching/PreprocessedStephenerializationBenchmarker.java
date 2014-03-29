package com.enragedginger.stephenerialization.benching;

import java.io.IOException;

public class PreprocessedStephenerializationBenchmarker extends BaseStephenerializationBenchmarker {

    /**
     * {@inheritDoc}
     */
    protected BasicPojo createNewPojo() {
        return new PreprocessedStephenerializablePojo();
    }

    /**
     * {@inheritDoc}
     */
    protected String getTempObjectOutputStreamFile() {
        return this.getClass().getSimpleName() + "tempbench";
    }

    public static void main(String[] args) throws IOException {
        PreprocessedStephenerializationBenchmarker benchmarker = new PreprocessedStephenerializationBenchmarker();
        benchmarker.runTest(args);
    }

}
