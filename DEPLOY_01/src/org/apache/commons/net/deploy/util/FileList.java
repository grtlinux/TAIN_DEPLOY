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

import java.io.File;
import java.io.FileFilter;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : FileList.java
 *   -. Package    : tain.kr.com.test.deploy.v01.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class FileList {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(FileList.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static void print(final File folder) throws Exception {
		
		if (flag) {
			File[] files = null;
			
			try {
				files = folder.listFiles();
				
				if (flag) {
					/*
					 * print files
					 */
					for (File f : files) {
						if (f.isDirectory()) {
							log.debug("directory > " + f);
							print(f);
						} else {
							log.debug("     file > " + f);
						}
					}
				}

			} catch (Exception e) {
				throw e;
			} finally {
				
			}
		}
	}

	public static void printList(final String pathName, final String exceptName) throws Exception {
		
		if (flag) {
			
			File folder = new File(pathName);
			
			File[] files = null;
			
			try {
				/*
				 * search non delete folder
				 */
				files = folder.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						
						if (flag) {
							// get name of file
							String name = file.getName();
							
							if (exceptName.equals(name)) {
								return false;
							}
						}

						return true;
					}
				});
				
				if (flag) {
					/*
					 * print files
					 */
					for (File f : files) {
						if (f.isDirectory()) {
							log.debug("directory > " + f);
							print(f);
						} else {
							log.debug("     file > " + f);
						}
					}
				}

			} catch (Exception e) {
				throw e;
			} finally {
				
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static void test01(String[] args) throws Exception {
		
		if (flag) {
			String pathName = "N:/TEMP/app01/sas_webapps/sas.emartcms.war.arrange";
			String exceptName = "FILES";
			
			FileList.printList(pathName, exceptName);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		if (flag) log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());
		
		if (flag) test01(args);
	}
}
