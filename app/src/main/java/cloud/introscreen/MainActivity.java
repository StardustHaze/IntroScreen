package cloud.introscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;



public class MainActivity extends Activity {



    private ViewPager viewPager;

    private IntroManager introManager;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView[] dots;
    private Button next, skip;
    private LinearLayout dotsLayout;



    private int[] layouts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //checking for first time launch - before calling setContentView
        introManager = new IntroManager(this);
        if (!introManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        //making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21)
        {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
            //requestWindowFeature(Window.FEATURE_NO_TITLE);
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }


        setContentView(R.layout.activity_main);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.LayoutDots);
        skip = (Button) findViewById(R.id.btn_skip);
        next = (Button) findViewById(R.id.btn_next);
                //for(int i : )

        //layouts of all screens
                    layouts = new int[]{
                    R.layout.activity_screen1,
                    R.layout.activity_screen2,
                    R.layout.activity_screen3,
                    R.layout.activity_screen4,
                    R.layout.activity_screen5};

        //adding bottom dots
        addBottomDots(0);

        //making notification bar transparent
        changeStatusBarColor();


        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);



        skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   launchHomeScreen();
                }
            });

    next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //checking for last page
                //if last page home screen will be launched

            int current = getItem(+1);
            if (current < layouts.length) {
                //move to next screen
                viewPager.setCurrentItem(current);
            } else {
                launchHomeScreen();
                }
            }
        });
    }



private void addBottomDots(int position)
        {

        dots = new TextView[layouts.length];


        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorInactive = getResources().getIntArray(R.array.dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive[position]);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[position].setTextColor(colorActive[position]);
        }

    private int getItem(int i)
        {
        return viewPager.getCurrentItem() + i;
        }
    private void launchHomeScreen() {
        introManager.setFirstTimeLaunch(false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }



    //viewpager change listener
     ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
        {

@Override
public void onPageScrolled(int arg0,float arg1 ,int arg2){

        }


@Override
public void onPageSelected(int position){

        addBottomDots(position);

    //changing the next button text 'Proceed'
        if(position==layouts.length-1)
        {
            //last pasge, make button text 'Proceed'
            next.setText(getString(R.string.proceed));
            skip.setVisibility(View.GONE);
        }
        else
        {
            //still pages are left
            next.setText(getString(R.string.next));
            skip.setVisibility(View.VISIBLE);
        }
        }


@Override
public void onPageScrollStateChanged(int arg0){

        }

        };


    //making notification bar transparent
    private void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        }
}

    //view pager adapter

    public class ViewPagerAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;

        public ViewPagerAdapter(){

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position], container, false);
            container.addView(v);
            return v;
        }



        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }}}

