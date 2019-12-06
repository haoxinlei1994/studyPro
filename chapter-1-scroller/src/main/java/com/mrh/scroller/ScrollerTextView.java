package com.mrh.scroller;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Scroller;

/**
 * 可以跟随手指滑动，带有fling效果的textView
 * Created by haoxinlei on 2019-12-03.
 */
public class ScrollerTextView extends AppCompatTextView {
    /**
     * 跟随手指滑动的时候记录下上一次的Y坐标
     */
    private float mLastY;
    /**
     * 滑动Scroller
     */
    private Scroller mScroller;
    /**
     * 速度跟踪器
     */
    private VelocityTracker mVelocityTracker;
    /**
     * fling 的最小速度
     */
    private float mMinV;
    /**
     * fling 的最大速度
     */
    private float mMaxV;
    /**
     * 通过scroller开始fling的过程中，记录上一次fling的Y坐标
     */
    private int mInitFlingY;

    public ScrollerTextView(Context context) {
        super(context);
        init();
    }

    public ScrollerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext(), null, true);
        mMinV = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
        mMaxV = ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = mLastY - event.getY();
                scrollBy(0, (int) dy);
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //速度的最大值mMaxV
                mVelocityTracker.computeCurrentVelocity(1000, mMaxV);
                //手指抬起的时候，计算此时的Y方向的速度
                int vy = (int) mVelocityTracker.getYVelocity();
                if (Math.abs(vy) > mMinV) {
                    mInitFlingY = getScrollY();
                    mScroller.fling(0, getScrollY(), 0, vy, 0, 0, -Integer.MAX_VALUE, Integer.MAX_VALUE);
                    invalidate();
                }

                mVelocityTracker.recycle();
                mVelocityTracker = null;
                break;
        }

        return true;
    }

    @Override
    public void computeScroll() {
        //计算当前时刻最新的坐标值，如果整个过程还没有结束，会返回true
        if (mScroller.computeScrollOffset()) {
            //获取当前最新的Y坐标
            int currY = mScroller.getCurrY();
            //用上一个时刻的Y坐标减去当前时刻的Y坐标得到一个差值
            int diff = mInitFlingY - currY;
            //滚动内容
            scrollBy(0, diff);
            //再次触发invalidate使View不断重绘，也就实现了在fling的整个过程中，随着时间的流逝
            //View 不断的回调computeScroll方法 -> 获取新的坐标 -> 滚动内容 -> 再次出发重绘
            //这样在视觉上就出现了fling的效果
            postInvalidate();
            mInitFlingY = mScroller.getCurrY();
        }
    }
}
