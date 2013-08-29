package com.bbk.iplan.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * 
 * @author �ƺ��
 * 
 * @version V1.0
 * @since 2013/8/22
 *
 */
public class IPlanDataBaseHelper extends SQLiteOpenHelper{

	
	public static final String DATABASE_NAME = "iplan_database"; 
	
	public static final int VERSION = 1;
	
	public IPlanDataBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, VERSION);
	}

	
	/**
	 * �����ӿ�
	 * ʵ�ָ���Ĵ���
	 */
	public static interface TableCreateInterface {
		
		/**
		 * ������
		 * @param db
		 */
		public void onCreate( SQLiteDatabase db );
		
		/**
		 * ���
		 * @param db
		 * @param oldVersion
		 * @param newVersion
		 */
		public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion );
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
