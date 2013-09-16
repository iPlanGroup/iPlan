package com.bbk.iplan.notification;

import com.bbk.iplan.R;
import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.HomeworkInfo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
	private NotificationManager notificationManager;
	private Context mContext;
	private SharedPreferences settings;
	public static final int MODE_HOMEWORK = 10; // 不要改
	private String defaultring = "content://settings/system/ringtone";

	@Override
	public void onReceive(Context context, Intent intent) {
		this.mContext = context;
		settings = context.getSharedPreferences("settingXML", 0);
		int mode = intent.getIntExtra("mode", 1);
		String name = intent.getStringExtra("name");
		String mark = intent.getStringExtra("mark");
		notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		if (intent.getAction().equals("com.bbk.iPlan.notification")) {
			String str = defaultring;
			switch (mode) {
			case EventInfo.MODE_EXAM:
				if (settings.getBoolean("exam_checkBox", true)) {
					str = settings.getString("exam_ring", defaultring);
				}

				break;
			case EventInfo.MODE_LONGSUBJECT:
				if (settings.getBoolean("class_checkBox", true)) {
					str = settings.getString("class_ring", defaultring);
				}

				break;
			case MODE_HOMEWORK:
				if (settings.getBoolean("class_checkBox", true)) {
					str = settings.getString("work_ring", defaultring);
				}

				break;

			}

			setNotification("来自iPan的通知", mode, name, mark,
					R.drawable.ic_launcher, str);

		}
	}

	private void setNotification(String tickerText, int mode, String name,
			String mark, int drawable, String uri) {
		Notification notification = new Notification(drawable, tickerText,
				System.currentTimeMillis());
		Intent intent = new Intent();
		intent.putExtra("mode", mode);
		intent.putExtra("name", name);
		intent.putExtra("mark", mark);
		intent.setClass(mContext, DialogActivity.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// notification.defaults = Notification.DEFAULT_ALL;

		notification.sound = Uri.parse(uri);

		notification.flags = notification.FLAG_INSISTENT
				| Notification.FLAG_INSISTENT;
		notification.setLatestEventInfo(mContext, name, mark, pendingIntent);
		notificationManager.notify(R.layout.plan_dayplan, notification);

	}

}
