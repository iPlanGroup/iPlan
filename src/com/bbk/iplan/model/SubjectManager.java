package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;
import com.bbk.iplan.interfaces.ManagerInterface;
import com.bbk.iplan.interfaces.SubjectInterface;

public class SubjectManager implements ManagerInterface{
	private SubjectInterface mInterface;
	public List<SubjectInfo> getAllSubjet() {
		return mInterface.getAllSubject();

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
	public void ModifyInfo(int id, String column, Object value, int mode) {
		// TODO Auto-generated method stub
		mInterface.modifySubjectInfo(id, column, value, mode);
	}
}
