package com.bbk.iplan.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;


public class WeekTimeTable implements TableCreateInterface
{

	public static final String TABLE_NAME = "week_time";

	private static final String WEEK_TIME_ID = "_id"; // vacation_id
	private static final String NAME = "name";

	private static WeekTimeTable mWeekPlanTable = new WeekTimeTable();

	private WeekTimeTable()
	{

	}

	public static WeekTimeTable getInstance()
	{
		return mWeekPlanTable;
	}

	private static void insert(SQLiteDatabase db, String name) {
		
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		db.insert(TABLE_NAME, null, values);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE " + WeekTimeTable.TABLE_NAME + " ( "
				+ WeekTimeTable.WEEK_TIME_ID
				+ " integer primary key autoincrement, "
				+ WeekTimeTable.NAME + "  varchar(1024)" + " );";
		db.execSQL(sql);
		
		insert(db, "星期日");
		insert(db, "星期一");
		insert(db, "星期二");
		insert(db, "星期三");
		insert(db, "星期四");
		insert(db, "星期五");
		insert(db, "星期六");
		
		
		
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

		if (oldVersion < newVersion)
		{
			String sql = "DROP TABLE IF EXISTS " + WeekTimeTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}

	}

}
