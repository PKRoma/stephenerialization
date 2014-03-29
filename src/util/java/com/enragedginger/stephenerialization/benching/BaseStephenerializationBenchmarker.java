package com.enragedginger.stephenerialization.benching;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 * Base class for Stephenerialization benchmarking.
 * @author Stephen Hopper
 *
 */
public abstract class BaseStephenerializationBenchmarker {
	
	private static final Random randy = new Random();
	
	private static final String QUOTE = "\"";
	private static final String SEPARATOR = "\",\"";
	
	protected abstract BasicPojo createNewPojo();
	
	protected abstract String getTempObjectOutputStreamFile();
	
	protected void fillPojo(BasicPojo pojo) {
		pojo.setSomeBoolean(randy.nextBoolean());
		pojo.setSomeDouble(randy.nextDouble());
		pojo.setSomeFloat(randy.nextFloat());
		pojo.setSomeInt(randy.nextInt());
		pojo.setSomeLong(randy.nextLong());
	}
	
	protected void runTest(String[] args) throws IOException {
		int groupSize = Integer.parseInt(args[0]);
		File tempOutputFile = new File(getTempObjectOutputStreamFile());
		ObjectOutputStream streamer = new ObjectOutputStream(new FileOutputStream(tempOutputFile));
		streamer.writeObject((BasicPojo) createNewPojo());
		BasicPojo[] pojos = new BasicPojo[groupSize];
		for (int i = 0; i < groupSize; i++) {
			BasicPojo pojo = createNewPojo();
			fillPojo(pojo);
			pojos[i] = pojo;
		}
		
		long startMilliTime = System.currentTimeMillis();
		long startNanoTime = System.nanoTime();
		for (BasicPojo pojo : pojos) {
			streamer.writeObject(pojo);
		}
		streamer.flush();
		streamer.close();
		long endMilliTime = System.currentTimeMillis();
		long endNanoTime = System.nanoTime();
		tempOutputFile.delete();
		
		long totalMilliTime = endMilliTime - startMilliTime;
		long totalNanoTime = endNanoTime - startNanoTime;
		System.out.println(QUOTE + groupSize + SEPARATOR + totalMilliTime + SEPARATOR + totalNanoTime + QUOTE);
	}

}
