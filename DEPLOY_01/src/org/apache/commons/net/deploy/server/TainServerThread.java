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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ResourceBundle;

import org.apache.commons.net.deploy.common.PacketHeader;
import org.apache.commons.net.deploy.server.tr.TR0001;
import org.apache.commons.net.deploy.server.tr.TR0101;
import org.apache.commons.net.deploy.server.tr.TR0201;
import org.apache.commons.net.deploy.server.tr.TR0501;
import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TainServerThread.java
 *   -. Package    : org.apache.commons.net.deploy.server
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class TainServerThread extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TainServerThread.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private String className = null;
	private ResourceBundle resourceBundle = null;
	
	private int idxThr = -1;
	private Socket socket = null;
	
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private void init() throws Exception {
		
		if (flag) {
			this.className = new Object(){}.getClass().getEnclosingClass().getName();
			
			this.resourceBundle = ResourceBundle.getBundle(this.className.replace('.', '/'));
		}
		
		if (flag) {
			log.debug(">>>>> " + this.className);
			log.debug(">>>>> " + this.resourceBundle.getString("tain.comment"));
		}
	}
	
	public TainServerThread(int idxThr, Socket socket) throws Exception {
		
		if (flag) init();
		
		if (flag) {
			this.idxThr = idxThr;
			this.socket = socket;
			this.dis = new DataInputStream(this.socket.getInputStream());
			this.dos = new DataOutputStream(this.socket.getOutputStream());
			
			if (flag) log.debug(String.format("%s : ########## START idxThr=%d ########## socket=%s ", this.getName(), this.idxThr, this.socket.toString()));
		}
	}
	
	public void run() {
		
		if (flag) {
			/*
			 * 2nd transaction logic
			 */
			try {
				
				byte[] header = null;

				if (flag) {
					/*
					 * 1. recv header
					 */
					
					header = recv(PacketHeader.getLength());
					if (flag) log.debug(String.format("<- 1. REQ RECV HEADER [%s]", new String(header)));
				}
				
				if (flag) {
					/*
					 * process for the request and then make a result for response
					 */
					String trCode = PacketHeader.TR_CODE.getString(header);
					if (flag) log.debug("> TR_CODE = " + trCode);

					// java 1.7 or higher
					switch (trCode) {
					case "TR0000": new TR0001(this.socket, this.dis, this.dos, header).execute(); break;
					case "TR0100": new TR0101(this.socket, this.dis, this.dos, header).execute(); break;
					case "TR0200": new TR0201(this.socket, this.dis, this.dos, header).execute(); break;
					case "TR0500": new TR0501(this.socket, this.dis, this.dos, header).execute(); break;
					default:
						PacketHeader.RET_CODE.setVal(header, "99999");
						PacketHeader.RET_MSG.setVal(header, "NO_TR_CODE");
						dos.write(header, 0, header.length);
						if (flag) log.debug("[" + new String(header) + "]");
						break;
					}
				}
				
				if (!flag) {
					/*
					 * finish
					 */
					
					try { Thread.sleep(1000); } catch (InterruptedException e) {}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (this.dis != null) try { this.dis.close(); } catch (Exception e) {}
				if (this.dos != null) try { this.dos.close(); } catch (Exception e) {}
				if (this.socket != null) try { this.socket.close(); } catch (Exception e) {}
			}
		}
		
		if (flag) {
			if (flag) log.debug(String.format("%s : ########## FINISH idxThr=%d ##########\n\n", this.getName(), this.idxThr));
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
