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
import com.bbk.iplan.interfaces.VacationInterface;


public class VacationTable implements TableCreateInterface, VacationInterface{

	
	public static final String TABLE_NAME = "vacation";
	
	public static final String VACATION_ID = "_id"; //vacation_id
	public static final String IS_TERM = "is_term";
	public static final String START_TIME = "start_time";
	public static final String END_TIME = "end_time";
	public static final String NAME = "name";
	
	
	private static VacationTable mVacationTable = new VacationTable();
	
	private VacationTable()
	{
		
	}
	
	public static VacationTable getInstance()
	{
		return mVacationTable;
	}
	
	/**
	 * 
	 * 通过ID获取TermInfo
	 * 
	 * @param vocationId
	 * @return
	 */
	public static TermInfo getTermInfoById(int vocationId)
	{
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		Cursor cursor = db.query(VacationTable.TABLE_NAME, null, 
				VACATION_ID+"=?", 
				new String[]{String.valueOf(vocationId)}, 
				null, null, null);
		
		TermInfo termInfo = null;
		
		if(cursor.moveToNext())
		{
			termInfo = new TermInfo();
			termInfo.setEndTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(END_TIME))));
			termInfo.setStartTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(START_TIME))));
			termInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
			termInfo.setID(cursor.getInt(cursor.getColumnIndexOrThrow(VACATION_ID)));
			termInfo.setIsTerm(cursor.getInt(cursor.getColumnIndexOrThrow(IS_TERM))==0?false:true);
		}
		
		if(cursor != null)
		{
			cursor.close();
		}
		
		return termInfo;
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE "
				+ VacationTable.TABLE_NAME 
				+ " ( "
				+ VacationTable.VACATION_ID + " integer primary key autoincrement, "	
				+ VacationTable.START_TIME + " long not null, "
				+ VacationTable.END_TIME + " long not null, "
				+ VacationTable.NAME + " varchar(1024), "
				+ VacationTable.IS_TERM + " boolean"
				+ " );";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if (oldVersion < newVersion) {
			String sql = "DROP TABLE IF EXISTS " + VacationTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}
		
	}

	
	@Override
	public List<TermInfo> getAllVacation() {
		
		List<TermInfo> list = new ArrayList<TermInfo>();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
	
		TermInfo termInfo = null;
		while(cursor.moveToNext())
		{
			termInfo = new TermInfo();
			termInfo.setEndTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(END_TIME))));
			termInfo.setStartTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(START_TIME))));
			termInfo.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
			termInfo.setID(cursor.getInt(cursor.getColumnIndexOrThrow(VACATION_ID)));
			termInfo.setIsTerm(cursor.getInt(cursor.getColumnIndexOrThrow(IS_TERM))==0?false:true);
			list.add(termInfo);
		}
		
		if(cursor != null)
		{
			cursor.close();
		}
		
		return list;
	}

	@Override
	public TermInfo CreateVacation(String name, Date StartTime, Date EndTime,
			boolean isVacation) {
		
		TermInfo termInfo = new TermInfo();
		termInfo.setEndTime(EndTime);
		termInfo.setStartTime(StartTime);
		termInfo.setIsTerm(isVacation);
		termInfo.setName(name);
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(END_TIME, EndTime.getTime());
		values.put(START_TIME, StartTime.getTime());
		values.put(IS_TERM, isVacation);
		values.put(NAME, name);
		db.insert(TABLE_NAME, null, values);
		
		return termInfo;
	}

	@Override
	public boolean DeleteVacationInfo(int id) {
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		try {
			db.delete(TABLE_NAME, "_id=?", new String[] {String.valueOf(id)});
			//删除说有与这条vacation相关的课程
			List<SubjectInfo> lists = SubjectTable.getSubjectInfoByVocaitonId(id);
			
			for(SubjectInfo info : lists)
			{
				SubjectTable.getInstance().DeleteSubjectInfo(info.getID());		
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public void modifyVacationInfo(int id, ContentValues contentValues) {
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		db.update(TABLE_NAME, contentValues, "_id=?", new String[] {String.valueOf(id)});
	}


}
