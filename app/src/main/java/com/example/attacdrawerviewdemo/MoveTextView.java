package com.example.attacdrawerviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

public class MoveTextView extends AppCompatTextView {
    private int moveX;

    public MoveTextView(Context context) {
        super(context);
    }

    public MoveTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //@Override
    //public boolean onTouchEvent(MotionEvent event) {
    //    switch (event.getAction()) {
    //        case MotionEvent.ACTION_DOWN:
    //            offsetLeftAndRight((int) event.getX() - (getWidth() / 2));
    //            offsetTopAndBottom((int) event.getY() - (getHeight() / 2));
    //            break;
    //        case MotionEvent.ACTION_MOVE:
    //            //调用layout方法来重新放置它的位置
    //            offsetLeftAndRight((int) event.getX() - (getWidth() / 2));
    //            offsetTopAndBottom((int) event.getY() - (getHeight() / 2));
    //            if (mOnMoveListener != null) {
    //                mOnMoveListener.onMove();
    //            }
    //            break;
    //    }
    //    return super.onTouchEvent(event);
    //}

    private OnMoveListener mOnMoveListener;

    public void setOnMoveListener(OnMoveListener onMoveListener) {
        mOnMoveListener = onMoveListener;
    }

    public interface OnMoveListener {
        void onMove();
    }
}
