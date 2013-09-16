package com.bbk.iplan.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;
import com.bbk.iplan.interfaces.SubjectInterface;
import com.bbk.iplan.utils.Utils;

public class SubjectTable implements TableCreateInterface,SubjectInterface
{

	public static final String TABLE_NAME = "subject";

	public static final String SUBJECT_ID = "_id"; // subject_id

	public static final String EVENT_ID = "event_id";
	public static final String VOCATION_ID = "vocation_id";
	public static final String NAME = "name";
	public static final String START_TIME = "start_time";
	public static final String END_TIME = "end_time";
	public static final String PLACE = "place";
	public static final String TEACHER = "teacher";
	public static final String REMIND_TIME = "remind_time";
	public static final String MARK = "mark";

	private static SubjectTable mSubjectTable = new SubjectTable();

	private SubjectTable()
	{

	}

	public static SubjectTable getInstance()
	{
		return mSubjectTable;
	}

	public static String getSubjectName(int subjectId)
	{

		String name = null;
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, new String[]
		{ SubjectTable.NAME }, " _id=?", new String[]
		{ String.valueOf(subjectId) }, null, null, null);

		while (cursor.moveToNext())
		{
			name = cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.NAME));
		}

		if (cursor != null)
		{
			cursor.close();
		}

		return name;

	}

	public static List<SubjectInfo> getSubjectInfoByVocaitonId(int vocationId)
	{
		List<SubjectInfo> list = new ArrayList<SubjectInfo>();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, VOCATION_ID + "=?", new String[] {String.valueOf(vocationId)}, null, null, null);
		
		SubjectInfo subjectInfo = null;
		while(cursor.moveToNext())
		{
			subjectInfo = new SubjectInfo();
			subjectInfo.setID(cursor.getInt(cursor.getColumnIndexOrThrow(SUBJECT_ID)));
			subjectInfo.setMark((cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.MARK))));
			subjectInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.NAME)));
			subjectInfo.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.PLACE)));
			subjectInfo.setTeacher(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.TEACHER)));
			subjectInfo.setNotifyTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.END_TIME))));
			subjectInfo.setTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.START_TIME))));
			subjectInfo.setTermInfo(VacationTable.getTermInfoById(vocationId));
			
			list.add(subjectInfo);
			
		}
		
		if(cursor != null)
		{
			cursor.close();
		}
		
		return list;
		
	}
	
	public static int getSubjectId(String subjectName)
	{

		int id = -1;
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper()
				.getReadableDatabase();

		Cursor cursor = db.query(SubjectTable.TABLE_NAME, new String[]
		{ SubjectTable.SUBJECT_ID }, " name=?", new String[]
		{ subjectName }, null, null, null);

		if (cursor.moveToNext())
		{
			id = cursor.getInt(cursor
					.getColumnIndexOrThrow(SubjectTable.SUBJECT_ID));
		}

		if (cursor != null)
		{
			cursor.close();
		}

		return id;

	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE " + SubjectTable.TABLE_NAME + " ( "
				+ SubjectTable.SUBJECT_ID
				+ " integer primary key autoincrement, " + SubjectTable.NAME
				+ " varchar(1024) not null, " + SubjectTable.EVENT_ID
				+ " int not null, " + SubjectTable.VOCATION_ID
				+ " int not null, " + SubjectTable.START_TIME
				+ " long not null, " + SubjectTable.END_TIME
				+ " long not null, " + SubjectTable.PLACE + " varchar(1024), "
				+ SubjectTable.REMIND_TIME + " long, "
				+ SubjectTable.MARK+ " varchar(1024), "
				+ SubjectTable.TEACHER+ " varchar(1024)" + " );";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

		if (oldVersion < newVersion)
		{
			String sql = "DROP TABLE IF EXISTS " + SubjectTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}

	}

	public static SubjectInfo getSubjectInfoById(int subjectId)
	{
		SubjectInfo subjectInfo = new SubjectInfo();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, "_id=?", new String[] {String.valueOf(subjectId)}, null, null, null);
		
		if(cursor.moveToNext())
		{
			subjectInfo.setID(cursor.getInt(cursor.getColumnIndexOrThrow(SUBJECT_ID)));
			subjectInfo.setMark((cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.MARK))));
			subjectInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.NAME)));
			subjectInfo.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.PLACE)));
			subjectInfo.setTeacher(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.TEACHER)));
			subjectInfo.setNotifyTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.END_TIME))));
			subjectInfo.setTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.START_TIME))));
		}
		
		int vocationId = cursor.getInt(cursor.getColumnIndexOrThrow(VOCATION_ID));
		TermInfo termInfo = VacationTable.getTermInfoById(vocationId);
		subjectInfo.setTermInfo(termInfo);
		
		if(cursor != null)
		{
			cursor.close();
		}
		
		return subjectInfo;
	}
	
	public List<SubjectInfo> getAllSubject() {
	
		List<SubjectInfo> list = new ArrayList<SubjectInfo>();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		SubjectInfo subjectInfo = null;
		
		while(cursor.moveToNext())
		{
			subjectInfo = new SubjectInfo();
			subjectInfo.setID(cursor.getInt(cursor.getColumnIndexOrThrow(SUBJECT_ID)));
			subjectInfo.setMark((cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.MARK))));
			subjectInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.NAME)));
			subjectInfo.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.PLACE)));
			subjectInfo.setTeacher(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.TEACHER)));
			subjectInfo.setNotifyTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.END_TIME))));
			subjectInfo.setTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.START_TIME))));
			list.add(subjectInfo);
			
		}
		
		if(cursor != null)
		{
			cursor.close();
		}
		
		return list;
	}

	@Override
	public List<SubjectInfo> getLocalSubject(Date time) {
		
		List<SubjectInfo> list = new ArrayList<SubjectInfo>();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		
		Date startDate = null;
		Date endDate = null;
		SubjectInfo subjectInfo = null;
		
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		
		while(cursor.moveToNext())
		{
			startDate = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.START_TIME)));
			endDate = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.END_TIME)));
			subjectInfo = new SubjectInfo();
			if(Utils.isSubjectDate(startDate, endDate, time))
			{
				subjectInfo.setID(cursor.getInt(cursor.getColumnIndexOrThrow(SUBJECT_ID)));
				subjectInfo.setMark((cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.MARK))));
				subjectInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.NAME)));
				subjectInfo.setPlace(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.PLACE)));
				subjectInfo.setTeacher(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.TEACHER)));
				subjectInfo.setNotifyTime(endDate);
				subjectInfo.setTime(startDate);
				list.add(subjectInfo);
			}
		}
		
		if(cursor != null)
		{
			cursor.close();
		}
		
		return list;
	}
	
	@Override
	public boolean DeleteSubjectInfo(int id) {
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		try {
			db.delete(TABLE_NAME, "_id=?", new String[] {String.valueOf(id)});
			SubjectWeekTimeTable.remove(id);
		} catch (Exception e) {
			return false;
		}
		
		return true;
		
	}

	@Override
	public SubjectInfo CreateLongSubject(String name, Date time,
			int[] week_time, Date notifytime, String place, String teacher,
			String mark, TermInfo termInfo) {
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper()
				.getWritableDatabase();
		
		SubjectInfo subjectInfo = new SubjectInfo();
		subjectInfo.setMark(mark);
		subjectInfo.setName(name);
		subjectInfo.setNotifyTime(notifytime);
		subjectInfo.setTime(time);
		subjectInfo.setPlace(place);
		subjectInfo.setTeacher(teacher);

		ContentValues values = new ContentValues();

		values.put(END_TIME, notifytime.getTime());
		values.put(MARK, mark);
		values.put(START_TIME, time.getTime());
		values.put(NAME, name);
		values.put(PLACE, place);
		values.put(TEACHER, teacher);
		
		/* 
		 * 选择合适的学期
		 */
		long vocationStartTime = termInfo.getStartTime();
		long vocationEndTime = termInfo.getEndTime();
		
		String sql = "select * from " + VacationTable.TABLE_NAME + " where "
				+ VacationTable.START_TIME +" <= " + String.valueOf(vocationStartTime) 
				+" and " + VacationTable.END_TIME + " >= " + String.valueOf(vocationEndTime);
		
		Cursor cursor  = db.rawQuery(sql, null);
		int vocationId = 0;
		if(cursor.moveToNext())
		{
			vocationId = cursor.getInt(cursor.getColumnIndexOrThrow(VacationTable.VACATION_ID));
		}
		
		if(cursor != null)
		{
			cursor.close();
		}
		values.put(VOCATION_ID, vocationId);
		
		/*
		 * 添加event_id,长期课为1，单次课为2，考试是3
		 */
		values.put(EVENT_ID, 1);
		
		db.insert(TABLE_NAME, null, values);
		
		int subjectId = 0;
		subjectInfo.setID(subjectId = Utils.getMaxId(TABLE_NAME));
		subjectInfo.setTermInfo(termInfo);
		
		//更新subject_week_time表
		for(int i = 0; i < 6; i++ )
		{
			if(week_time[i] == 1)
			{
				SubjectWeekTimeTable.insert(i + 1, subjectId);
			}
		}
		subjectInfo.setWeek_time(week_time);
		
		return subjectInfo;
	}

	@Override
	public List<SubjectInfo> getAllSubject(int ID) {
		return getSubjectInfoByVocaitonId(ID);
	}

	@Override
	public void modifySubjectInfo(int id, ContentValues contentValues) {
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		db.update(TABLE_NAME, contentValues, "_id=?", new String[] {String.valueOf(id)});
	}

}
