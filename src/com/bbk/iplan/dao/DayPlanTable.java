package com.bbk.iplan.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;

/**
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
	private static final String HOMEWORK_SUBJECT_ID = "homework_subject_id";
	
	/**
	 * 读HomeworkSubjectID
	 * @return
	 */
	public static List<Integer> readHomeworkSubject()
	{
		
		List<Integer> temp = new ArrayList<Integer>();
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_NAME, 
				new String[]{DayPlanTable.HOMEWORK_SUBJECT_ID}, null, null, null, null, null);
		
		while (cursor.moveToNext())
		{
			temp.add(cursor.getInt(cursor.getColumnIndexOrThrow(DayPlanTable.HOMEWORK_SUBJECT_ID)));
		}
		
		if(cursor != null)
		{
			cursor.close();
			db.close();
		}
		
		return temp;
		
	}
	
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql = "CREATE TABLE "
				+ DayPlanTable.TABLE_NAME 
				+ " ( "
				+ DayPlanTable.DAY_PLAN_ID + " integer primary key autoincrement, "	
				+ DayPlanTable.HOMEWORK_SUBJECT_ID + " integer"
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
