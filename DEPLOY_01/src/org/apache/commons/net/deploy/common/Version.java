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

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Version.java
 *   -. Package    : org.apache.commons.net.deploy.common
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 11. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class Version {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(Version.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * initialization
	 */
	private static final String STR_VERSION = "VER_2016-03-08_10:12:25 [INIT]";
	
	private Version() throws Exception {
		
		if (flag) {
			
		}
	}
	
	public String getVersion() throws Exception {
		return Version.STR_VERSION;
	}
	
	public void printVersion() throws Exception {
		System.out.println(">>>>> " + Version.STR_VERSION + " <<<<<");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static Version instance = null;
	
	public static synchronized Version getInstance() throws Exception {
		
		if (instance == null) {
			instance = new Version();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			log.debug("> " + Version.getInstance().getVersion() + ".");
		}
	}

	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
	}
}
