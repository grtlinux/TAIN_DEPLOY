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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ParamHash.java
 *   -. Package    : tain.kr.com.test.deploy.v01.common
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 29. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class ParamMap {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ParamMap.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private Map<String, String> paramMap = null;
	
	private ParamMap() throws Exception {
		
		if (this.paramMap == null) {
			this.paramMap = new HashMap<String, String>();
			
			/*
			 * initialize base keys and values
			 */
			this.paramMap.put("tain.author", "Kiea_Seok_Kang");
			this.paramMap.put("tain.company", "TAIN,Inc.");
			this.paramMap.put("tain.version", "ver_1.0_29_Feb_2016");
			this.paramMap.put("tain.serial.key", "918X942YAAAZAA2K94C8");
			
			/*
			 * date format
			 */
			this.paramMap.put("tain.date.format", System.getProperty("tain.date.format"));

			/*
			 * client keys and values
			 */
			this.paramMap.put("tain.client.host", System.getProperty("tain.client.host"));
			this.paramMap.put("tain.client.port", System.getProperty("tain.client.port"));

			this.paramMap.put("tain.client.deploy.file.name", System.getProperty("tain.client.deploy.file.name"));
			
			this.paramMap.put("tain.client.exec.cmd", System.getProperty("tain.client.exec.cmd"));
			this.paramMap.put("tain.client.exec.log", System.getProperty("tain.client.exec.log"));
			
			/*
			 * server keys and values
			 */
			this.paramMap.put("tain.server.host", System.getProperty("tain.server.host"));
			this.paramMap.put("tain.server.port", System.getProperty("tain.server.port"));

			this.paramMap.put("tain.server.deploy.file.name", System.getProperty("tain.server.deploy.file.name"));
			
			this.paramMap.put("tain.server.exec.cmd", System.getProperty("tain.server.exec.cmd"));
			this.paramMap.put("tain.server.exec.log", System.getProperty("tain.server.exec.log"));
		}
	}
	
	public void put(String key, String val) throws Exception {
		
		if (flag) {
			this.paramMap.put(key, val);
		}
	}
	
	public String get(String key) throws Exception {
		
		String val = null;
		
		if (flag) {
			val = this.paramMap.get(key);
		}
		
		return val;
	}
	
	public void printList() throws Exception {
		
		if (flag) {
			for (Map.Entry<String, String> entry : this.paramMap.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				
				if (flag) log.debug(" paramMap (" + key + "=" + val +")");
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static ParamMap instance = null;
	
	public static ParamMap getInstance() throws Exception {
		
		if (flag) {
			if (ParamMap.instance == null) {
				ParamMap.instance = new ParamMap();
			}
		}
		
		return ParamMap.instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			ParamMap.getInstance().put("param1", "param1val");
			ParamMap.getInstance().put("param2", "param2val");
			ParamMap.getInstance().put("param3", "param3val");
			ParamMap.getInstance().put("param4", "param4val");
			ParamMap.getInstance().put("param5", "param5val");
			ParamMap.getInstance().put("param6", "param6val");
			
			ParamMap.getInstance().printList();
		}
		
		if (flag) {
			log.debug(String.format("> %s => %s", "param1", ParamMap.getInstance().get("param1")));
			log.debug(String.format("> %s => %s", "param2", ParamMap.getInstance().get("param2")));
			log.debug(String.format("> %s => %s", "param3", ParamMap.getInstance().get("param3")));
			log.debug(String.format("> %s => %s", "param4", ParamMap.getInstance().get("param4")));
			log.debug(String.format("> %s => %s", "param5", ParamMap.getInstance().get("param5")));
			log.debug(String.format("> %s => %s", "param6", ParamMap.getInstance().get("param6")));
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">" + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
	}
}
