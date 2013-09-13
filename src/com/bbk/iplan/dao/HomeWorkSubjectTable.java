package com.bbk.iplan.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;


/**
 * <pre>
 * create table homework_subject
 * (
 *  homework_subject_id  integer not null,
 *  homework_id          int,
 *  subject_id           int,
 *  primary key (homework_subject_id)
 * );
 *	</pre>
 *
 */
public class HomeWorkSubjectTable implements TableCreateInterface{

	
	public static final String TABLE_NAME = "homework_subject";
	
	public static final String HOMEWORK_SUBJECT_ID = "_id"; //homework_subject_id
	public static final String HOMEWORK_ID = "homework_id";
	public static final String SUBJECT_ID = "subject_id";
	
	private static HomeWorkSubjectTable mHomeWorkSubjectTable = new HomeWorkSubjectTable();
	
	private HomeWorkSubjectTable()
	{
		
	}
	
	public static HomeWorkSubjectTable getInstance()
	{
		return mHomeWorkSubjectTable;
	}
	
	public static void insert(int homeworkId, int subjectId)
	{
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(HOMEWORK_ID, homeworkId);
		values.put(SUBJECT_ID, subjectId);
		db.insert(TABLE_NAME, null, values);
		values = new ContentValues(); 
		db.close();
	}
	
	public static void remove(int homeworkId)
	{
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		db.delete(TABLE_NAME, HOMEWORK_ID + "=?", new String[] {String.valueOf(homeworkId)});
	}
	
	public static String getSubjectNameForHomework(int homeworkId)
	{
		int subjectId = getSubjectId(homeworkId);
		return SubjectTable.getSubjectName(subjectId);
	}
	
	public static int getSubjectId(int homeworkId)
	{
		
		int subjectId = -1;
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, new String[]
		{ HomeWorkSubjectTable.SUBJECT_ID }, null, null, null, null, null);
		
		
		while(cursor.moveToNext())
		{
			subjectId = cursor.getInt(cursor.getColumnIndexOrThrow(HomeWorkSubjectTable.SUBJECT_ID));
		}
		
		if(cursor != null)
		{
			cursor.close();
			db.close();
		}
		
		return subjectId;
		
	}
	
	public static List<Integer> readHomeworkId(int homeworkSubjectId)
	{
		
		List<Integer> temp = new ArrayList<Integer>();
		
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_NAME, 
				new String[]{HomeWorkSubjectTable.HOMEWORK_ID}, null, null, null, null, null);
		
		while (cursor.moveToNext())
		{
			temp.add(cursor.getInt(cursor.getColumnIndexOrThrow(HomeWorkSubjectTable.HOMEWORK_ID)));
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
				+ HomeWorkSubjectTable.TABLE_NAME 
				+ " ( "
				+ HomeWorkSubjectTable.HOMEWORK_SUBJECT_ID + " integer primary key autoincrement, "	
				+ HomeWorkSubjectTable.HOMEWORK_ID + " integer, "
				+ HomeWorkSubjectTable.SUBJECT_ID + " integer"
				+ " );";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if (oldVersion < newVersion) {
			String sql = "DROP TABLE IF EXISTS " + HomeWorkSubjectTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}
		
	}

}
