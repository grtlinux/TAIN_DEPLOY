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

import java.util.Scanner;

import org.apache.commons.net.deploy.common.ParamMap;
import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : CheckDeployKey.java
 *   -. Package    : org.apache.commons.net.deploy.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 11. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class CheckDeployKey {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(CheckDeployKey.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String PARAM_KEY = "tain.deploy.key";
	
	private String deployKey = null;
	
	private CheckDeployKey() throws Exception {
		
		if (flag) {
			/*
			 * get deploy key from system properties
			 */
			deployKey = System.getProperty(PARAM_KEY);
			
			if (deployKey == null) {
				deployKey = ParamMap.getInstance().get(PARAM_KEY);
			} else {
				ParamMap.getInstance().put(PARAM_KEY, deployKey);
			}
		}
	}
	
	public boolean check() throws Exception {
		
		boolean ret = false;
		
		if (flag) {
			/*
			 * check the deploy key
			 */
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.println("##### input the deploy key...");
			
			String strKey = scan.nextLine();
			
			if (!strKey.equals(deployKey)) {
				System.out.println("ERROR : don't match the deploy key...!!!");
				System.exit(-1);
			}
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static CheckDeployKey instance = null;
	
	public static synchronized CheckDeployKey getInstance() throws Exception {
		
		if (instance == null) {
			instance = new CheckDeployKey();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			CheckDeployKey.getInstance().check();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
	}
}
