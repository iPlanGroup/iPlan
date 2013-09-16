package com.bbk.iplan.notification;

import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.HomeworkInfo;
import com.bbk.iplan.model.SystemManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * @Title: MyNotification.java
 * @Package com.bbk.notification
 * @Description: 通知
 * @author A18ccms A18ccms_gmail_com
 * @date 2013-9-13 上午09:05:19
 * @version V1.0
 */
public class MyNotification {
	private Context mContext;
	private AlarmManager mAlarm;
	private PendingIntent sender;
	private SharedPreferences settings;
	private long timeBreak;
	private long remindTime;

	public MyNotification(Context context) {
		this.mContext = context;
		mAlarm = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
		settings = context.getSharedPreferences("settingXML", 0);

	}

	public void setNotifi(EventInfo eventInfo) {
		// 获取时间信息
		int mode_Type = eventInfo.getMode();
		String name = eventInfo.getName();
		String mark = eventInfo.getMark();
		long startime = eventInfo.getStartTime();
		switch (mode_Type) {
		case EventInfo.MODE_LONGSUBJECT:

			timeBreak = settings.getLong("class_time", 10 * 60 * 1000);
			break;
		case EventInfo.MODE_EXAM:

			timeBreak = settings.getLong("exam_time", 1 * 24 * 60 * 60 * 1000);
			break;

		}
		remindTime = startime - timeBreak;
		remindTime = startime;
		FinishNotification(remindTime, mode_Type, name, mark);

	}

	public void setNotifi(HomeworkInfo eventInfo, String mode_Type) {
		String name = eventInfo.getName();
		String mark = eventInfo.getMark();
		long startime = eventInfo.getEndTime();

		timeBreak = settings.getLong("work_time", 6 * 60 * 60 * 1000);
		remindTime = startime - timeBreak;
		FinishNotification(remindTime, 10, name, mark);

	}

	public void FinishNotification(long remindTime, int mode_Type, String name,
			String mark) {

		Intent intent = new Intent(mContext, AlarmBroadcastReceiver.class);
		intent.setAction("com.bbk.iPlan.notification");
		intent.putExtra("mode", mode_Type);
		intent.putExtra("name", name);
		intent.putExtra("mark", mark);

		sender = PendingIntent.getBroadcast(mContext, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		mAlarm.set(AlarmManager.RTC_WAKEUP, remindTime, sender);
	}

}
