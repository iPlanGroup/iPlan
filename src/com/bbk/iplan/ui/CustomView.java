package com.bbk.iplan.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class CustomView extends LinearLayout {

	private boolean s1 = true;
	Scroller mScroller = null;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScroller = new Scroller(context);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//			scrollTo(0, mScroller.getCurrY());
			postInvalidate();
		}
	}

	public void beginScroll(int startX, int startY,int dx,int dy,int durating) {
		if (!s1) {
			mScroller.startScroll(0, 0, 0, 0, 1000);
			// 0 0 0 0 1000
			s1 = true;
		} else {
			mScroller.startScroll(startX, startY, dx, dy, durating);
			//0 0 320 0 1000
			s1 = false;
		}
		invalidate();
	}
}
