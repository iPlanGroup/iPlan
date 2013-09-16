package com.bbk.iplan.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bbk.iplan.R;
import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.HomeworkInfo;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.model.DayPlan;
import com.bbk.iplan.model.FacadePlan;
import com.bbk.iplan.model.SystemManager;
import com.bbk.iplan.utils.Utils;
import com.bbk.notification.MyNotification;

public class MainActivity extends ActivityGroup implements OnClickListener {
	private SystemManager manager;
	private RelativeLayout container = null;
	private Button dayplanButton;
	private Button weekplanButton;
	private Button summaryBtn = null;
	private Button SetButton;
	public PopupWindow summaryPW = null;

	public List<HashMap<String, Object>> bookDataList = null;
	public SimpleAdapter bookDataAdapter = null;

	public Utils utils = null;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.plan_main);

		utils = new Utils(this);

		getSystemService(WINDOW_SERVICE);
		manager = SystemManager.getInstance();
		manager.getSystemManager(SystemManager.MODE_EVENT);

		init();

		container.removeAllViews();
		container.addView(getLocalActivityManager().startActivity(
				"Module1",
				new Intent(MainActivity.this, DayPlanActivity.class)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView());

		// summaryBtn = (Button) findViewById(R.id.summarybtn);
		summaryBtn.setOnClickListener(this);
		dayplanButton.setOnClickListener(this);
		weekplanButton.setOnClickListener(this);
		SetButton.setOnClickListener(this);

		FacadePlan mFacadePlan = new FacadePlan();
		Date date = new Date(System.currentTimeMillis());
		DayPlan mDayPlan = mFacadePlan.getCurDayPlan(date);
		List<EventInfo> eventsList = mDayPlan.getmListEventInfos();
		List<SubjectInfo> daySubjectList = mDayPlan.getDaySubject();
		List<HomeworkInfo> dayHomeworkList = mDayPlan.getDayHomework();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.summarybtn: {
			utils.showSummaryPop(summaryBtn);
			break;
		}
		case R.id.setbtn: {
			// 通知设置范例
			Toast.makeText(getApplicationContext(), "notifi", 0).show();
			MyNotification myNotification = new MyNotification(
					MainActivity.this);
			EventInfo eventInfo = new EventInfo();
			eventInfo.setMode(EventInfo.MODE_SINGLE);
			eventInfo.setName("数学");
			eventInfo.setMark("aaaaaaa");
			long remindTime = System.currentTimeMillis() + 5000;
			eventInfo.setRemindTime(remindTime);

			myNotification.setNotifi(eventInfo);

			break;
		}

		case R.id.dayplanbtn: {
			container.removeAllViews();
			container.addView(getLocalActivityManager().startActivity(
					"Module1",
					new Intent(MainActivity.this, DayPlanActivity.class)
							.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
					.getDecorView());

			dayplanButton.setBackgroundResource(R.drawable.press);
			weekplanButton.setBackgroundResource(R.drawable.unpress);
			break;

		}
		case R.id.weekplanbtn: {
			container.removeAllViews();
			container.addView(getLocalActivityManager().startActivity(
					"Module2",
					new Intent(MainActivity.this, WeekPlanActivity.class)
							.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
					.getDecorView());
			dayplanButton.setBackgroundResource(R.drawable.unpress);
			weekplanButton.setBackgroundResource(R.drawable.press);
			break;
		}
		}
	}

	// 可删除
	public void viewMove(View view, float startX, float startY, float dx,
			float dy, int durating) {

		TranslateAnimation translateAnimation = new TranslateAnimation(startX,
				startY, dx, dy);
		translateAnimation.setDuration(durating);
		translateAnimation.setFillAfter(true);
		view.startAnimation(translateAnimation);
		view.setVisibility(0);
	}

	public void init() {
		container = (RelativeLayout) findViewById(R.id.container);
		dayplanButton = (Button) findViewById(R.id.dayplanbtn);
		weekplanButton = (Button) findViewById(R.id.weekplanbtn);
		summaryBtn = (Button) findViewById(R.id.summarybtn);
		SetButton = (Button) findViewById(R.id.setbtn);
	}

}
