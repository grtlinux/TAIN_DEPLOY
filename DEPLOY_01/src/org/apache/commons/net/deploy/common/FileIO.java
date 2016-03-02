/**
 * Copyright 2014, 2015, 2016 TAIN, Inc. all rights reserved.
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * -----------------------------------------------------------------
 * Copyright 2014, 2015, 2016 TAIN, Inc.
 *
 */
package org.apache.commons.net.deploy.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : FileIO.java
 *   -. Package    : tain.kr.com.test.deploy.v01.common
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 17. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@SuppressWarnings("unused")
public class FileIO {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(FileIO.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	// The size of blocking to use
	protected static final int BLKSIZ = 16384;
	
	/*
	 * String for encoding UTF-8; copied by inclusion from StringUtil
	 */
	public static final String ENCODING_UTF_8 = "UTF-8";
	
	private FileIO() {
		// nothing to do
	}
	
	/**
	 * copy a file from one filename to another
	 * 
	 * @param inName
	 * @param outName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void copyFile(String inName, String outName) throws FileNotFoundException, IOException {
		BufferedInputStream is = null;
		BufferedOutputStream os = null;
		
		try {
			is = new BufferedInputStream(new FileInputStream(inName));
			os = new BufferedOutputStream(new FileOutputStream(outName));
			copyFile(is, os, false);
		} finally {
			if (is != null)
				is.close();
			
			os.flush();
			
			if (os != null)
				os.close();
		}
	}
	
	/**
	 * copy a file from an opened InputStream to opened OutputStream
	 * 
	 * @param is
	 * @param os
	 * @param close
	 * @throws IOException
	 */
	public static void copyFile(InputStream is, OutputStream os, boolean close) throws IOException {
		byte[] b = new byte[BLKSIZ];
		int n;
		while ((n = is.read(b)) != -1) {
			os.write(b, 0, n);
		}

		is.close();
		
		os.flush();
		
		if (close)
			os.close();
	}
	
	/**
	 * copy a file from an opened Reader to opened Writer
	 * 
	 * @param is
	 * @param os
	 * @param close
	 * @throws IOException
	 */
	public static void copyFile(Reader is, Writer os, boolean close) throws IOException {
		int b;    // the byte read from the file
		
		while ((b = is.read()) != -1) {
			os.write(b);
		}
		
		is.close();

		os.flush();
		
		if (close)
			os.close();
	}
	
	/**
	 * copy a file from a filename to a PrintWriter
	 * 
	 * @param inName
	 * @param pw
	 * @param close
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void copyFile(String inName, PrintWriter pw, boolean close) throws FileNotFoundException, IOException {
		BufferedReader ir = new BufferedReader(new FileReader(inName));
		copyFile(ir, pw, close);
	}
	
	/**
	 * copy a file to a directory, given File objects representing the files
	 * 
	 * @param file
	 * @param target
	 * @throws IOException
	 */
	public static void copyFile(File file, File target) throws IOException {
		if (!file.exists() || !file.isFile() || !(file.canRead())) {
			throw new IOException(file + " is not a readable file..");
		}
		
		File dest = target;
		if (target.isDirectory()) {
			dest = new File(dest, file.getName());
		}
		
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream(file);
			os = new FileOutputStream(dest);
			int n;
			byte[] b = new byte[BLKSIZ];
			while ((n = is.read(b)) != -1) {
				os.write(b, 0, n);
			}

			os.flush();
			
		} finally {
			is.close();
			os.close();
		}
	}
	
	/**
	 * copy a data file from one filename to another, alternative method.
	 * as the name suggests, use my own buffer instead of letting
	 * the BufferedReader allocate an use the buffer.
	 * 
	 * @param inName
	 * @param outName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void copyFileBuffered(String inName, String outName) throws FileNotFoundException, IOException {
		InputStream is = null;
		OutputStream os = null;
		
		try {
			is = new FileInputStream(inName);
			os = new FileOutputStream(outName);
			int n;
			byte[] b = new byte[BLKSIZ];
			while ((n = is.read(b)) != -1) {
				os.write(b, 0, n);
			}

			os.flush();
			
		} finally {
			if (is != null)
				is.close();
			
			if (os != null)
				os.close();
		}
	}
	
	/**
	 * copy all objects found in and under 'fromdir', to their places in 'todir'
	 * 
	 * @param fromDir
	 * @param toDir
	 * @param create
	 * @throws IOException
	 */
	public static void copyRecursively(File fromDir, File toDir, boolean create) throws IOException {
		
		if (!fromDir.exists()) {
			throw new IOException(String.format("Source directory %s does not exist", fromDir));
		}
		
		if (create) {
			toDir.mkdirs();
		} else if (!toDir.exists()) {
			throw new IOException(String.format("Destination dir %s must exist", toDir));
		}
		
		for (File src : fromDir.listFiles()) {
			if (src.isDirectory()) {
				File destSubDir = new File(toDir, src.getName());
				copyRecursively(src, destSubDir, true);
			} else if (src.isFile()) {
				copyFile(src, toDir);
			} else {
				System.err.println(String.format("Warning: %s is neither file nor directory", src));
			}
		}
	}
	
