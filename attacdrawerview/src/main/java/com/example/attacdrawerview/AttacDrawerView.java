package com.example.attacdrawerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;

public class AttacDrawerView extends LinearLayout {

    private float mSpaceThan;
    private View bindView;
    private View contentView;
    private int contentViewHeight;

    public AttacDrawerView(Context context, @LayoutRes int layoutRes) {
        super(context);
        initView(context, layoutRes);
    }

    private void initView(Context context, int layoutRes) {
        setOrientation(VERTICAL);
        contentView = LayoutInflater.from(context).inflate(layoutRes, this, false);
        addView(contentView);
        contentViewHeight = unDisplayViewSize(contentView)[1];
        ViewGroup.LayoutParams contentlayoutParams = contentView.getLayoutParams();
        contentlayoutParams.height = 0;
        contentView.setLayoutParams(contentlayoutParams);
    }

    public int[] unDisplayViewSize(View view) {
        int size[] = new int[2];
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        size[0] = view.getMeasuredWidth();
        size[1] = view.getMeasuredHeight();
        return size;
    }

    /**
     * 默认和依附控件 宽统一
     *
     * @param view 需要依附的view 会把view的点击和移动一起监听 如果需要监听该View的点击事件请设置setOnAttacDrawerClickListener();
     */
    public void bindView(View view) {
        bindView(view, 0);
    }

    /**
     * @param view      需要依附的view 会把view的点击和移动一起监听 如果需要监听该View的点击事件请设置setOnAttacDrawerClickListener();
     * @param spaceThan 控件总占比
     */
    public void bindView(View view, float spaceThan) {
        bindView = view;
        this.mSpaceThan = spaceThan;
        if (view != null)
            view.post(new Runnable() {
                @Override
                public void run() {
                    int index = 0;
                    ViewParent parent = bindView.getParent();
                    if (parent instanceof ViewGroup) {
                        ViewGroup viewGroup = (ViewGroup) parent;
                        int count = viewGroup.getChildCount();

                        for (int i = 0 ; i < count ; i++) {
                            if (bindView == viewGroup.getChildAt(i)) {
                                index = i + 1;
                                break;
                            }
                        }
                        viewGroup.addView(AttacDrawerView.this, index);
                    }

                    int top = bindView.getTop();
                    int height = bindView.getHeight();
                    int newTop = top + height;
                    int left = (int) (bindView.getLeft() + ((bindView.getWidth() * mSpaceThan) / 2));
                    //int right = (int) (view.getLeft() + view.getWidth() - view.getWidth() * spaceThan);
                    setY(newTop);
                    setX(left);
                    ViewGroup.LayoutParams layoutParams = getLayoutParams();
                    layoutParams.width = (int) (bindView.getWidth() - bindView.getWidth() * mSpaceThan);
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    setLayoutParams(layoutParams);
                    bindView.setOnTouchListener(new OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            upYX();
                            return false;
                        }
                    });
                    bindView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getAnimator().start();
                            if (mOnAttacDrawerClickListener != null)
                                mOnAttacDrawerClickListener.onClick(v);
                        }
                    });
                }
            });
    }

    private int animDuration = 500;

    private ViewGroup.LayoutParams contentViewlayoutParams;

    public void setAnimDuration(int animDuration) {
        this.animDuration = animDuration;
    }

    private ValueAnimator getAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1);
        contentViewlayoutParams = contentView.getLayoutParams();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                if (isShow) {
                    int newHeight = (int) (contentViewHeight * (1 - fraction));
                    if (contentViewlayoutParams != null) {
                        contentViewlayoutParams.height = newHeight;
                    }
                } else {
                    int newHeight = (int) (contentViewHeight * fraction);
                    if (contentViewlayoutParams != null) {
                        contentViewlayoutParams.height = newHeight;
                    }
                }
                contentView.setLayoutParams(contentViewlayoutParams);
            }
        });
        valueAnimator.setDuration(animDuration);
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isShow = !isShow;
            }
        });
        return valueAnimator;
    }

    private boolean isShow = false;

    public void upYX() {
        int top = bindView.getTop();
        int height = bindView.getHeight();
        int newTop = top + height;
        int left = (int) (bindView.getLeft() + ((bindView.getWidth() * mSpaceThan) / 2));
        setY(newTop);
        setX(left);
    }

    private OnAttacDrawerClickListener mOnAttacDrawerClickListener;

    public void setOnAttacDrawerClickListener(OnAttacDrawerClickListener onAttacDrawerClickListener) {
        mOnAttacDrawerClickListener = onAttacDrawerClickListener;
    }

    public interface OnAttacDrawerClickListener {
        void onClick(View v);
    }
}
