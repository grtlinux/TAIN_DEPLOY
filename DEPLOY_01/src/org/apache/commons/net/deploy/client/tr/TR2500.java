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
package org.apache.commons.net.deploy.client.tr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import org.apache.commons.net.deploy.common.PacketHeader;
import org.apache.commons.net.deploy.common.ParamMap;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TR0000.java
 *   -. Package    : org.apache.commons.net.deploy.client.tr
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class TR2500 extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TR2500.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private String className = null;
	private String trCode = null;
	private ResourceBundle resourceBundle = null;
	private String comment = null;

	private String host = null;
	private String port = null;
	
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public TR2500() throws Exception {
		
		if (flag) {
			/*
			 * base parameter
			 */
			this.className = this.getClass().getName();
			this.trCode = this.className.substring(this.className.lastIndexOf("TR"));
			this.resourceBundle = ResourceBundle.getBundle(this.className.replace('.', '/'));
			this.comment = this.resourceBundle.getString("tain.comment");
		}
		
		if (flag) {
			/*
			 * parameters
			 */
			this.host = ParamMap.getInstance().get("tain.server.app02.host");
			if (this.host == null) {
				this.host = this.resourceBundle.getString("tain.server.app02.host");
			}

			this.port = ParamMap.getInstance().get("tain.server.app02.port");
			if (this.port == null) {
				this.port = this.resourceBundle.getString("tain.server.app02.port");
			}
		}
		
		if (flag) {
			/*
			 * hired parameter
			 */
			this.socket = new Socket(this.host, Integer.parseInt(this.port));
			this.dis = new DataInputStream(this.socket.getInputStream());
			this.dos = new DataOutputStream(this.socket.getOutputStream());
		}
		
		if (flag) {
			/*
			 * print information
			 */
			log.debug(">>>>> " + this.className);
			log.debug(">>>>> " + this.comment);
			log.debug(">>>>> host = " + this.host);
			log.debug(">>>>> port = " + this.port);
			log.debug(">>>>> trCode = " + this.trCode);
			log.debug("Connection .....");
		}
	}
	
	public void run() {
		
		if (flag) {
			/*
			 * TODO : version 0.2 at 2016.02.29
			 *     
			 *     1. pre job
			 *     
			 *       2. send header
			 *       
			 *         3. send data
			 *         
			 *           4. execute job
			 *           
			 *         5. recv header
			 *         
			 *       6. recv data
			 *       
			 *     7. post job
			 *     
			 */
			try {
				
				byte[] header = null;
				byte[] data = null;
				int dataLen = 0;
				
				if (flag) {
					/*
					 * 1. pre job
					 */
					
					data = String.format("%s;%s", "RUN_TR0501", ParamMap.getInstance().get("tain.deploy.time")).getBytes("EUC-KR");
					dataLen = data.length;
					
					if (flag) log.debug(String.format("-- 1. DATA [%d:%s]", dataLen, new String(data)));
				}
				
				if (flag) {
					/*
					 * 2. send header
					 */
					
					header = PacketHeader.makeBytes();
					PacketHeader.TR_CODE.setVal(header, trCode);
					PacketHeader.BODY_LEN.setVal(header, String.valueOf(dataLen));
					
					this.dos.write(header, 0, header.length);
					if (flag) log.debug(String.format("-> 2. REQ SEND HEADER [%s]", new String(header)));
				}
				
				if (flag) {
					/*
					 * 3. send data
					 */
					
					this.dos.write(data, 0, dataLen);
					if (flag) log.debug(String.format("-> 3. REQ SEND DATA   [%s]", new String(data)));
				}
				
				if (flag) {
					/*
					 * 4. execute job
					 */
					
					if (flag) log.debug(String.format("-- 4. don't execute local job"));
				}
				
				if (flag) {
					/*
					 * 5. recv header
					 */
					
					header = recv(header.length);
					if (flag) log.debug(String.format("<- 5. RES RECV HEADER [%s]", new String(header)));
					
					dataLen = Integer.parseInt(PacketHeader.BODY_LEN.getString(header));
				}
				
				if (flag) {
					/*
					 * 6. recv data
					 */
					
					data = recv(dataLen);
					if (flag) log.debug(String.format("<- 6. RES RECV DATA   [%s]", new String(data)));
				}
				
				if (flag) {
					/*
					 * 7. post job
					 */
					
					if (flag) log.debug(String.format("-- 7. DATA [%d:%s]", dataLen, new String(data)));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (this.dis != null) try { this.dis.close(); } catch (Exception e) {}
				if (this.dos != null) try { this.dos.close(); } catch (Exception e) {}
				if (this.socket != null) try { this.socket.close(); } catch (Exception e) {}
			}
		}
	}
	
	private byte[] recv(final int size) throws Exception {
		
		int ret = 0;
		int readed = 0;
		byte[] buf = new byte[size];
		
		this.socket.setSoTimeout(0);
		while (readed < size) {
			ret = this.dis.read(buf, readed, size - readed);
			if (!flag) log.debug("    size:" + size + "    readed:" + readed + "     ret:" + ret);
			
			if (ret <= 0) {
				try { Thread.sleep(1000); } catch (Exception e) {}
				continue;
			} else {
				if (flag) this.socket.setSoTimeout(1000);
			}
			
			readed += ret;
		}
		
		return buf;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
}
