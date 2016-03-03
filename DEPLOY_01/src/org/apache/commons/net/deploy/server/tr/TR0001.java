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
package org.apache.commons.net.deploy.server.tr;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import org.apache.commons.net.deploy.common.PacketHeader;
import org.apache.commons.net.deploy.common.ParamMap;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TR0001.java
 *   -. Package    : org.apache.commons.net.deploy.server.tr
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@SuppressWarnings("unused")
public class TR0001 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TR0001.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private String className = null;
	private String trCode = null;
	private ResourceBundle resourceBundle = null;
	private String comment = null;

	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	private byte[] header = null;

	private byte[] body = null;
	private int bodyLen = 0;
	
	private String dateFormat = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public TR0001(Socket socket, DataInputStream dis, DataOutputStream dos, byte[] header) throws Exception {
		
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
			this.dateFormat = ParamMap.getInstance().get("tain.date.format");
			if (this.dateFormat == null) {
				this.dateFormat = this.resourceBundle.getString("tain.date.format");
			}
		}
		
		if (flag) {
			/*
			 * hired parameter
			 */
			this.socket = socket;
			this.dis = dis;
			this.dos = dos;
			this.header = header;
			
			this.bodyLen = Integer.parseInt(PacketHeader.BODY_LEN.getString(this.header));
		}
		
		if (flag) {
			/*
			 * print information
			 */
			log.debug(">>>>> " + this.className);
			log.debug(">>>>> " + this.comment);
			log.debug(">>>>> trCode = " + this.trCode);
		}
	}
	
	public void execute() throws Exception {
		
		if (flag) {
			/*
			 * 2. recv data
			 */

			this.body = recv(this.bodyLen);
			if (flag) log.debug(String.format("<- 2. REQ RECV DATA   [%s]", new String(this.body)));
		}
		
		if (flag) {
			/*
			 * 3. execute job
			 */
			
			if (flag) {
				// make return body
				
				this.body = "00000000-000000".getBytes("EUC-KR");   // TODO 2016.0302 : for test
				if (flag) this.body = new SimpleDateFormat(this.dateFormat, Locale.KOREA).format(new Date()).getBytes("EUC-KR");
				
				this.bodyLen = this.body.length;

				if (flag) log.debug(String.format("-- 3. DATA [%d:%s]", this.bodyLen, new String(this.body)));
			}
		}
		
		if (flag) {
			/*
			 * 4. send header
			 */

			this.header = PacketHeader.makeBytes();
			PacketHeader.TR_CODE.setVal(this.header, this.trCode);
			PacketHeader.BODY_LEN.setVal(this.header, String.valueOf(this.bodyLen));
			PacketHeader.RET_CODE.setVal(this.header, "00000");
			PacketHeader.RET_MSG.setVal(this.header, "SUCCESS");
			
			this.dos.write(this.header, 0, this.header.length);
			if (flag) log.debug(String.format("-> 4. RES SEND HEADER [%s]", new String(this.header)));
		}
		
		if (flag) {
			/*
			 * 5. send data
			 */

			this.dos.write(this.body, 0, this.bodyLen);
			if (flag) log.debug(String.format("-> 5. RES SEND DATA   [%s]", new String(this.body)));
		}
		
		if (flag) {
			/*
			 * 6. post job
			 */
			
			if (flag) log.debug(String.format("-- 6. [%s] process is OK!!", this.trCode));
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
