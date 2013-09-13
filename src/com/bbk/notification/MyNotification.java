package com.bbk.notification;

import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.model.SystemManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

	public MyNotification(Context context) {
		this.mContext = context;
		mAlarm = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
	}

	public void setNotifi(EventInfo eventInfo) {

		// 获取时间信息
		int mode_Type = eventInfo.getMode();
		long remindTime = eventInfo.getRemindTime();
		String name = eventInfo.getName();
		String mark = eventInfo.getMark();

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
