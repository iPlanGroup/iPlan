package com.bbk.iplan.ui;

import java.util.Calendar;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bbk.iplan.R;
import com.bbk.iplan.calendar.DateWidget;

public class WeekPlanActivity extends Activity {
	private GridView grid;
	private Calendar mCalendar = null;

	private TextView calendar_day;
	private TextView calendar_month;
	private TextView calendar_week;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plan_week);

		init();

		grid.setAdapter(new GridViewAdapter(this));
		grid.setOnItemClickListener(new ItemClickListener());

	}

	/**
	 * 点击日历单元格的时候调用此
	 * 
	 * @param myCalendar
	 */
	public void setDate(Calendar myCalendar) {
		this.mCalendar = myCalendar;
		System.out.println("设置日期成功");
	}

	class ItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View view, int id,
				long arg3) {
			Toast.makeText(getApplicationContext(), "click", 1).show();
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			return true;
		}
		return super.dispatchTouchEvent(ev);
	}

	public void init() {

		grid = (GridView) findViewById(R.id.main_GridView);
		calendar_day = (TextView) findViewById(R.id.calendar_day);
		calendar_month = (TextView) findViewById(R.id.calendar_week);
		calendar_week = (TextView) findViewById(R.id.calendar_month);
		grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		Calendar myCalendar = Calendar.getInstance();
		int month = myCalendar.get(Calendar.MONTH) + 1;
		int day = myCalendar.get(Calendar.DAY_OF_MONTH);
		int week = myCalendar.get(Calendar.DAY_OF_WEEK) - 1;

		calendar_day.setText(day + "");
		calendar_month.setText(month + "月");
		calendar_week.setText(DayPlanActivity.WeekDays[week]);

		DateWidget dateWidget = new DateWidget();
		View calendar = dateWidget.init(WeekPlanActivity.this);
		final RelativeLayout calendarView = (RelativeLayout) findViewById(R.id.calendarview);
		dateWidget.setFlag(1);
		calendarView.addView(calendar);
		dateWidget.init2();

	}

}
