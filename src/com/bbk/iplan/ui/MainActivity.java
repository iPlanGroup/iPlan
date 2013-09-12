package com.bbk.iplan.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.bbk.iplan.R;
import com.bbk.iplan.model.SystemManager;
import com.bbk.iplan.utils.Utils;
import com.bbk.pop.SummaryExListAdapter;

@SuppressLint("NewApi")
public class MainActivity extends ActivityGroup implements OnClickListener{
	private SystemManager manager;
	private RelativeLayout container = null;
	private Button dayplanButton;
	private Button weekplanButton;
	private Button summaryBtn = null;

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
		
		summaryBtn = (Button)findViewById(R.id.summarybtn);
		summaryBtn.setOnClickListener(this);
		dayplanButton.setOnClickListener(this);
		weekplanButton.setOnClickListener(this);

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
		case R.id.dayplanbtn: {
			container.removeAllViews();
			container.addView(getLocalActivityManager().startActivity(
					"Module1",
					new Intent(MainActivity.this, DayPlanActivity.class)
							.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
					.getDecorView());
			break;
		}
		case R.id.weekplanbtn: {
			container.removeAllViews();
			container.addView(getLocalActivityManager().startActivity(
					"Module2",
					new Intent(MainActivity.this, WeekPlanActivity.class)
							.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
					.getDecorView());
			break;
		}
		}
	}

	
	public void viewMove(View view, float startX, float startY, float dx,
			float dy, int durating) {

		TranslateAnimation translateAnimation = new TranslateAnimation(
				startX, startY, dx, dy);
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
	}


}
