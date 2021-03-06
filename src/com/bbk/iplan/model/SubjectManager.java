package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

import android.content.ContentValues;

import com.bbk.iplan.dao.SubjectTable;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;
import com.bbk.iplan.interfaces.ManagerInterface;
import com.bbk.iplan.interfaces.SubjectInterface;

public class SubjectManager implements ManagerInterface{
	private SubjectInterface mInterface=SubjectTable.getInstance();
	public List<SubjectInfo> getAllSubjet(int id) {
		return mInterface.getAllSubject(id);

	}

	public List<SubjectInfo> getLocalSubject(Date time) {
		return mInterface.getLocalSubject(time);
	}

	public SubjectInfo CreateLongSubject(String name, Date time,int[] week_time,
			Date notifytime, String place, String teacher, String mark,TermInfo termInfo) {
		SubjectInfo info = new SubjectInfo();
		info.setName(name);
		info.setNotifyTime(time);
		info.setTime(time);
		info.setPlace(place);
		info.setTeacher(teacher);
		info.setMark(mark);
		info.setTermInfo(termInfo);
		mInterface.CreateLongSubject(name, time, week_time, notifytime, place, teacher, mark, termInfo);
		return info;
	}

	@Override
	public boolean DeleteInfo(int id) {
		// TODO Auto-generated method stub
		return mInterface.DeleteSubjectInfo(id);
	}

	@Override
	public void ModifyInfo(int id, ContentValues contentValues) {
		// TODO Auto-generated method stub
		mInterface.modifySubjectInfo(id, contentValues);
	}
	public List<SubjectInfo> getSubjectInfoByVacationID(int id)
	{
		return mInterface.getSubjectInfosByVacationID(id);
	}
	
}
