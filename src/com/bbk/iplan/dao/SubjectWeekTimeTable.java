package com.bbk.iplan.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;


public class SubjectWeekTimeTable implements TableCreateInterface
{

	public static final String TABLE_NAME = "subject_week_time";

	private static final String SUBJECT_WEEK_TIME_ID = "_id"; // vacation_id
	private static final String SUBJECT_ID = "subject_id";
	private static final String WEEK_TIME_ID = "week_time_id";

	private static SubjectWeekTimeTable mWeekPlanTable = new SubjectWeekTimeTable();

	private SubjectWeekTimeTable()
	{

	}

	public static SubjectWeekTimeTable getInstance()
	{
		return mWeekPlanTable;
	}

	/**
	 * 插入一条数据
	 * @param weekTimeId
	 * @param subjectId
	 */
	public static void insert(int weekTimeId, int subjectId)
	{
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(WEEK_TIME_ID, weekTimeId);
		values.put(SUBJECT_ID, subjectId);
		db.insert(TABLE_NAME, null, values);
	} 
	
	/**
	 * 移除指定subjectID的数据
	 * @param subjectId
	 */
	public static void remove(int subjectId)
	{
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		db.delete(TABLE_NAME, SUBJECT_ID + "=?", new String[] {String.valueOf(subjectId)});
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE " + SubjectWeekTimeTable.TABLE_NAME + " ( "
				+ SubjectWeekTimeTable.SUBJECT_WEEK_TIME_ID
				+ " integer primary key autoincrement, "
				+ SubjectWeekTimeTable.SUBJECT_ID + " int, " 
				+ SubjectWeekTimeTable.WEEK_TIME_ID + " int " 
				+ " );";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		
		if (oldVersion < newVersion)
		{
			String sql = "DROP TABLE IF EXISTS " + SubjectWeekTimeTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}

	}

}
