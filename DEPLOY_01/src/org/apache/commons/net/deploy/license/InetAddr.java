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
package org.apache.commons.net.deploy.license;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : InetAddr.java
 *   -. Package    : org.apache.commons.net.deploy.license
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class InetAddr {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(InetAddr.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private String inetAddrInfo = null;
	
	private InetAddr() {
		
		if (flag) log.debug(">>>>> Object : " + this.getClass().getName());
	}
	
	public String getInfo(String expireDate) throws Exception {
	
		if (this.inetAddrInfo == null) {
			
			InetAddress inetAddress = null;
			
			try {
				inetAddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// e.printStackTrace();
				throw e;
			}
			
			if (flag) log.debug(String.format("%s, %s", inetAddress.getHostName(), inetAddress.getHostAddress()));
			
			if (!flag) {
				/*
				 * normal format : 127.0.0.1
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append((int) ip[i] & 0xFF);   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				
				if (flag) log.debug(sb.toString());
			}
			
			if (flag) {
				/*
				 * key format : 999.999.999.999
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append(String.format("%03d", (int) ip[i] & 0xFF));   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				
				if (flag) log.debug(sb.toString());
				
				this.inetAddrInfo = String.format("%s-%s", sb.toString(), expireDate);
			}
		}
		
		return this.inetAddrInfo;
	}
	
	public String getInfo() throws Exception {
		
		return getInfo("202507");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static InetAddr instance = null;
	
	public static synchronized InetAddr getInstance() throws Exception {
		
		if (flag) {
			if (InetAddr.instance == null) {
				InetAddr.instance = new InetAddr();
			}
		}
		
		return InetAddr.instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * Code Templates > Comments > Methods
	 *
	 * <PRE>
	 *   -. ClassName  : IpAddressTestMain
	 *   -. MethodName : test02
	 *   -. Comment    :
	 *   -. Author     : taincokr
	 *   -. First Date : 2016. 3. 2. {time}
	 * </PRE>
	 *
	 * @param args
	 * @throws Exception
	 * 
	 * [ OUTPUT ]
	 *     (IpAddressTestMain.java:152) - >>>>> tain.kr.com.test.IpAddress.v01.IpAddressTestMain
	 *     (IpAddressTestMain.java:73) - localhost, 127.0.0.1
	 *     (IpAddressTestMain.java:87) - 127.0.0.1
	 *     (IpAddressTestMain.java:102) - 127.000.000.001
	 *     (IpAddressTestMain.java:116) - taincokr-PC, 172.31.16.2
	 *     (IpAddressTestMain.java:130) - 172.31.16.2
	 *     (IpAddressTestMain.java:145) - 172.031.016.002
	 * 
	 */
	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			/*
			 * for test
			 */
			StringBuffer sb = new StringBuffer();
			
			byte[] ip = { -4, -3, -2, -1 };
			for (int i=0; i < ip.length; i++) {
				sb.append((int) ip[i] & 0xFF);   // bug
				if (i != ip.length - 1)
					sb.append(".");
			}
			log.debug(sb.toString());
		}
		
		if (flag) {
			
			InetAddress inetAddress = null;
			
			try {
				inetAddress = InetAddress.getLoopbackAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			log.debug(String.format("%s, %s", inetAddress.getHostName(), inetAddress.getHostAddress()));
			
			if (flag) {
				/*
				 * normal format : 127.0.0.1
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append((int) ip[i] & 0xFF);   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				log.debug(sb.toString());
			}
			
			if (flag) {
				/*
				 * key format : 999.999.999.999
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append(String.format("%03d", (int) ip[i] & 0xFF));   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				log.debug(sb.toString());
			}
		}
		
		if (flag) {
			
			InetAddress inetAddress = null;
			
			try {
				inetAddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
			log.debug(String.format("%s, %s", inetAddress.getHostName(), inetAddress.getHostAddress()));
			
			if (flag) {
				/*
				 * normal format : 127.0.0.1
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append((int) ip[i] & 0xFF);   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				log.debug(sb.toString());
			}
			
			if (flag) {
				/*
				 * key format : 999.999.999.999
				 */
				StringBuffer sb = new StringBuffer();
				
				byte[] ip = inetAddress.getAddress();
				for (int i=0; i < ip.length; i++) {
					sb.append(String.format("%03d", (int) ip[i] & 0xFF));   // bug
					if (i != ip.length - 1)
						sb.append(".");
				}
				log.debug(sb.toString());
			}
		}
	}
	
	private static void test02(String[] args) throws Exception {
		
		if (flag) {
			log.debug(">" + InetAddr.getInstance().getInfo());
			log.debug(">" + InetAddr.getInstance().getInfo("201612"));
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
		if (flag) test02(args);
	}
}
