package com.bbk.iplan.dao;

import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;

/**
 * ��ҵ��
 * 
 * @author �ƺ��
 * 
 * <pre>
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
public class HomeWorkTable implements TableCreateInterface {

	public static final String TABLE_NAME = "homework";

	private static final String HOMEWORK_ID = "_id"; // homework_id
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String SUBJECT_ID = "subject_id";
	private static final String LEVEL = "level";
	private static final String DEALLINE = "deadline";
	private static final String PARTNER = "partner";

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE "
				+ HomeWorkTable.TABLE_NAME 
				+ " ( "
				+ HomeWorkTable.HOMEWORK_ID + " integer primary key autoincrement, "	
				+ HomeWorkTable.NAME + " varchar(1024) not null, "
				+ HomeWorkTable.DESCRIPTION + " varchar(1024), "
				+ HomeWorkTable.SUBJECT_ID + " int not null," 
				+ HomeWorkTable.LEVEL + " int not null,"
				+ HomeWorkTable.DEALLINE + " date not null,"
				+ HomeWorkTable.PARTNER + " varchar(1024)"
				+ " );";
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

}
