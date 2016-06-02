package com.structure.greenboard.Activities.Tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.structure.greenboard.Activities.NextActivity.NextActivity;
import com.structure.greenboard.MyApplication;
import com.structure.greenboard.R;
import com.structure.greenboard.util.MSharedPreference;

/**
 * Created by abhishekdewa on 5/31/2016.
 */
public class TutorialActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private int dotsCount;
    private TutorialPagerAdapter mAdapter;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    private TextView btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_screen);
        initUiElements();
        btnNext.setVisibility(View.GONE);
        MSharedPreference.getSharedPref(MyApplication.getAppContext()).setTutorialFlag();
        Bundle bundle1, bundle2, bundle3;
        Fragment fragment = new TutorialFragment();
        bundle1 = new Bundle();
        bundle1.putString("title", "Request Organization");
        bundle1.putString("sub", "Just Email us to register your Organization");
        bundle1.putInt("img", R.mipmap.request);
        fragment.setArguments(bundle1);
        mAdapter.addFragment(fragment);

        fragment = new TutorialFragment();
        bundle2 = new Bundle();
        bundle2.putString("title", "Add Subscription");
        bundle2.putString("sub", "Add multiple subscription so that you will get notified");
        bundle2.putInt("img", R.mipmap.subscription1);
        fragment.setArguments(bundle2);

        mAdapter.addFragment(fragment);
        fragment = new TutorialFragment();
        bundle3 = new Bundle();
        bundle3.putString("title", "Broadcast");
        bundle3.putString("sub", "Broadcast your event and let other's know about it");
        bundle3.putInt("img", R.mipmap.broadcast1);
        fragment.setArguments(bundle3);
        mAdapter.addFragment(fragment);
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(this);
        setUiPageViewController();
    }

    private void initUiElements() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pager_indicator = (LinearLayout) findViewById(R.id.pagerIndicator);
        btnNext = (TextView) findViewById(R.id.btnNext);
        mAdapter = new TutorialPagerAdapter(getSupportFragmentManager());
    }

    private void setUiPageViewController() {
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_color));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selected_color));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == mAdapter.getCount() - 1) {
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText("Finish");
            btnNext.setOnClickListener(btnClickListener);
        } else
            btnNext.setVisibility(View.GONE);
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.non_selected_color));
        }
        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selected_color));
    }

    View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(TutorialActivity.this, NextActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
