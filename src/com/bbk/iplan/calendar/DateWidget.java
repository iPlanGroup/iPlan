package com.bbk.iplan.calendar;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.bbk.iplan.R;
import com.bbk.iplan.ui.DayPlanActivity;
import com.bbk.iplan.ui.WeekPlanActivity;

public class DateWidget {
	private ArrayList<DateWidgetDayCell> days = new ArrayList<DateWidgetDayCell>();
	// private SimpleDateFormat dateMonth = new SimpleDateFormat("MMMM yyyy");
	private Calendar calStartDate = Calendar.getInstance();
	private Calendar calToday = Calendar.getInstance();
	private Calendar calCalendar = Calendar.getInstance();
	private Calendar calSelected = Calendar.getInstance();
	LinearLayout layContent = null;
	Button btnPrev = null;
	Button btnToday = null;
	TextView textToday = null;
	Button btnNext = null;
	private int iFirstDayOfWeek = Calendar.MONDAY;
	private int iMonthViewCurrentMonth = 0;
	private int iMonthViewCurrentYear = 0;
	public static final int SELECT_DATE_REQUEST = 111;
	private static final int iDayCellSize = 40;
	private static final int iDayHeaderHeight = 40;
	private static final int iTotalWidth = (iDayCellSize * 7);
	private TextView tv;
	private int mYear;
	private int mMonth;
	private int mDay;
	private int flag_plan = 0;
	Context mActivity;
	DayPlanActivity mDayPlan = null;
	WeekPlanActivity mWeekPlan = null;

	public View init(Context context) {

		mActivity = context;
		iFirstDayOfWeek = Calendar.MONDAY;
		mYear = calSelected.get(Calendar.YEAR);
		mMonth = calSelected.get(Calendar.MONTH);
		mDay = calSelected.get(Calendar.DAY_OF_MONTH);
		// generateContentView();

		return generateContentView();
	}

	public void init2() {
		calStartDate = getCalendarStartDate();
		DateWidgetDayCell daySelected = updateCalendar();
		updateControlsState();
		if (daySelected != null)
			daySelected.requestFocus();

	}

	public void setFlag(int flag) {
		this.flag_plan = flag;
		if (flag_plan == 1) {
			mWeekPlan = (WeekPlanActivity) mActivity;
		} else {
			mDayPlan = (DayPlanActivity) mActivity;
		}

	}

