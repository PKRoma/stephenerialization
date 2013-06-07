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
package com.enragedginger.stephenerialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class for building output and input streams for functional (funk) tests.
 * @author Stephen Hopper
 *
 */
public class TestStreamBuilder {
	
	private static final String TEST_FILE_DIR = "test/output";

	/**
	 * Creates an output stream for testing.
	 * @param fileGroup The name of the class.
	 * @param filename The filename.
	 * @return An output stream for testing.
	 * @throws IOException If failure.
	 */
	public static ObjectOutputStream createOutputStream(String fileGroup, String filename) throws IOException {
		final FileOutputStream stream = new FileOutputStream(createFile(TEST_FILE_DIR, fileGroup, filename));
		return new ObjectOutputStream(stream);
	}
	
	/**
	 * Creates an input stream for testing.
	 * @param fileGroup The name of the class.
	 * @param filename The filename.
	 * @return An input stream for testing.
	 * @throws IOException If failure.
	 */
	public static ObjectInputStream createInputStream(String fileGroup, String filename) throws IOException {
		final FileInputStream stream = new FileInputStream(createFile(TEST_FILE_DIR, fileGroup, filename));
		return new ObjectInputStream(stream);
	}
	
	/**
	 * Creates a file object at baseDir/name/version.
	 * @param baseDir The base directory.
	 * @param className The name of the class.
	 * @param filename The filename.
	 * @return A file object at baseDir/name/version.
	 * @throws IOException 
	 */
	private static File createFile(String baseDir, String className, String filename) throws IOException {
		File dir = new File("./" + baseDir + "/" + className);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(dir, filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

}
