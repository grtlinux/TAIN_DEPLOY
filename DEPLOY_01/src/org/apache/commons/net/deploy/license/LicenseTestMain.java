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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : LicenseTestMain.java
 *   -. Package    : tain.kr.com.test.license.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class LicenseTestMain {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(LicenseTestMain.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			
			/*
			 * ############################
			 *  license key
			 */
			String license = null;
			license = "918X942YAAAZAA2K94C8";    // correct [192.168.000.008-201612] 48 8
			license = "AAAX111YAAAZ111K94C9";    // correct : ignore ip check
			//license = "918X942YAAAZAA2K94C84";   // size error
			//license = "918X942YAAAZAA2K94T8";    // wrong license key
			//license = "918X942YAAAZAA1K94C7";    // wrong ip address
			//license = "918X942YAAAZAA2K94C9";    // crc error
			//license = "918X942YAAAZAA2K95C9";    // expire date error
			
			if (flag) {
				/*
				 * 1. check size of license
				 */
				if (license.length() != 20) {
					throw new Exception("ERROR : error for checking the size of license");
				}
			}
			
			String key = license.substring(0, 19);
			String crc = license.substring(19);
			if (!flag) log.debug(String.format("[%s] = [%s] [%s]", license, key, crc));

			StringBuffer sbInfo = new StringBuffer();
			int crcDigit = 0;
			
			if (flag) {
				/*
				 * 3. check characters of license key
				 */
				
				char[] chs = key.toCharArray();
				
				for (char ch : chs) {
					String strKey = "" + ch;
					String strVal = LicenseMap.getInstance().get(strKey);
					if (strVal == null) {
						throw new Exception("ERROR : error because of wrong license key");
					}
					
					if (Character.isDigit(ch)) {
						crcDigit += Integer.parseInt(strKey);
					}
					
					sbInfo.append(strVal);
				}
				
				if (!flag) log.debug(String.format("[%s] %d %d", sbInfo.toString(), crcDigit, crcDigit % 10));
			}

			if (flag) {
				/*
				 * 4. check CRC value
				 */
				if (!crc.equals(String.valueOf(crcDigit % 10))) {
					throw new Exception("ERROR : error for crc check.");
				}
			}
			
			StringBuffer sbIp = new StringBuffer();
			
			if (flag) {
				/*
				 * 5. get local ip address
				 */
				InetAddress inetAddr = null;
				
				try {
					inetAddr = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					throw e;
				}

				byte[] ip = inetAddr.getAddress();
				for (int i=0; i < ip.length; i++) {
					sbIp.append(String.format("%03d", (int) ip[i] & 0xFF));
					if (i != ip.length - 1)
						sbIp.append(".");
				}
				
				if (!flag) log.debug("real ip : " + sbIp.toString());
			}
			
			if (flag) {
				/*
				 * 6. check ip address
				 *    if sbInfo is started with 000, then skip
				 */
				
				if (!flag) log.debug(String.format("[%s] [%s]", sbInfo.substring(0, 15), sbIp));
				
				if (!"000".equals(sbInfo.substring(0, 3))) {
					// license key does not start with AAA
					if (!sbIp.toString().equals(sbInfo.substring(0, 15))) {
						throw new Exception("ERROR : error because of wrong ip address.");
					}
				}
			}
			
			if (flag) {
				/*
				 * 7. check expire date
				 */
				String[] str = sbInfo.toString().split("-");
				String yyyymm = new SimpleDateFormat("yyyyMM").format(new Date());

				if (!flag) log.debug(String.format("[%s] [%s]", str[1], yyyymm));
				
				if (yyyymm.compareTo(str[1]) > 0) {
					throw new Exception("ERROR : error out of expire date");
				}
			}
			
			if (flag) log.debug("SUCCESS : success to check license");
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
	}
}
