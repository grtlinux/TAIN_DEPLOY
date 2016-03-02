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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Exec.java
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
public class Exec {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(Exec.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * Need a Runtime object for any of these methods
	 */
	protected final static Runtime r = Runtime.getRuntime();
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Run the command given as a String, print its output to "out", close whether osClose
	 * 
	 * @param cmd
	 * @param out
	 * @param osClose
	 * @return
	 * @throws IOException
	 */
	public static int run(String cmd, Writer out, boolean osClose) throws IOException {
		
		Process p = r.exec(cmd);
		
		FileIO.copyFile(new InputStreamReader(p.getInputStream()), out, osClose);
		
		try {
			p.waitFor();     // wait for process to complete
		} catch (InterruptedException e) {
			return -1;
		}
		
		return p.exitValue();
	}

	/**
	 * Run the command given as a String, print its output to System.out, close whether osClose
	 * 
	 * @param cmd
	 * @param osClose
	 * @return
	 * @throws IOException
	 */
	public static int run(String cmd, boolean osClose) throws IOException {
		return run(cmd, new OutputStreamWriter(System.out), osClose);
	}
	
	/**
	 * Run the command given as a String, print its output to "out", and then close
	 * 
	 * @param cmd
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public static int run(String cmd, Writer out) throws IOException {
		return run(cmd, out, true);
	}

	/**
	 * run the command given as a String, print its output to System.out, and then close
	 * @param cmd
	 * @return
	 * @throws IOException
	 */
	public static int run(String cmd) throws IOException {
		return run(cmd, new OutputStreamWriter(System.out), true);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Run the command given as a String[], print its output to "out", close whether osClose
	 * 
	 * @param cmd
	 * @param out
	 * @param osClose
	 * @return
	 * @throws IOException
	 */
	public static int run(String[] cmd, Writer out, boolean osClose) throws IOException {
		
		Process p = r.exec(cmd);
		
		FileIO.copyFile(new InputStreamReader(p.getInputStream()), out, osClose);
		
		try {
			p.waitFor();     // wait for process to complete
		} catch (InterruptedException e) {
			return -1;
		}
		
		return p.exitValue();
	}
	
	/**
	 * Run the command given as a String[], print its output to System.out, close whether osClose
	 * 
	 * @param cmd
	 * @param osClose
	 * @return
	 * @throws IOException
	 */
	public static int run(String[] cmd, boolean osClose) throws IOException {
		return run(cmd, new OutputStreamWriter(System.out), osClose);
	}

	/**
	 * Run the command given as a String[], print its output to "out", and then close
	 * 
	 * @param cmd
	 * @param out
	 * @return
	 * @throws IOException
	 */
	public static int run(String[] cmd, Writer out) throws IOException {
		return run(cmd, out, true);
	}

	/**
	 * Run the command given as a String[], print its output to System.out, and then close
	 * 
	 * @param cmd
	 * @return
	 * @throws IOException
	 */
	public static int run(String[] cmd) throws IOException {
		return run(cmd, new OutputStreamWriter(System.out), true);
	}
}
