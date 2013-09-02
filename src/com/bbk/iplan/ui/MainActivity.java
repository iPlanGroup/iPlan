package com.bbk.iplan.ui;

import com.bbk.iplan.model.SystemManager;

import android.os.Bundle;
import android.app.ActivityGroup;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends ActivityGroup {
	private SystemManager manager;
	private RelativeLayout container = null;
	// private Button staticButton;
	// private LinearLayout dateView = null;
	// private CustomView mCustomView;
	// private CustomView mHwDetails;
	// private CalendarView mCalendarView;
	private Button dayplanButton;
	private Button weekplanButton;

	// private LinearLayout bseLinearLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plan_main);
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

		dayplanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"Module1",
						new Intent(MainActivity.this, DayPlanActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());

			}
		});
		weekplanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"Module2",
						new Intent(MainActivity.this, WeekPlanActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());
			}
		});

		// staticButton.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		//
		// scrollLayout(mCustomView, 0, 0, 320, 0, 1000);
		// viewMove(dateView, 0f, -300f, 0f, 0f, 500);
		// viewMove(mCalendarView, 0f, 0f, 0f, -150f, 700);
		// scrollLayout(mHwDetails, 0, 0, 0, 250, 2000);
		//
		// }
		//
		// });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// public void setFullscreen() {
	// requestWindowFeature(Window.FEATURE_NO_TITLE);
	// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	// WindowManager.LayoutParams.FLAG_FULLSCREEN);
	// }

	public void scrollLayout(CustomView view, int startX, int startY, int dx,
			int dy, int durating) {

		view.beginScroll(startX, startY, dx, dy, durating);
		// mCustomView.beginScroll(0, 0, 320, 0, 1000);

	}

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
		// mCustomView = (CustomView) findViewById(R.id.custom_viewgroup);
		// mHwDetails = (CustomView) findViewById(R.id.custom_detail);
		// mCalendarView = (CalendarView) findViewById(R.id.calendarview);
		//
		// dateView = (LinearLayout) findViewById(R.id.dategroup);

		// staticButton = (Button) findViewById(R.id.staticbtn);
		container = (RelativeLayout) findViewById(R.id.container);
		dayplanButton = (Button) findViewById(R.id.dayplanbtn);
		weekplanButton = (Button) findViewById(R.id.weekplanbtn);
	}
}