	/**
	 * copy files representing in subdirectories
	 * 
	 * @param fromDir
	 * @param toDir
	 * @throws IOException
	 */
	public static void copyRecursively(File fromDir, File toDir) throws IOException {
		copyRecursively(fromDir, toDir, false);
	}
	
	/**
	 * delete files representing in subdirectories
	 * 
	 * @param startDir
	 * @throws IOException
	 */
	public static void deleteRecursively(File startDir) throws IOException {
		String startDirPath = startDir.getCanonicalPath();
		
		for (File f : startDir.listFiles()) {
			if (!f.getCanonicalPath().startsWith(startDirPath)) {
				throw new IOException("Attempted to go out of " + startDir);
			}
			
			if (f.isDirectory()) {
				deleteRecursively(f);
			}
		}
		
		for (File f : startDir.listFiles()) {
			f.delete();
			if (f.exists()) {
				System.err.println(f + " did not get deleted.!");
			}
		}
		
		startDir.delete();
	}
	
	/**
	 * copy a tree of files to directory, given file objects representing the files
	 * 
	 * @param base
	 * @param startingDir
	 * @param toDir
	 * @throws IOException
	 */
	public static void copyRecursively(JarFile base, JarEntry startingDir, File toDir) throws IOException {
		if (!startingDir.isDirectory()) {
			throw new IOException(String.format("Starting point %s is not a directory", startingDir));
		}
		
		if (!toDir.exists()) {
			throw new IOException(String.format("Destination dir %s must exist", toDir));
		}
		
		Enumeration<JarEntry> all = base.entries();
		
		while (all.hasMoreElements()) {
			JarEntry file = all.nextElement();
			
			// ensure that it matches starting dir
			if (file.isDirectory()) {
				copyRecursively(base, file, new File(toDir, file.getName()));
			} else {
				InputStream is = null;
				OutputStream os = null;
				
				try {
					is = base.getInputStream(file);
					os = new FileOutputStream(new File(toDir, file.getName()));
					copyFile(is, os, false);

					os.flush();
					
				} finally {
					if (os != null)
						os.close();
					
					if (is != null)
						is.close();
				}
			}
		}
	}
	
	/**
	 * methods that do reading
	 * open a file and read the first line from it.
	 * 
	 * @param inName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readLine(String inName) throws FileNotFoundException, IOException {
		BufferedReader is = null;
		try {
			is = new BufferedReader(new FileReader(inName));
			String line = null;
			line = is.readLine();
			is.close();
			return line;
		} finally {
			if (is != null)
				is.close();
		}
	}
	
	/**
	 * read the entire content of a Reader into a String;
	 * of course Readers should only be used for text files;
	 * please do not use this to read a JPEG file, for example
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String readerToString(Reader is) throws IOException {
		StringBuffer sb = new StringBuffer();
		char[] b = new char[BLKSIZ];
		int n;
		
		while ((n = is.read(b)) > 0) {
			sb.append(b, 0, n);
		}
		
		return sb.toString();
	}
	
	/**
	 * read the content of a Stream into a String
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputStreamToString(InputStream is) throws IOException {
		return readerToString(new InputStreamReader(is));
	}
	
	/**
	 * read a string
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String readAsString(String fileName) throws IOException {
		return readerToString(new FileReader(fileName));
	}
	
	/**
	 * write a String as the entire content fo a file
	 * 
	 * @param text
	 * @param fileName
	 * @throws IOException
	 */
	public static void stringToFile(String text, String fileName) throws IOException {
		BufferedWriter os = new BufferedWriter(new FileWriter(fileName));
		os.write(text);
		os.flush();
		os.close();
	}
	
	/**
	 * open a BufferedReader from a named file.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static BufferedReader openFile(String fileName) throws IOException {
		return new BufferedReader(new FileReader(fileName));
	}
}
