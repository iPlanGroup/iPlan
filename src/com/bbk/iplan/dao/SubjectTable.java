package com.bbk.iplan.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;
import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;

/**
 * 
 * @author �ƺ��
 * 
 * <pre>
 * 
 * create table subject
 * (
 *  subject_id           integer not null,
 *  event_id             int not null,
 *  name                 varchar(1024) not null,
 *  start_time           date not null,
 *  end_time             date not null,
 *  place                varchar(1024),
 *  teacher              varchar(1024),
 *  primary key (subject_id)
 * );
 * </pre>
 * 
 */
public class SubjectTable implements TableCreateInterface{

	
	public static final String TABLE_NAME = "subject";
	
	private static final String SUBJECT_ID = "_id"; //subject_id
	private static final String EVENT_ID = "event_id";
	private static final String NAME = "name";
	private static final String START_TIME = "start_time";
	private static final String END_TIME = "end_time";
	private static final String PLACE = "place";
	private static final String TEACHER = "teacher";
	
	
	
	public static void insert(SubjectInfo subjectInfo, EventInfo eventInfo, TermInfo info)
	{
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper()
				.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(SubjectTable.NAME, subjectInfo.getName());
		values.put(SubjectTable.EVENT_ID, eventInfo.getID());
//		values.put(SubjectTable.START_TIME, eventInfo.getStatrTime().toString());
		values.put(SubjectTable.START_TIME, eventInfo.getStartTime().toString());
		values.put(SubjectTable.TEACHER, subjectInfo.getTeacher());
		values.put(SubjectTable.PLACE, subjectInfo.getPlace());
		values.put(SubjectTable.END_TIME, eventInfo.getEndTime());
		
		
		
		db.insert(TABLE_NAME, null, values );
	}
	
	public static String readSubjectName(int subjectId)
	{
		
		String name = null;
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper()
				.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, new String[]
		{ SubjectTable.NAME }, null, null, null, null, null);
		
		
		while(cursor.moveToNext())
		{
			name = cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.NAME));
		}
		
		if(cursor != null)
		{
			cursor.close();
			db.close();
		}
		
		return name;
		
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE "
				+ SubjectTable.TABLE_NAME 
				+ " ( "
				+ SubjectTable.SUBJECT_ID + " integer primary key autoincrement, "	
				+ SubjectTable.NAME + " varchar(1024) not null, "
				+ SubjectTable.EVENT_ID + " int not null, "
				+ SubjectTable.START_TIME + " date not null," 
				+ SubjectTable.END_TIME + " date not null,"
				+ SubjectTable.PLACE + " varchar(1024),"
				+ SubjectTable.TEACHER + " varchar(1024)"
				+ " );";
		db.execSQL(sql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		if (oldVersion < newVersion) {
			String sql = "DROP TABLE IF EXISTS " + SubjectTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}
		
	}

}
