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


/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : LicenseType.java
 *   -. Package    : org.apache.commons.net.deploy.license
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 3. 2. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public enum LicenseType {

	KEY_1 ("1", "9"  ),
	KEY_2 ("2", "8"  ),
	KEY_3 ("3", "7"  ),
	KEY_4 ("4", "6"  ),
	KEY_5 ("5", "5"  ),
	KEY_6 ("6", "4"  ),
	KEY_7 ("7", "3"  ),
	KEY_8 ("8", "2"  ),
	KEY_9 ("9", "1"  ),
	KEY_A ("A", "0"  ),
	KEY_B ("B", "11" ),
	KEY_C ("C", "12" ),
	KEY_K ("K", "-20"),
	KEY_X ("X", "."  ),
	KEY_Y ("Y", "."  ),
	KEY_Z ("Z", "."  ),
	;
	
	private String key;
	private String val;
	
	LicenseType(String key, String val) {
		this.key = key;
		this.val = val;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	// private static boolean flag = true;

	// private static final Logger log = Logger.getLogger(LicenseType.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

}
