package com.bbk.iplan.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;
import com.bbk.iplan.data.HomeworkInfo;
import com.bbk.iplan.interfaces.HomeworkInterface;
import com.bbk.iplan.utils.Utils;

public class HomeWorkTable implements TableCreateInterface, HomeworkInterface {

	public static final String TABLE_NAME = "homework";

	public static final String HOMEWORK_ID = "_id"; // homework_id
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String LEVEL = "level";
	public static final String DEALLINE = "deadline";
	public static final String PARTNER = "partner";
	public static final String SUBJECT_NAME = "subject_name";

	private static HomeWorkTable mHomeWorkTable = new HomeWorkTable();

	private HomeWorkTable() {

	}

	public static HomeWorkTable getInstance() {
		return mHomeWorkTable;
	}

	/**
	 * @param homeworkId
	 * @param date
	 *            2013/9/9, date
	 * @return
	 */
	public static List<HomeworkInfo> getAllHomeworkInfo(int homeworkId, long date) {

		List<HomeworkInfo> temp = new ArrayList<HomeworkInfo>();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

		HomeworkInfo homeworkInfo = null;
		while (cursor.moveToNext()) {
			homeworkInfo = new HomeworkInfo();
			homeworkInfo.setEndTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(HomeWorkTable.DEALLINE))));
			homeworkInfo.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(HomeWorkTable.LEVEL)));
			homeworkInfo.setMark(cursor.getString(cursor.getColumnIndexOrThrow(HomeWorkTable.DESCRIPTION)));
			homeworkInfo.setID(cursor.getInt(cursor.getColumnIndexOrThrow(HomeWorkTable.HOMEWORK_ID)));
			homeworkInfo.setSubjectName(SubjectTable.getSubjectName(HomeWorkSubjectTable.getSubjectId(homeworkId)));
			temp.add(homeworkInfo);
		}

		if (cursor != null) {
			cursor.close();
		}

		return temp;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE " + HomeWorkTable.TABLE_NAME + " ( "
				+ HomeWorkTable.HOMEWORK_ID
				+ " integer primary key autoincrement, " + HomeWorkTable.NAME
				+ " varchar(1024) not null, " + HomeWorkTable.DESCRIPTION
				+ " int not null, " + HomeWorkTable.LEVEL + " int not null, "
				+ HomeWorkTable.DEALLINE + " long not null,"
				+ HomeWorkTable.SUBJECT_NAME + " varchar(1024) not null, "
				+ HomeWorkTable.PARTNER + " varchar(1024)" + " );";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		if (oldVersion < newVersion) {
			String sql = "DROP TABLE IF EXISTS " + HomeWorkTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}

	}
	
	public static List<HomeworkInfo> getHomeWorkByLevel(int level)
	{
		List<HomeworkInfo> listHomework = new ArrayList<HomeworkInfo>();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		String sql = "select * from "+ TABLE_NAME + " where " + LEVEL + "=" + level + " order by desc " + DEALLINE;
		Cursor cursor = db.rawQuery(sql, null);
		HomeworkInfo homeworkInfo = null;
		int homeworkId = 0;
		while(cursor.moveToNext())
		{
			homeworkInfo = new HomeworkInfo();
			homeworkInfo.setEndTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DEALLINE))));
			homeworkInfo.setID(homeworkId = cursor.getInt(cursor.getColumnIndexOrThrow(HOMEWORK_ID)));
			homeworkInfo.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL)));
			homeworkInfo.setMark(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
			homeworkInfo.setSubjectName(HomeWorkSubjectTable.getSubjectNameForHomework(homeworkId));
			listHomework.add(homeworkInfo);
		}
		
		if(cursor != null)
		{
			cursor.close();
		}
		
		return listHomework;
	}

	public List<HomeworkInfo> getAllHomework()
	{
		List<HomeworkInfo> listHomework = new ArrayList<HomeworkInfo>();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		HomeworkInfo homeworkInfo = null;
		int homeworkId = 0;
		while(cursor.moveToNext())
		{
			homeworkInfo = new HomeworkInfo();
			homeworkInfo.setEndTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DEALLINE))));
			homeworkInfo.setID(homeworkId = cursor.getInt(cursor.getColumnIndexOrThrow(HOMEWORK_ID)));
			homeworkInfo.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL)));
			homeworkInfo.setMark(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
			homeworkInfo.setSubjectName(HomeWorkSubjectTable.getSubjectNameForHomework(homeworkId));
			listHomework.add(homeworkInfo);
		}
		
		if(cursor != null)
		{
			cursor.close();
		}
		
		return listHomework;
		
	}
	
	@Override
	public List<HomeworkInfo> getUnFinished() {
		
		List<HomeworkInfo> listHomework = new ArrayList<HomeworkInfo>();
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();

		String currentTime = String.valueOf(System.currentTimeMillis());
		String sql = "select * from " + TABLE_NAME + " where " + DEALLINE + " > " + currentTime;
		Cursor cursor = db.rawQuery(sql, null);
		HomeworkInfo homeworkInfo = null;
		int homeworkId = 0;
		while(cursor.moveToNext())
		{
			homeworkInfo = new HomeworkInfo();
			homeworkInfo.setEndTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DEALLINE))));
			homeworkInfo.setID(homeworkId = cursor.getInt(cursor.getColumnIndexOrThrow(HOMEWORK_ID)));
			homeworkInfo.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL)));
			homeworkInfo.setMark(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
			homeworkInfo.setSubjectName(HomeWorkSubjectTable.getSubjectNameForHomework(homeworkId));
			listHomework.add(homeworkInfo);
		}
		
		if(cursor != null)
		{
			cursor.close();
			db.close();
		}
		
		return listHomework;
	}

	@Override
	public List<HomeworkInfo> getLocalHomework(Date time) {
		
		List<HomeworkInfo> listHomework = getUnFinished();
		List<HomeworkInfo> lists = new ArrayList<HomeworkInfo>();
		
		for(HomeworkInfo info: listHomework)
		{
			if(Utils.isLocalDate(time, new Date(info.getEndTime())))
			{
				lists.add(info);
			}
		}
		
		return lists;
	}

	@Override
	public HomeworkInfo CreateHomework(String name, String subjectName,
			Date StartTime, Date EndTime, int Level, String Mark) {

		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();

		HomeworkInfo homeworkInfo = new HomeworkInfo();

		homeworkInfo.setEndTime(EndTime);
		homeworkInfo.setStartTime(StartTime);
		homeworkInfo.setLevel(Level);
		homeworkInfo.setMark(Mark);
		homeworkInfo.setName(name);
		homeworkInfo.setSubjectName(subjectName);

		ContentValues values = new ContentValues();

		values.put(HomeWorkTable.LEVEL, Level);
		values.put(HomeWorkTable.DEALLINE, EndTime.getTime());
		values.put(HomeWorkTable.NAME, name);
		values.put(HomeWorkTable.DESCRIPTION, Mark);
		values.put(HomeWorkTable.PARTNER, "");
		values.put(HomeWorkTable.SUBJECT_NAME, subjectName);

		db.insert(TABLE_NAME, null, values);

		int homeworkId = 0;
		homeworkInfo.setID(homeworkId = Utils.getMaxId(TABLE_NAME));

		//更新SubjectHomeworkTable表
		int subjectId = SubjectTable.getSubjectId(subjectName);
		HomeWorkSubjectTable.insert(homeworkId, subjectId);
		
		return homeworkInfo;
	}

	@Override
	public boolean DeleteHomeworkInfo(int id) {

		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		try {
			db.delete(TABLE_NAME, "_id=?", new String[] {String.valueOf(id)});
			HomeWorkSubjectTable.remove(id);
		} catch (Exception e) {
			return false;
		}
		return  true ;
	}

	@Override
	public void modifyHomeworkInfo(int id, ContentValues contentValues) {
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		db.update(TABLE_NAME, contentValues, "_id=?", new String[] {String.valueOf(id)});
	}

}
