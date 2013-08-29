package com.bbk.iplan.model;

public class SystemManager extends AbstractSystemManager {
	private EventManager mEventManager=new EventManager();
	private HomeworkManager mHomeworkManager=new HomeworkManager();
	private SubjectManager mSubjectManager=new SubjectManager();
	private VacationManager mVacationManager=new VacationManager();
	public static final int MODE_EVENT = 0;
	public static final int MODE_HOMEWORK = 1;
	public static final int MODE_SUBJECT = 2;
	public static final int MODE_VACATION = 3;
	private static SystemManager instance;
	private SystemManager(){
	};
	public static SystemManager getInstance(){
		if (instance==null)
			instance=new SystemManager();
			return instance;

	}
	@Override
	public Object getSystemManager(int mode) {
		// TODO Auto-generated method stub
		Object manager=null;
		switch (mode) {
		case MODE_EVENT:
			manager=mEventManager;
			break;
		case MODE_HOMEWORK:
			manager=mHomeworkManager;
			break;
		case MODE_SUBJECT:
			manager=mSubjectManager;
			break;
		case MODE_VACATION:
			manager=mVacationManager;
			break;

		}
		return manager;
	}
	

}