package com.bbk.iplan.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;


/**
 * �¼���
 * @author �ƺ��
 * 
 * <pre>
 * create table event 
 *	(
 *	   event_id             integer not null,
 *	   name                 varchar(1024) not null,
 *	   primary key (event_id)
 *	);
 * </pre>
 * 
 */
public class EventTable implements TableCreateInterface{

	public static final String TABLE_NAME = "event";
	
	private static final String EVENT_ID = "_id"; //event_id
	private static final String NAME = "name";
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql = "CREATE TABLE "
				+ EventTable.TABLE_NAME 
				+ " ( "
				+ EventTable.EVENT_ID + " integer primary key autoincrement, "	
				+ EventTable.NAME + " varchar(1024) not null"
				+ " );";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			String sql = "DROP TABLE IF EXISTS " + EventTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}
		
	}

}
