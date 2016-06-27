package com.DGuard.liveVideo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.DGuard.liveVideo.MyScrollView.OnScrollStopListner;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MainActivity extends Activity implements TabFrameLayout.TabPageChangeListener {
	private static final String TAG = "MainActivity"; 
	private ViewPager advPager = null;
	private AtomicInteger what = new AtomicInteger(0);
	private int PAGESIZE = 8;
	List<CourseBean> courseBeans;
    private String[] tilteTabtext = {
    	"影视分类",
    	"精彩推荐",
    	"设置"
    };
    
    private int [] videoItemColNum = {
    	4,4,3
    };
    
    TabFrameLayout Tabfl;
    //HorizontalScrollView videtypeTabpage;
    HorizontalScrollView videtypeTabpage;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Tabfl =  (TabFrameLayout) findViewById(R.id.tabutton);
		Tabfl.CreateTabButton(tilteTabtext);
		Tabfl.setPageTabControl(this);
		courseBeans = new ArrayList<CourseBean>();
		loadData();
		initViewPager();
		displaydpi();
	}
	
	private void displaydpi(){
		String str = ""; 
        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getApplicationContext().getResources().getDisplayMetrics(); 
        int screenWidth = dm.widthPixels; 
        int screenHeight = dm.heightPixels; 
        float density = dm.density; 
        str += "屏幕分辨率为:" + dm.widthPixels + " * " + dm.heightPixels + "\n"; 
        str += "绝对宽度:" + String.valueOf(screenWidth) + "pixels\n"; 
        str += "绝对高度:" + String.valueOf(screenHeight) 
                + "pixels\n"; 
        str += "逻辑密度:" + String.valueOf(density) 
                + "\n"; 
        str += "屏幕长 :" + String.valueOf(dm.widthPixels/density) + "dp\n"; 
        str += "屏幕款:" + String.valueOf(dm.heightPixels/density) + "dp\n"; 
        Log.i("displaydpi", str); 
	}
	
	private void initViewPager() {
		advPager = (ViewPager) findViewById(R.id.adv_pager);
		List<View> contentGridViews = new ArrayList<View>();

		//test
		//videtypeTabpage = (HorizontalScrollView)getLayoutInflater().inflate(R.layout.layout_videotype_page, null);
		//contentGridViews.add(videtypeTabpage);
		
		for (int i = 0; i < courseBeans.size() / PAGESIZE+1; i++) {
			Log.i(TAG, "载入第" + i + "页");
			GridView gridView = new GridView(this);
			int start = i * PAGESIZE;
			int end = i * PAGESIZE + PAGESIZE;
			end = end > courseBeans.size() ? courseBeans.size() : end;
			gridView.setAdapter(new CourseAdapter(courseBeans.subList(start,end), this));
			gridView.setNumColumns(videoItemColNum[i]);
			gridView.setPadding(45, 0, 45, 0);
			contentGridViews.add(gridView);
		}
		//advPager.setOffscreenPageLimit(3);courseBeans = new ArrayList<CourseBean>();courseBeans = new ArrayList<CourseBean>();dimens.xmldimens.xmldimens.xml
		advPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin));
		advPager.setAdapter(new PageAdapter(contentGridViews));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
	}

	/**
	 * 获取数据逻辑
	 */
	private void loadData() {
		
		courseBeans.add(new CourseBean(R.color.nowpublic,"直播",R.drawable.metro_icon1,""));
		courseBeans.add(new CourseBean(R.drawable.metro_bg2,"电视剧",R.drawable.metro_icon2,""));
		courseBeans.add(new CourseBean(R.drawable.metro_bg3,"电影",R.drawable.metro_icon3,""));
		courseBeans.add(new CourseBean(R.color.police,"综艺",R.drawable.metro_icon4,""));
		courseBeans.add(new CourseBean(R.color.idcard,"喜好",R.drawable.metro_icon5,""));
		courseBeans.add(new CourseBean(R.color.clean,"播放历史",R.drawable.metro_icon6,""));
		courseBeans.add(new CourseBean(R.color.business,"体育",R.drawable.metro_icon7,""));
		courseBeans.add(new CourseBean(R.color.building,"搜索",R.drawable.metro_icon8,""));
		
		courseBeans.add(new CourseBean(R.drawable.metro_moive1,"搜索",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive2,"搜索",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive3,"搜索",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive4,"搜索",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive5,"搜索",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive6,"搜索",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive7,"搜索",0,""));
		courseBeans.add(new CourseBean(R.drawable.metro_moive8,"搜索",0,""));
		
		courseBeans.add(new CourseBean(R.color.traffic,"直播设置",R.drawable.metro_icon9,""));
		courseBeans.add(new CourseBean(R.color.chat,"点播设置",R.drawable.metro_icon10,""));
		courseBeans.add(new CourseBean(R.color.student,"帐号设置",R.drawable.metro_icon11,""));
		courseBeans.add(new CourseBean(R.color.lightbulb,"购买权限",R.drawable.metro_icon12,""));
	}

	/**
	 * 设置页面指示器
	 * 
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageSelected(int arg0) {
			what.getAndSet(arg0);
			Tabfl.SelectAniTab(arg0);
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}

	}
	
	@Override
	public void SelPageTab(int index) {
		// TODO Auto-generated method stub
		if(index!=advPager.getCurrentItem())
		{
			advPager.setCurrentItem(index);
		}
			
	}
}
