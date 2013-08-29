package com.bbk.iplan.dao;

import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;


/**
 * 
 * @author �ƺ��
 * 
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
	
	private static final String HOMEWORK_SUBJECT_ID = "_id"; //homework_subject_id
	private static final String HOMEWORK_ID = "homework_id";
	private static final String SUBJECT_ID = "subject_id";
	
	
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
