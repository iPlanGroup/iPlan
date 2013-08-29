package com.bbk.iplan.dao;

import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;

/**
 * �ռƻ���
 * @author �ƺ��
 * 
 * <pre>
 * create table day_plan
 * (
 *   day_plan_id          integer not null,
 *   event_id             int,
 *   homework_id          int,
 *   primary key (day_plan_id)
 * );
 *</pre>
 *
 */
public class DayPlanTable implements TableCreateInterface{

	
	public static final String TABLE_NAME = "day_plan";
	
	private static final String DAY_PLAN_ID = "_id"; //day_plan_id
	private static final String EVENT_ID = "event_id";
	private static final String HOMEWORK_ID = "homework_id";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql = "CREATE TABLE "
				+ DayPlanTable.TABLE_NAME 
				+ " ( "
				+ DayPlanTable.DAY_PLAN_ID + " integer primary key autoincrement, "	
				+ DayPlanTable.EVENT_ID + " integer, "
				+ DayPlanTable.HOMEWORK_ID + " integer"
				+ " );";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if (oldVersion < newVersion) {
			String sql = "DROP TABLE IF EXISTS " + DayPlanTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}
		
	}
	
	
	

}
