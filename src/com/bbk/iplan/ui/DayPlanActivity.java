package com.bbk.iplan.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bbk.flipview.library.flipcontrol.FlipViewController;
import com.bbk.flipview.library.fliputils.TravelAdapter;
import com.bbk.iplan.R;
import com.bbk.iplan.calendar.DateWidget;
import com.bbk.iplan.model.SystemManager;
import com.bbk.iplan.utils.BookUtils;
import com.bbk.pop.PopUtils;

public class DayPlanActivity extends Activity implements OnItemClickListener,
		OnItemLongClickListener {
	private SystemManager manager;
	private CustomView dateView;
	private CustomView mCustomView;
	private CustomView mHwDetails;
	private CustomView custom_calendar;
	private FlipViewController flipView;
	public List<HashMap<String, Object>> bookDataList = new ArrayList<HashMap<String, Object>>();
	public SimpleAdapter bookDataAdapter = null;
	private Calendar mCalendar = null;
	private static DateWidget dateWidget;
	private RelativeLayout calendarView; // relativelayout 下的calendarview
	private LinearLayout detailslayout; // 取消动画的layout
	private TextView calendar_day;
	private TextView calendar_month;
	private TextView calendar_week;
	private TravelAdapter mAdapter;
	public PopUtils pop = null;
	public BookUtils bookUtils = null;
	public static String[] WeekDays = { "周日", "周一", "周二", "周三", "周四", "周五",
			"周六", };

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plan_dayplan);

		pop = new PopUtils(this);
		bookUtils = new BookUtils();
		init();
		bookDataList = bookUtils.getBookDataList();
		bookDataAdapter = getBookDataAdapter(bookDataList);

		detailslayout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scrollLayout(dateView, 0, 0, -300, 0, 200);
				scrollLayout(mCustomView, 0, 0, -320, 0, 200);
				scrollLayout(custom_calendar, 0, 0, 0, -150, 200);
				scrollLayout(mHwDetails, 0, 0, 0, -250, 400);
			}
		});

		flipView = (FlipViewController) findViewById(R.id.flip_view);
		mAdapter = new TravelAdapter(this, null);
		flipView.setAdapter(mAdapter);
		
		
		flipView.setOnViewFlipListener(new FlipViewController.ViewFlipListener() {
			@Override
			public void onViewFlipped(View view, int position) {
				
				Toast.makeText(view.getContext(), "当前页码: " + position,
						Toast.LENGTH_SHORT).show();
				
			}
		});
		
		

		/**
		 * 日历部分
		 * */
		dateWidget = new DateWidget();
		View calendar = dateWidget.init(DayPlanActivity.this);
		calendarView = (RelativeLayout) findViewById(R.id.calendarview);
		dateWidget.setFlag(0);
		calendarView.addView(calendar);
		dateWidget.init2();

	}

	public static void goBack2Today() {

		dateWidget.backToday();

	}

	public void move() {
		scrollLayout(dateView, 0, 0, 300, 0, 200);
		scrollLayout(mCustomView, 0, 0, 320, 0, 200);
		scrollLayout(custom_calendar, 0, 0, 0, 150, 200);
		scrollLayout(mHwDetails, 0, 0, 0, 250, 400);
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

	public void scrollLayout(CustomView view, int startX, int startY, int dx,
			int dy, int durating) {
		view.beginScroll(startX, startY, dx, dy, durating);
	}

	public void init() {
		mCustomView = (CustomView) findViewById(R.id.custom_viewgroup);
		mHwDetails = (CustomView) findViewById(R.id.custom_detail);
		custom_calendar = (CustomView) findViewById(R.id.custom_calendarview);

		dateView = (CustomView) findViewById(R.id.custom_dategroup);
		detailslayout = (LinearLayout) findViewById(R.id.detailslayout);

		calendar_day = (TextView) findViewById(R.id.calendar_day);
		calendar_month = (TextView) findViewById(R.id.calendar_week);
		calendar_week = (TextView) findViewById(R.id.calendar_month);

		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		calendar_day.setText(day + "");
		calendar_month.setText(month + "月");
		calendar_week.setText(WeekDays[week]);

	}

	public SimpleAdapter getBookDataAdapter(
			List<HashMap<String, Object>> dataList) {
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, dataList,
				R.layout.list_item, new String[] { "ItemImage", "ItemTitle",
						"ItemText" }, new int[] { R.id.ItemImage1,
						R.id.ItemTitle, R.id.ItemText });

		return listItemAdapter;
	}

	
	/**
	 * 作业信息 item 的点击事件
	 * */
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int id, long arg3) {

		move();

	}
/**
 * 长按出现删除数据选项
 * */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v,
			final int position, long id) {

		final ListView hwListView = (ListView) v.getParent();

		ImageView delimg = (ImageView) v.findViewById(R.id.ItemImage2);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
		alphaAnimation.setDuration(500);
		delimg.startAnimation(alphaAnimation);
		delimg.setVisibility(View.VISIBLE);
		delimg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				TravelAdapter.listItem.remove(position);
				hwListView.setAdapter(TravelAdapter.hwAdapter);
			}
		});
		return true;
	}

}
