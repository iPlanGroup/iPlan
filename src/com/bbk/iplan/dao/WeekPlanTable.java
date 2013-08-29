package com.bbk.iplan.dao;

import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;

/**
 * �ܼƻ�
 * 
 * <pre>
 * create table week_plan
 * (
 *  week_plan_id         integer not null,
 *  day_plan_id          int,
 *  primary key (week_plan_id)
 * );
 * </pre>
 * @author �ƺ��
 *
 */
public class WeekPlanTable implements TableCreateInterface{

	
	public static final String TABLE_NAME = "week_plan";
	
	private static final String WEEK_PLAN_ID = "_id"; //vacation_id
	private static final String DAY_PLAN_ID = "day_plan_id";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE "
				+ WeekPlanTable.TABLE_NAME 
				+ " ( "
				+ WeekPlanTable.WEEK_PLAN_ID + " integer primary key autoincrement, "	
				+ WeekPlanTable.DAY_PLAN_ID + " int"
				+ " );";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if (oldVersion < newVersion) {
			String sql = "DROP TABLE IF EXISTS " + WeekPlanTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}
		
	}


}
