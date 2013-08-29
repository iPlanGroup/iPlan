package com.bbk.iplan.dao;

import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;


/**
 * ���ڱ�
 * 
 * @author �ƺ��
 * 
 * <pre>
 * create table vacation
 * (
 *  vacation_id          integer not null,
 *  subject_id           int,
 *  primary key (vacation_id)
 * );
 * 
 * </pre>
 * 
 *
 */
public class VacationTable implements TableCreateInterface{

	
	public static final String TABLE_NAME = "vacation";
	
	private static final String VACATION_ID = "_id"; //vacation_id
	private static final String SUBJECT_ID = "subject_id";
	private static final String IS_TERM = "is_term";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE "
				+ VacationTable.TABLE_NAME 
				+ " ( "
				+ VacationTable.VACATION_ID + " integer primary key autoincrement, "	
				+ VacationTable.SUBJECT_ID + " int, "
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

}
