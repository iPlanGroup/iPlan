package com.bbk.iplan.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @version V1.0
 * @since 2013/8/22
 * 
 */
public class IPlanDataBaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "iplan_database";

	public static final int VERSION = 1;

	public IPlanDataBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, VERSION);
		this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		EventTable.getInstance().onCreate(db);
		HomeWorkSubjectTable.getInstance().onCreate(db);
		SubjectTable.getInstance().onCreate(db);
		VocationTable.getInstance().onCreate(db);
		HomeWorkTable.getInstance().onCreate(db);
		WeekTimeTable.getInstance().onCreate(db);
		SubjectWeekTimeTable.getInstance().onCreate(db);
		ExamSingleSubjectTable.getInstance().onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		WeekTimeTable.getInstance().onUpgrade(db, oldVersion, newVersion);
		EventTable.getInstance().onUpgrade(db, oldVersion, newVersion);
		HomeWorkSubjectTable.getInstance()
				.onUpgrade(db, oldVersion, newVersion);
		HomeWorkTable.getInstance().onUpgrade(db, oldVersion, newVersion);
		SubjectTable.getInstance().onUpgrade(db, oldVersion, newVersion);
		VocationTable.getInstance().onUpgrade(db, oldVersion, newVersion);
		SubjectWeekTimeTable.getInstance()
				.onUpgrade(db, oldVersion, newVersion);
	}

	public static interface TableCreateInterface {

		public void onCreate(SQLiteDatabase db);

		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
	}

}
