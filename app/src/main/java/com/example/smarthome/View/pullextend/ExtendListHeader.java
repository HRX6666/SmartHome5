package com.example.smarthome.View.pullextend;

import android.content.Context;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome.Helper.UIHelper;
import com.example.smarthome.R;


/**
 * 这个类封装了下拉刷新的布局
 */
public class ExtendListHeader extends ExtendLayout {


    float containerHeight = UIHelper.dip2px(60);
    float listHeight = UIHelper.dip2px(500);
    boolean arrivedListHeight = false;
    private RecyclerView mRecyclerView;
    ImageView sun;

    /**
     * 原点
     */

    private ExpendPoint mExpendPoint;

    /**
     * 构造方法
     *
     * @param context context
     */
    public ExtendListHeader(Context context) {
        super(context);

    }


    /**
     * 构造方法
     *
     * @param context context
     * @param attrs   attrs
     */
    public ExtendListHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(getContext(), R.layout.extend_header, this);
        sun = view.findViewById(R.id.sun);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 在此处获取listHeight 和 containerHeight值
        listHeight = mRecyclerView == null ? UIHelper.dip2px(120) : mRecyclerView.getMeasuredHeight();
        containerHeight = listHeight / 2;
    }

    @Override
    protected void bindView(View container) {
        mRecyclerView = findViewById(R.id.list);
        mExpendPoint = findViewById(R.id.expend_point);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    protected View createLoadingView(Context context, AttributeSet attrs) {
        return LayoutInflater.from(context).inflate(R.layout.extend_header, null);
    }


    @Override
    public int getContentSize() {
        return (int) (containerHeight);
    }

    @Override
    public int getListSize() {
        return (int) (listHeight);
    }


    @Override
    protected void onReset() {
        mExpendPoint.setVisibility(VISIBLE);
        mExpendPoint.setAlpha(1);
        mExpendPoint.setTranslationY(0);
        mRecyclerView.setTranslationY(0);
        arrivedListHeight = false;
    }

    @Override
    protected void onReleaseToRefresh() {
    }

    @Override
    protected void onPullToRefresh() {

    }

    @Override
    protected void onArrivedListHeight() {
        arrivedListHeight = true;
    }

    @Override
    protected void onRefreshing() {

    }

    @Override
    public void onPull(int offset) {
        if (!arrivedListHeight) {
            mExpendPoint.setVisibility(VISIBLE);
            float percent = Math.abs(offset) / containerHeight;
            int moreOffset = Math.abs(offset) - (int) containerHeight;
            if (percent <= 1.0f) {
                mExpendPoint.setPercent(percent);
                mExpendPoint.setTranslationY(-Math.abs(offset) / 2 + mExpendPoint.getHeight() / 2);
                mRecyclerView.setTranslationY(-containerHeight);
            } else {
                float subPercent = (moreOffset) / (listHeight - containerHeight);
                subPercent = Math.min(1.0f, subPercent);
                mExpendPoint.setTranslationY(-(int) containerHeight / 2 + mExpendPoint.getHeight() / 2 + (int) containerHeight * subPercent / 2);
                mExpendPoint.setPercent(1.0f);
                float alpha = (1 - subPercent * 2);
                mExpendPoint.setAlpha(Math.max(alpha, 0));
                mRecyclerView.setTranslationY(-(1 - subPercent) * containerHeight);
            }
        }
        if (Math.abs(offset) >= listHeight) {
            mExpendPoint.setVisibility(INVISIBLE);
            mRecyclerView.setTranslationY(-(Math.abs(offset) - listHeight) / 2);
        }
    }
    public void tran(){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.4f, 1.0f, 0.50f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setRepeatCount(100);
//        rongYi.startAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
//        alphaAnimation.setFillAfter(fragment);
        alphaAnimation.setDuration(3000);
//        fragment.startAnimation(alphaAnimation);
        TranslateAnimation translateAnimation=new TranslateAnimation(0f,0f,100f,-100f);
        translateAnimation.setDuration(3000);
        translateAnimation.setRepeatCount(100);
//        rongYi.startAnimation(translateAnimation);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
         sun.startAnimation(animationSet);
    }


}