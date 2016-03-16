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
package org.apache.commons.net.deploy.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ResourceBundle;

import org.apache.commons.net.deploy.common.ParamMap;
import org.apache.commons.net.deploy.common.Version;
import org.apache.commons.net.deploy.util.CheckDeployKey;
import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TainServerMain.java
 *   -. Package    : org.apache.commons.net.deploy.server
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class TainServerMain {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TainServerMain.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static String className = null;
	private static ResourceBundle resourceBundle = null;
	
	private static String port = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void init() throws Exception {
		
		// print version
		if (flag) Version.getInstance().printVersion();
		
		// check the deploy key
		if (!flag) CheckDeployKey.getInstance().check();

		if (flag) {
			className = new Object(){}.getClass().getEnclosingClass().getName();
			
			resourceBundle = ResourceBundle.getBundle(className.replace('.', '/'));
			
			port = resourceBundle.getString("tain.listen.port");
		}
		
		if (flag) ParamMap.getInstance().printList();
		
		if (flag) {
			log.debug(">>>>> " + className);
			log.debug(">>>>> port = " + port);
		}
	}
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			/*
			 * 1st socket program
			 */
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
			if (flag) log.debug(String.format("SERVER : listening by port %s [%s]", port, serverSocket.toString()));
			
			for (int idxThr = 0; ; idxThr ++) {
				if (idxThr > 100000000)
					idxThr = 0;
				
				Socket socket = serverSocket.accept();
				if (flag) log.debug(String.format("SERVER : accept the connection(%d)", idxThr));
				
				Thread thr = new TainServerThread(idxThr, socket);
				thr.start();
				thr.join();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) init();
		
		if (flag) test01(args);
	}
}
