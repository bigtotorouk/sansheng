package com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.activity.custome.CustomeAdapter;

public class SideBar extends View {
	private char[] alpha;
	private SectionIndexer sectionIndexter = null;
	private ListView list;
	private TextView mDialogText;
	private int m_nItemHeight = 30;
	private Context context;
	private int height;
	private Context c;
	private int searchHeight = 17;

	public SideBar(Context context) {
		super(context);
		c = context;
		init();
	}

	public SideBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		c = context;
		init();
	}

	private void init() {

		alpha = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
				'W', 'X', 'Y', 'Z' };
	}

	public SideBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void setListView(ListView _list) {
		list = _list;
		sectionIndexter = (SectionIndexer) _list.getAdapter();
	}

	public void setTextView(TextView mDialogText) {
		this.mDialogText = mDialogText;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		int i = (int) event.getY();
		int idx = i / m_nItemHeight;
		if (idx >= alpha.length) {
			idx = alpha.length - 1;
		} else if (idx < 0) {
			idx = 0;
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE) {
			mDialogText.setVisibility(View.VISIBLE);
			mDialogText.setText("" + alpha[idx]);
			if (sectionIndexter == null) {
				sectionIndexter = ((CustomeAdapter) ((HeaderViewListAdapter) list
						.getAdapter()).getWrappedAdapter());
			}
			// Log.e("debug", "idx" + idx);
			int position = sectionIndexter.getPositionForSection(alpha[idx]);
			// if (idx == 0) {
			// position = 0;
			// }
			// if (idx == 1) {
			// position = 1;
			// }
			// if (idx == 2) {
			// position = 2;
			// }
			// if (position == -1) {
			// return true;
			// }
			list.setSelection(position);
		} else {
			mDialogText.setVisibility(View.INVISIBLE);
		}
		return true;
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		// setBackgroundColor(Color.parseColor("#ff00ff"));
		paint.setColor(Color.parseColor("#555555"));
		paint.setTextSize(22);
		paint.setAntiAlias(true);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextAlign(Paint.Align.CENTER);
		if (getMeasuredHeight() > height) {
			height = getMeasuredHeight();
		}
		Log.e("debug", "h:" + height + "  mh:" + getMeasuredHeight());
		m_nItemHeight = getMeasuredHeight() / (alpha.length);
		float widthCenter = getMeasuredWidth() / 2;
		for (int i = 0; i < alpha.length; i++) {

			canvas.drawText(String.valueOf(alpha[i]), widthCenter,
					m_nItemHeight + (i * m_nItemHeight), paint);
		}
		super.onDraw(canvas);
	}
}
