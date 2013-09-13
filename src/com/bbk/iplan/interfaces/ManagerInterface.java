package com.bbk.iplan.interfaces;

import android.content.ContentValues;

/** 
 * @Title: ManagerInterface.java 
 * @Package com.bbk.iplan.interfaces 
 * @Description: 管理器接口 
 * @author A18ccms A18ccms_gmail_com 
 * @date 2013-9-12 下午02:32:42 
 * @version V1.0 
 */
public interface ManagerInterface {
	public  boolean DeleteInfo(int id);
	public  void ModifyInfo(int id,ContentValues contentValues); 
}
