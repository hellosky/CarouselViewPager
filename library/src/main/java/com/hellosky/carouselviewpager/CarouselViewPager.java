package com.hellosky.carouselviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by hellosky on 16/7/13.
 */

public class CarouselViewPager extends ViewPager {

    private boolean mCancelled = false;
    private long delay = 2000;//delay 2 seconds by default
    private boolean isRunning = false;

    public CarouselViewPager(Context context){
        super(context);
    }

    public CarouselViewPager(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    private static final int MSG = 38;

    /**
     * Set time in Millisecond.
     */
    public void setTimeInterval(long interval){
        delay = interval;
    }

    /**
     * Cancel the carousel.
     */
    public synchronized final void cancel() {
        mCancelled = true;
        isRunning = false;
        mHandler.removeMessages(MSG);
    }

    /**
     * Start the carousel.
     */
    public synchronized final CarouselViewPager start() {
        return startDelayed(0);
    }

    /**
     * Start the carousel delayed.
     */
    public synchronized final CarouselViewPager startDelayed(long delayMillis) {
        mCancelled = false;
        if (getAdapter() == null || getAdapter().getCount() <= 1) {
            return this;
        }
        if(!isRunning){
            mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG), delayMillis);
            isRunning = true;
        }
        return this;
    }

    // handles carousel
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (CarouselViewPager.this) {
                if (mCancelled) {
                    return;
                }

                int currentItem = getCurrentItem();
                currentItem = (currentItem + 1) % getAdapter().getCount();
                setCurrentItem(currentItem);
                sendMessageDelayed(obtainMessage(MSG), delay);
            }
        }
    };
}
