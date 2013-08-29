package com.bbk.iplan.dao;

import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;

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
