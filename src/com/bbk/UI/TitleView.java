package cn.eebbk.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class TitleView extends TextView {

	public TitleView(Context context) {
		super(context);
	}

	public TitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void onDraw(Canvas canvas) {
		// ¥¥Ω®ª≠± 
		Paint paint = new Paint();
		// …Ë÷√—’…´
		paint.setColor(0xffDCDCDC);
		// …Ë÷√¥÷œ∏
		paint.setStrokeWidth(3f);
		// …Ë÷√æ‚≥›
		paint.setAntiAlias(false);

		int viewWidth = this.getWidth();
		int viewHeight = this.getHeight();
		canvas.drawLine(0, 0, 0, viewHeight, paint);
		canvas.drawLine(viewWidth, 0, viewWidth, viewHeight, paint);
		canvas.drawLine(0, 0, viewWidth, 0, paint);
		canvas.drawLine(0, viewHeight, viewWidth, viewHeight, paint);

		super.onDraw(canvas);
	}

}
