package com.bbk.iplan.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;
import com.bbk.iplan.model.HomeworkInfo;

/**
 *         <pre>
 * create table homework
 * (
 *  homework_id          integer not null,
 *  name                 varchar(1024) not null,
 *  description          varchar(1024),
 *  subject_id           int not null,
 *  level                int not null,
 *  deadline             date not null,
 *  partner              varchar(1024),
 *  primary key (homework_id)
 * );
 * </pre>
 * 
 */
public class HomeWorkTable implements TableCreateInterface
{

	public static final String TABLE_NAME = "homework";

	private static final String HOMEWORK_ID = "_id"; // homework_id
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String SUBJECT_ID = "subject_id";
	private static final String LEVEL = "level";
	private static final String DEALLINE = "deadline";
	private static final String PARTNER = "partner";
	private static final String LOCAL_TIME = "local_time";

	
	/**
	 * 
	 * @param homeworkId
	 * @param date 2013/9/9, date 
	 * @return
	 */
	public static List<HomeworkInfo> readHomeworkInfo(int homeworkId, long date)
	{

		List<HomeworkInfo> temp = new ArrayList<HomeworkInfo>();

		SQLiteDatabase db = IPlanApplication.getDataBaseHelper()
				.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, new String[]
		{ HomeWorkTable.HOMEWORK_ID }, null, null, null, null, null);

		HomeworkInfo homeworkInfo = null; 
		String localTime = null;
		Date bufferTime = null;
		while (cursor.moveToNext())
		{
			homeworkInfo = new HomeworkInfo();
			
			//添加作业的时间
			bufferTime = new Date(cursor.getString(cursor.getColumnIndexOrThrow(HomeWorkTable.LOCAL_TIME)));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(bufferTime);
			
			Calendar localCalendar = Calendar.getInstance();
			localCalendar.setTimeInMillis(date);
			
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			
			int localYear = localCalendar.get(Calendar.YEAR);
			int localMonth = localCalendar.get(Calendar.MONTH);
			int localDay = localCalendar.get(Calendar.DAY_OF_MONTH);
			
			//是今天的作业
			if(year == localYear && month == localMonth && day == localDay)
			{
				homeworkInfo.setEndTime(new Date(cursor.getString(cursor.getColumnIndexOrThrow(HomeWorkTable.LOCAL_TIME))));
				homeworkInfo.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(HomeWorkTable.LEVEL)));
				homeworkInfo.setMark(cursor.getString(cursor.getColumnIndexOrThrow(HomeWorkTable.DESCRIPTION)));
				homeworkInfo.setStartTime(bufferTime);
			}
			
		}

		if (cursor != null)
		{
			cursor.close();
			db.close();
		}

		return null;
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{

		String sql = "CREATE TABLE " + HomeWorkTable.TABLE_NAME + " ( "
				+ HomeWorkTable.HOMEWORK_ID
				+ " integer primary key autoincrement, " + HomeWorkTable.NAME
				+ " varchar(1024) not null, " + HomeWorkTable.DESCRIPTION
				+ " varchar(1024), " + HomeWorkTable.SUBJECT_ID
				+ " int not null," + HomeWorkTable.LEVEL + " int not null,"
				+ HomeWorkTable.DEALLINE + " date not null,"
				+ HomeWorkTable.PARTNER + " varchar(1024)" + " );";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

		if (oldVersion < newVersion)
		{
			String sql = "DROP TABLE IF EXISTS " + HomeWorkTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}

	}

}
