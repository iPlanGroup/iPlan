package com.bbk.iplan.calendar;

import java.util.Calendar;

import com.bbk.iplan.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

public class DateWidgetDayHeader extends View {
	// fields
	private final static int iDayHeaderFontSize = 18;

	// fields
	private Paint pt = new Paint();
	private RectF rect = new RectF();
	private int iWeekDay = -1;
	private boolean bHoliday = false;

	// methods
	public DateWidgetDayHeader(Context context, int iWidth, int iHeight) {
		super(context);
		setLayoutParams(new LayoutParams(iWidth, iHeight));
		setBackgroundResource(R.drawable.calendar_week_bg);
	}

	public void setData(int iWeekDay) {
		this.iWeekDay = iWeekDay;
		this.bHoliday = false;
		if ((iWeekDay == Calendar.SATURDAY) || (iWeekDay == Calendar.SUNDAY))
			this.bHoliday = true;
	}

	private void drawDayHeader(Canvas canvas) {
		if (iWeekDay != -1) {
			// background
			pt.setColor(DayStyle.getColorFrameHeader(bHoliday));
			canvas.drawRect(rect, pt);
			// text
			pt.setTypeface(Typeface.SERIF);
			pt.setTextSize(iDayHeaderFontSize);
			pt.setAntiAlias(true);
			pt.setFakeBoldText(false);
			pt.setColor(DayStyle.getColorTextHeader(bHoliday));

			final int iTextPosY = getHeight()/2 + getTextHeight()/2 - 2;
			final String sDayName = DayStyle.getWeekDayName(iWeekDay);

			// draw day name
			final int iDayNamePosX = (int) rect.left
					+ ((int) rect.width() >> 1)
					- ((int) pt.measureText(sDayName) >> 1);
			canvas.drawText(sDayName, iDayNamePosX, iTextPosY, pt);
		}
	}

	private int getTextHeight() {
		return (int) (-pt.ascent() + pt.descent());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		rect.set(0, 0, this.getWidth(), this.getHeight());
		drawDayHeader(canvas);
	}

}
