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

import java.io.FileWriter;

import org.apache.commons.net.deploy.common.Exec;
import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ExecTestMain.java
 *   -. Package    : org.apache.commons.net.deploy.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 3. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class ExecTestMain {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ExecTestMain.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			if (CheckSystem.getInstance().isWindows()) {
				String execCmd = "dir";
				String param = "/w";
				String execLog = "N:/ExecTestMain.log";
				
				if (flag) log.debug("WINDOWS");
				if (flag) Exec.run(new String[] {"cmd", "/c", execCmd, param }, new FileWriter(execLog), true);
			} else {
				String execCmd = "ls";
				String param = "1234567890";
				String execLog = "~/ExecTestMain.log";
				
				if (flag) log.debug("LINUX");
				if (flag) Exec.run(new String[] {"/bin/sh", "-c", execCmd, param }, new FileWriter(execLog), true);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
	}
}


