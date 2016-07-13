package com.carouselviewpager.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hellosky.carouselviewpager.CarouselViewPager;

public class MainActivity extends AppCompatActivity {

    private CarouselViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (CarouselViewPager) findViewById(R.id.pager);
        CustomPageAdapter adapter = new CustomPageAdapter(this);
        mPager.setAdapter(adapter);
        mPager.start();
    }

    private class CustomPageAdapter extends PagerAdapter {
        private int[] mDrawables = {R.drawable.yellow, R.drawable.blue, R.drawable.green, R.drawable.red};
        private Context mContext;

        public CustomPageAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mDrawables.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mDrawables[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO: 16/7/13
        }
    }

    @Override
    protected void onDestroy() {
        if(mPager != null){
            mPager.cancel();
        }
        super.onDestroy();
    }
}
