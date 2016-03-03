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
package org.apache.commons.net.deploy.util;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : CheckSystem.java
 *   -. Package    : org.apache.commons.net.deploy.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 3. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class CheckSystem {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(CheckSystem.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private Properties prop = null;
	
	private CheckSystem() throws Exception {
	
		if (flag) {
			prop = System.getProperties();
		}
	}
	
	private String get(String key) throws Exception {
		
		String val = null;
		
		if (flag) {
			val = this.prop.getProperty(key);
		}
		
		return val;
	}
	
	private String get(String key, String def) throws Exception {
		
		String val = null;
		
		if (flag) {
			val = this.prop.getProperty(key, def);
		}
		
		return val;
	}
	
	public boolean isWindows() throws Exception {
		
		boolean ret = false;

		if (!flag) {
			String osName = get("os.name", "");
			if (osName.indexOf("Win", 0) >= 0) {
				return true;
			}
		}
		
		if (flag) {
			String osName = get("os.name");
			if (osName.indexOf("Win", 0) >= 0) {
				return true;
			}
		}
		
		return ret;
	}
	
	public boolean isUnix() throws Exception {
		
		return isWindows() ? false : true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static CheckSystem instance = null;
	
	public static synchronized CheckSystem getInstance() throws Exception {
		
		if (instance == null) {
			instance = new CheckSystem();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			log.debug("windows " + CheckSystem.getInstance().isWindows());
			log.debug("unix    " + CheckSystem.getInstance().isUnix());
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
	}
}
