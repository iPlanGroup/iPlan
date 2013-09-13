package com.bbk.iplan.dao;

import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;


public class ExamSingleSubjectTable implements TableCreateInterface
{

	public static final String TABLE_NAME = "exam_single_subject_table";

	public static final String EXAMSINGLR_SUBJECT_ID = "_id"; 

	public static final String EVENT_ID = "event_id";
	public static final String SUBJECT_ID = "subject_id";
	public static final String NAME = "name";
	public static final String START_TIME = "start_time";
	public static final String END_TIME = "end_time";
	public static final String REMIND_TIME = "remind_time";
	public static final String MARK = "mark";

	private static ExamSingleSubjectTable mSubjectTable = new ExamSingleSubjectTable();

	private ExamSingleSubjectTable()
	{

	}

	public static ExamSingleSubjectTable getInstance()
	{
		return mSubjectTable;
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "CREATE TABLE " + ExamSingleSubjectTable.TABLE_NAME + " ( "
				+ ExamSingleSubjectTable.EXAMSINGLR_SUBJECT_ID + " integer primary key autoincrement, " 
				+ ExamSingleSubjectTable.NAME + " varchar(1024) not null, " 
				+ ExamSingleSubjectTable.EVENT_ID + " int not null, " 
				+ ExamSingleSubjectTable.SUBJECT_ID + " int not null, " 
				+ ExamSingleSubjectTable.START_TIME + " long not null, "
				+ ExamSingleSubjectTable.END_TIME + "long not null, "
				+ ExamSingleSubjectTable.REMIND_TIME + " long, "
				+ ExamSingleSubjectTable.MARK+ " varchar(1024) "
				+ " );";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

		if (oldVersion < newVersion)
		{
			String sql = "DROP TABLE IF EXISTS " + ExamSingleSubjectTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}

	}



}
