package com.bbk.UI;

import java.util.HashMap;

import com.bbk.iplan.model.SystemManager;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends ActivityGroup {
	private SystemManager manager;
	private RelativeLayout container = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plan_main);
		getSystemService(WINDOW_SERVICE);
		manager = SystemManager.getInstance();
		manager.getSystemManager(SystemManager.MODE_EVENT);

		container = (RelativeLayout) findViewById(R.id.dayplanLayout);
		Button dayplanButton = (Button) findViewById(R.id.dayplanbtn);
		Button weekplanButton = (Button) findViewById(R.id.weekplanbtn);

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
						"Module1",
						new Intent(MainActivity.this, WeekPlanActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setFullscreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}