	private LinearLayout createLayout(int iOrientation) {
		LinearLayout lay = new LinearLayout(mActivity);
		lay.setLayoutParams(new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		lay.setOrientation(iOrientation);
		return lay;
	}

	/**
	 * 获取日历头button
	 * 
	 * @param layTopControls
	 */
	private void generateTopButtons(LinearLayout layTopControls) {
		final int iHorPadding = 24;
		textToday = new TextView(mActivity);
		textToday.setLayoutParams(new LayoutParams(218,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT));
		textToday.setTextColor(0xffffffff);
		textToday.setPadding(iHorPadding, textToday.getPaddingTop(),
				iHorPadding, textToday.getPaddingBottom());
		textToday.setGravity(Gravity.CENTER);
		textToday.setTextSize(28);
		textToday.setTextColor(0xffffffff);

		textToday.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// setTodayViewItem();
			}
		});

		ImageButton btnPrev = new ImageButton(mActivity);
		btnPrev.setLayoutParams(new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		btnPrev.setBackgroundColor(0x00000000);
		btnPrev.setImageResource(R.drawable.button_left);
		btnPrev.setPadding(0, 4, 0, 4);

		ImageButton btnNext = new ImageButton(mActivity);

		btnNext.setLayoutParams(new LayoutParams(
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
		btnNext.setBackgroundColor(0x00000000);
		btnNext.setImageResource(R.drawable.button_right);
		btnNext.setPadding(0, 4, 0, 4);
		// set events
		btnPrev.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {

				setPrevViewItem();
			}
		});

		btnNext.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				setNextViewItem();
			}
		});

		layTopControls.setGravity(Gravity.CENTER_HORIZONTAL);
		layTopControls.addView(btnPrev);
		layTopControls.addView(textToday);
		layTopControls.addView(btnNext);

	}

	/**
	 * 获取日历View
	 * 
	 * @return
	 */
	private View generateContentView() {
		LinearLayout layMain = createLayout(LinearLayout.VERTICAL);
		layMain.setGravity(Gravity.CENTER);
		layMain.setPadding(0, 0, 0, 0);
		layMain.setBackgroundResource(R.drawable.calendar_bg);
		//layMain.setBackgroundColor(0xff1D2228);
		LinearLayout layTopControls = createLayout(LinearLayout.HORIZONTAL);
		layTopControls.setGravity(Gravity.CENTER);
		//layTopControls.setBackgroundColor(0xff272a31);
		layTopControls.setPadding(0, 10, 0, 10);

		layContent = createLayout(LinearLayout.VERTICAL);
		layContent.setPadding(1, 0, 1, 0);
		generateTopButtons(layTopControls);
		generateCalendar(layContent);
		layMain.addView(layTopControls);
		layMain.addView(layContent);

		tv = new TextView(mActivity);
		layMain.addView(tv);
		return layMain;
	}

	/**
	 * 日历的水平单元
	 * 
	 * @return
	 */
	private View generateCalendarRow() {
		LinearLayout layRow = createLayout(LinearLayout.HORIZONTAL);
		for (int iDay = 0; iDay < 7; iDay++) {
			DateWidgetDayCell dayCell = new DateWidgetDayCell(mActivity,
					iDayCellSize+2, iDayCellSize + 2);
			dayCell.setItemClick(mOnDayCellClick);
			days.add(dayCell);
			layRow.addView(dayCell);
		}
		return layRow;
	}

	/**
	 * 周一到周日
	 * 
	 * @return
	 */
	private View generateCalendarHeader() {
		LinearLayout layRow = createLayout(LinearLayout.HORIZONTAL);
		for (int iDay = 0; iDay < 7; iDay++) {
			DateWidgetDayHeader day = new DateWidgetDayHeader(mActivity,
					iDayCellSize+2, iDayHeaderHeight);
			final int iWeekDay = DayStyle.getWeekDay(iDay, iFirstDayOfWeek);
			day.setData(iWeekDay);
			layRow.addView(day);
		}
		return layRow;
	}

	/**
	 * 绘制日历方格
	 * 
	 * @param layContent
	 */
	private void generateCalendar(LinearLayout layContent) {
		layContent.addView(generateCalendarHeader());
		days.clear();
		for (int iRow = 0; iRow < 6; iRow++) {
			layContent.addView(generateCalendarRow());
		}
	}

	/**
	 * 获取日历时间
	 * 
	 * @return
	 */
	private Calendar getCalendarStartDate() {
		calToday.setTimeInMillis(System.currentTimeMillis());
		calToday.setFirstDayOfWeek(iFirstDayOfWeek);

		if (calSelected.getTimeInMillis() == 0) {
			calStartDate.setTimeInMillis(System.currentTimeMillis());
			calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		} else {
			calStartDate.setTimeInMillis(calSelected.getTimeInMillis());
			calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		}

		UpdateStartDateForMonth();

		return calStartDate;
	}

	private DateWidgetDayCell updateCalendar() {
		DateWidgetDayCell daySelected = null;
		boolean bSelected = false;
		int iDaySelected = 0;
		int iStartDay = iFirstDayOfWeek;
		final boolean bIsSelection = (calSelected.getTimeInMillis() != 0);
		int iSelectedYear = 0;
		int iSelectedMonth = 0;
		int iSelectedDay = 0;
		if (flag_plan == 1) {
			/**
			 * 判断筛选每一周的信息
			 */
			if (iStartDay == Calendar.MONDAY) {
				iDaySelected = calSelected.get(Calendar.DAY_OF_WEEK)
						- Calendar.MONDAY;
				if (iDaySelected < 0)
					iDaySelected = 6;
			}
			if (iStartDay == Calendar.SUNDAY) {
				iDaySelected = calSelected.get(Calendar.DAY_OF_WEEK)
						- Calendar.SUNDAY;
				if (iDaySelected < 0)
					iDaySelected = 6;
			}
			calSelected.add(Calendar.DAY_OF_WEEK, -iDaySelected);

		}
		int j = 0;
		calCalendar.setTimeInMillis(calStartDate.getTimeInMillis());
		for (int i = 0; i < days.size(); i++) {
			iSelectedYear = calSelected.get(Calendar.YEAR);
			iSelectedMonth = calSelected.get(Calendar.MONTH);
			iSelectedDay = calSelected.get(Calendar.DAY_OF_MONTH);
			final int iYear = calCalendar.get(Calendar.YEAR);
			final int iMonth = calCalendar.get(Calendar.MONTH);
			final int iDay = calCalendar.get(Calendar.DAY_OF_MONTH);
			final int iDayOfWeek = calCalendar.get(Calendar.DAY_OF_WEEK);
			DateWidgetDayCell dayCell = days.get(i);
			// check today
			boolean bToday = false;
			if (calToday.get(Calendar.YEAR) == iYear)
				if (calToday.get(Calendar.MONTH) == iMonth)
					if (calToday.get(Calendar.DAY_OF_MONTH) == iDay)
						bToday = true;
			// check holiday
			boolean bHoliday = false;
			if ((iDayOfWeek == Calendar.SATURDAY)
					|| (iDayOfWeek == Calendar.SUNDAY))
				bHoliday = true;
			if ((iMonth == Calendar.JANUARY) && (iDay == 1))
				bHoliday = true;

			dayCell.setData(iYear, iMonth, iDay, bToday, bHoliday,
					iMonthViewCurrentMonth);
			bSelected = false;

			if (bIsSelection)
				if ((iSelectedDay == iDay) && (iSelectedMonth == iMonth)
						&& (iSelectedYear == iYear)) {
					bSelected = true;
					if (flag_plan == 1) {
						j++;
						if (j < 7) {

							calSelected.add(Calendar.DAY_OF_WEEK, 1);
							// dayCell.setSelected(bSelected);
						}
					} else {

					}

				}
			dayCell.setSelected(bSelected);
			if (bSelected)
				daySelected = dayCell;
			calCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		layContent.invalidate();

		return daySelected;
	}

	/**
	 * 设置月和周的起止日期
	 */
	private void UpdateStartDateForMonth() {
		iMonthViewCurrentMonth = calStartDate.get(Calendar.MONTH);
		iMonthViewCurrentYear = calStartDate.get(Calendar.YEAR);
		calStartDate.set(Calendar.DAY_OF_MONTH, 1);
		UpdateCurrentMonthDisplay();
		// update days for week
		int iDay = 0;
		int iStartDay = iFirstDayOfWeek;
		if (iStartDay == Calendar.MONDAY) {
			iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY;
			if (iDay < 0)
				iDay = 6;
		}
		if (iStartDay == Calendar.SUNDAY) {
			iDay = calStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
			if (iDay < 0)
				iDay = 6;
		}
		calStartDate.add(Calendar.DAY_OF_WEEK, -iDay);

	}

	/**
	 * 当前时间
	 */
	private void UpdateCurrentMonthDisplay() {
		textToday.setText(new StringBuilder()
				.append(calStartDate.get(Calendar.YEAR)).append("/")
				.append(format(calStartDate.get(Calendar.MONTH) + 1)));
		// String s = calCalendar.get(Calendar.YEAR) + "/"
		// + (calCalendar.get(format(mMonth + 1)));//
		// dateMonth.format(calCalendar.getTime());
		// mYear = calCalendar.get(Calendar.YEAR);
		// textToday.setText(s);
	}

	private void setPrevViewItem() {
		iMonthViewCurrentMonth--;
		if (iMonthViewCurrentMonth == -1) {
			iMonthViewCurrentMonth = 11;
			iMonthViewCurrentYear--;
		}
		calStartDate.set(Calendar.DAY_OF_MONTH, 1);
		calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
		calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
		UpdateStartDateForMonth();
		updateCalendar();

	}

	private void setTodayViewItem() {
		calToday.setTimeInMillis(System.currentTimeMillis());
		calToday.setFirstDayOfWeek(iFirstDayOfWeek);
		calStartDate.setTimeInMillis(calToday.getTimeInMillis());
		calStartDate.setFirstDayOfWeek(iFirstDayOfWeek);
		calSelected.setTimeInMillis(calToday.getTimeInMillis());

		UpdateStartDateForMonth();
		updateCalendar();
	}

	private void setNextViewItem() {
		iMonthViewCurrentMonth++;
		if (iMonthViewCurrentMonth == 12) {
			iMonthViewCurrentMonth = 0;
			iMonthViewCurrentYear++;
		}
		calStartDate.set(Calendar.DAY_OF_MONTH, 1);
		calStartDate.set(Calendar.MONTH, iMonthViewCurrentMonth);
		calStartDate.set(Calendar.YEAR, iMonthViewCurrentYear);
		UpdateStartDateForMonth();
		updateCalendar();
	}

	private DateWidgetDayCell.OnItemClick mOnDayCellClick = new DateWidgetDayCell.OnItemClick() {
		public void OnClick(DateWidgetDayCell item) {
			calSelected.setTimeInMillis(item.getDate().getTimeInMillis());

			// 点击单元调用主界面函数
			if (flag_plan == 1) {

				System.out.println("点击-----22222222222--------------------事件");
				mWeekPlan.setDate(calSelected);
			} else {
				item.setSelected(true);
				System.out.println("点击-----22222222222--------------------事件");
				item.setSelected(true);
				mDayPlan.setDate(calSelected);
			}

			updateCalendar();
			updateControlsState();

		}
	};

	private void updateControlsState() {
		// SimpleDateFormat dateFull = new SimpleDateFormat("d MMMM yyyy");
		mYear = calSelected.get(Calendar.YEAR);
		mMonth = calSelected.get(Calendar.MONTH);
		mDay = calSelected.get(Calendar.DAY_OF_MONTH);
		textToday.setText(new StringBuilder().append(mYear).append("/")
				.append(format(mMonth + 1)));

	}

	private String format(int x) {
		String s = "" + x;
		if (s.length() == 1)
			s = "0" + s;
		return s;
	}

	/**
	 * 主界面点击回到今天内部接口
	 */
	public void backToday() {
		setTodayViewItem();
		String s = calToday.get(Calendar.YEAR) + "/"
				+ (calToday.get(Calendar.MONTH) + 1);
	}

}
