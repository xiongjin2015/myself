package com.haha.myself.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;

import com.haha.myself.R;


public class ScrollHideLayout extends LinearLayout implements OnScrollListener {

	private static final String TAG = ScrollHideLayout.class.getSimpleName();

	private int scrollViewId = R.id.scrollView;
	private int changeViewId = R.id.changeView;

	private int changeViewMaxHeight;
	private PointF touchPoint = new PointF();

	private View changeView;
	private AbsListView scrollView;

	private RectF scrollViewRect = new RectF();

	public ScrollHideLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScrollHideLayout(Context context) {
		this(context, null);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public ScrollHideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int len = getChildCount();

		if (null == changeView || null == scrollView) {
			for (int i = 0; i < len; i++) {
				View child = getChildAt(i);
				// �����ؼ�
				if (child.getId() == scrollViewId && child instanceof AbsListView) {
					scrollView = (AbsListView) child;
					setScrollViewRect();
				}
				if (child.getId() == changeViewId) {
					changeView = child;
					changeView.setMinimumHeight(0);

					changeViewMaxHeight = changeView.getMeasuredHeight();
				}

			}
		} else {
			// ���¼�������ؼ���λ��
			setScrollViewRect();
		}

		Log.i(TAG, "find scrollview and changeView :" + scrollViewId + "," + changeViewId);
		Log.i(TAG, "scrollview rect:" + changeView.getLayoutParams().getClass().getCanonicalName());

		if (null == changeView || null == scrollView) {
			throw new IllegalArgumentException("could not foud changeView or scrollView");
		}
	}

	private void setScrollViewRect() {
		// ��ȡ�����ؼ��ķ�Χ
		float left = ViewCompat.getX(scrollView);
		float top = ViewCompat.getY(scrollView);
		float right = left + scrollView.getMeasuredWidth();
		float bottom = top + scrollView.getMeasuredHeight();

		scrollViewRect.left = left;
		scrollViewRect.top = top;
		scrollViewRect.right = right;
		scrollViewRect.bottom = bottom;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		if (!isScrollViewTouch(ev)) {
			return false;
		}

		final android.view.ViewGroup.LayoutParams params = changeView.getLayoutParams();

		switch (ev.getAction()) {

		case MotionEvent.ACTION_DOWN:
			touchPoint.x = ev.getX();
			touchPoint.y = ev.getY();
			break;

		case MotionEvent.ACTION_MOVE:

			int height = params.height;

			// �����ؼ��ƶ��¼�
			float distance = ev.getY() - touchPoint.y;

			// ���߶ȣ����������϶�
			if (height >= changeViewMaxHeight && distance > 0) {
				touchPoint.y = ev.getY();
				break;
			}
			// �Ѿ����� �������ϻ���
			if (height <= 0 && distance < 0) {
				touchPoint.y = ev.getY();
				break;
			}

			// listview���ﶥ���ſ��������϶�
			if (distance > 0 && scrollView.getFirstVisiblePosition() != 0) {
				touchPoint.y = ev.getY();
				break;
			}

			height = Math.round(height + distance);

			if (height > changeViewMaxHeight) {
				height = changeViewMaxHeight;
			}

			if (height <= 0 && distance < 0) {
				height = 0;
				// TODO onhide
			}

			params.height = height;

			changeView.requestLayout();

			touchPoint.x = ev.getX();
			touchPoint.y = ev.getY();

			break;

		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:

			// �߶ȳ���һ�룬�Զ�����
			int[] values = null;
			// ���ϻ���,ʣ��λ�ò���һ��
			if (params.height <= changeViewMaxHeight / 2) {
				values = new int[] { params.height, 0 };
			} else {
				values = new int[] { params.height, changeViewMaxHeight };
			}

			if (null != values) {
				ValueAnimator anim = ObjectAnimator.ofInt(changeView, "translationY", values);
				anim.addUpdateListener(new AnimatorUpdateListener() {

					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int value = (int) animation.getAnimatedValue();
						params.height = value;
						changeView.requestLayout();
					}
				});
				anim.setDuration(250);
				anim.setTarget(changeView);
				anim.start();
			}

			break;
		}

		return true;
	}
	

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// ֱ�������¼�
		this.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}

	private boolean isScrollViewTouch(MotionEvent ev) {

		float x = ev.getX();
		float y = ev.getY();
		return (x >= scrollViewRect.left && x <= scrollViewRect.right) && (y >= scrollViewRect.top && y <= scrollViewRect.bottom);
	}

	/**
	 * @return Whether it is possible for the child view of this layout to
	 *         scroll up. Override this if the child view is a custom view.
	 */
	public boolean canChildScrollUp() {
		if (android.os.Build.VERSION.SDK_INT < 14) {
			if (scrollView instanceof AbsListView) {
				final AbsListView absListView = (AbsListView) scrollView;
				return absListView.getChildCount() > 0
						&& (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
			} else {
				return ViewCompat.canScrollVertically(scrollView, -1) || scrollView.getScrollY() > 0;
			}
		} else {
			return ViewCompat.canScrollVertically(scrollView, -1);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}
}
