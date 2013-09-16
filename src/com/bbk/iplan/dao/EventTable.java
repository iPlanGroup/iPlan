package com.bbk.iplan.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.dao.IPlanDataBaseHelper.TableCreateInterface;
import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;
import com.bbk.iplan.interfaces.EventInterface;
import com.bbk.iplan.utils.Utils;

public class EventTable implements TableCreateInterface, EventInterface {

	public static final String TABLE_NAME = "event";

	private static final String EVENT_ID = "_id"; // event_id
	private static final String NAME = "name";

	private static EventTable mEventTable = new EventTable();

	private EventTable() {

	}

	public static EventTable getInstance() {
		return mEventTable;
	}

	private static void insert(SQLiteDatabase db, String name) {
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		db.insert(TABLE_NAME, null, values);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE " + EventTable.TABLE_NAME + " ( "
				+ EventTable.EVENT_ID + " integer primary key autoincrement, "
				+ EventTable.NAME + " varchar(1024) not null" + " );";
		db.execSQL(sql);

		EventTable.insert(db, Utils.MODE_LONGSUBJECT_TEXT);
		EventTable.insert(db, Utils.MODE_SINGLE_TEXT);
		EventTable.insert(db, Utils.MODE_EXAM_TEXT);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			String sql = "DROP TABLE IF EXISTS " + EventTable.TABLE_NAME;
			db.execSQL(sql);
			this.onCreate(db);
		}
	}

	@Override
	public List<EventInfo> getAllEventInfo() {
		List<EventInfo> allEvents = new ArrayList<EventInfo>();
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getReadableDatabase();

		// 读课程表
		Cursor subjectCursor = db.query(ExamSingleSubjectTable.TABLE_NAME,
				null, null, null, null, null, null);

		EventInfo eventInfo = null;
		int eventId = 0;
		int subjectId = 0;
		int vocationId = 0;

		while (subjectCursor.moveToNext()) {
			
			eventInfo = new EventInfo();

			eventId = subjectCursor.getInt(subjectCursor.getColumnIndexOrThrow(ExamSingleSubjectTable.EVENT_ID));
			eventInfo.setEndTime(new Date(subjectCursor.getLong(subjectCursor.getColumnIndexOrThrow(ExamSingleSubjectTable.END_TIME))));
			eventInfo.setStartTime(new Date(subjectCursor.getLong(subjectCursor.getColumnIndexOrThrow(ExamSingleSubjectTable.START_TIME))));
			eventInfo.setID(subjectCursor.getInt(subjectCursor.getColumnIndexOrThrow(ExamSingleSubjectTable.SUBJECT_ID)));
			eventInfo.setName(subjectCursor.getString(subjectCursor.getColumnIndexOrThrow(ExamSingleSubjectTable.NAME)));
			eventInfo.setRemindTime(new Date(subjectCursor.getLong(subjectCursor.getColumnIndexOrThrow(ExamSingleSubjectTable.REMIND_TIME))));
			eventInfo.setMark(subjectCursor.getString(subjectCursor.getColumnIndexOrThrow(ExamSingleSubjectTable.MARK)));
			eventInfo.setMode(eventId);

			subjectId = subjectCursor.getInt(subjectCursor.getColumnIndexOrThrow(ExamSingleSubjectTable.SUBJECT_ID));
			eventInfo.setSubject(SubjectTable.getSubjectInfoById(subjectId));
			vocationId = subjectCursor.getInt(subjectCursor.getColumnIndexOrThrow(SubjectTable.VOCATION_ID));
			eventInfo.setTerm(VacationTable.getTermInfoById(vocationId));
			allEvents.add(eventInfo);
		}

		if (subjectCursor != null) {
			
			subjectCursor.close();
		}

		return allEvents;
	}

	@Override
	public EventInfo CreateExam(int Mode, String name, Date remindTime,
			SubjectInfo subject, String mark, Date StartTime, Date EndTime) {

		EventInfo eventInfo = new EventInfo();

		eventInfo.setEndTime(EndTime);
		eventInfo.setMode(Mode);
		eventInfo.setName(name);
		eventInfo.setRemindTime(remindTime);
		eventInfo.setStartTime(StartTime);
		eventInfo.setSubject(subject);

		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ExamSingleSubjectTable.END_TIME, EndTime.getTime());
		values.put(ExamSingleSubjectTable.START_TIME, StartTime.getTime());
		values.put(ExamSingleSubjectTable.REMIND_TIME, remindTime.getTime());
		values.put(ExamSingleSubjectTable.SUBJECT_ID, subject.getID());
		values.put(ExamSingleSubjectTable.EVENT_ID, 3);
		values.put(ExamSingleSubjectTable.MARK, mark);
		values.put(ExamSingleSubjectTable.NAME, name);
		
		db.insert(ExamSingleSubjectTable.TABLE_NAME, null, values);
		
		return eventInfo;
	}

	@Override
	public EventInfo CreateSingleSubject(int Mode, String name,
			Date remindTime, Date StartTime,Date EndTime, String mark, TermInfo term, SubjectInfo subject) {
		EventInfo eventInfo = new EventInfo();

		eventInfo.setEndTime(EndTime);
		eventInfo.setMode(Mode);
		eventInfo.setName(name);
		eventInfo.setRemindTime(remindTime);
		eventInfo.setStartTime(StartTime);
		eventInfo.setSubject(subject);

		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ExamSingleSubjectTable.END_TIME, EndTime.getTime());
		values.put(ExamSingleSubjectTable.START_TIME, StartTime.getTime());
		values.put(ExamSingleSubjectTable.REMIND_TIME, remindTime.getTime());
		values.put(ExamSingleSubjectTable.SUBJECT_ID, subject.getID());
		values.put(ExamSingleSubjectTable.EVENT_ID, 2);
		values.put(ExamSingleSubjectTable.MARK, mark);
		values.put(ExamSingleSubjectTable.NAME, name);
		
		db.insert(ExamSingleSubjectTable.TABLE_NAME, null, values);
		
		return eventInfo;
	}

	@Override
	public boolean DeleteEventInfo(int id) {

		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		try {
			db.delete(ExamSingleSubjectTable.TABLE_NAME, "_id=?", new String[] {String.valueOf(id)});
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public void ModifyEventInfo(int id, ContentValues contentValues) {
		SQLiteDatabase db = IPlanApplication.getDataBaseHelper().getWritableDatabase();
		db.update(ExamSingleSubjectTable.TABLE_NAME, contentValues, "_id=?", new String[] {String.valueOf(id)});
	}

}
