package com.DGuard.liveVideo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VideoTypelistActivity extends Activity {
	LinearLayout mLinearLayout;
	int screenWidth;// 屏幕宽度
	int screenHeight;// 屏幕高度
	LinearLayout videofilterl;
	ListView videoTpyelist = null;
	int      videotype = 1;
	GridView videGridlist = null;
	ImageView upImageV = null;
	ImageView downImageV = null;
	String[] itemlist = { "搜    索", "全    部", "筛    选", "最新更新", "内地热播", "日韩热播",
			"英美热播", "港台热播", "经典剧集", "情景喜剧" };

	List<VideoBeans> videoBeans;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_videotypelist);

		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		mLinearLayout = (LinearLayout) findViewById(R.id.leftL);
		mLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
				screenWidth / 6, screenHeight));
		videoTpyelist = (ListView) findViewById(R.id.listView1);
		upImageV = (ImageView) findViewById(R.id.imageView1);
		downImageV = (ImageView) findViewById(R.id.imageView2);
		videGridlist = (GridView) findViewById(R.id.gridView1);
		videofilterl = (LinearLayout)findViewById(R.id.videofilter);
		videofilterl.setVisibility(View.INVISIBLE);
		
		
		final MyVideoTypeAdapter adapter = new MyVideoTypeAdapter(itemlist,
				this);
		videoTpyelist.setAdapter(adapter);
		videoTpyelist.setDivider(null);
		
		
		videoBeans = new ArrayList<VideoBeans>();
		loadData();
		final MyListAdapter listadapter =  new MyListAdapter(videoBeans, this);
		videGridlist.setAdapter(listadapter);
		videGridlist.setNumColumns(5);
		
		videoTpyelist.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				switch (scrollState) {
				// 当不滚动时
				case OnScrollListener.SCROLL_STATE_IDLE:
					// 判断滚动到底部
					if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
						upImageV.setVisibility(View.VISIBLE);
						downImageV.setVisibility(View.INVISIBLE);
					}
					// 判断滚动到顶部
					if (view.getFirstVisiblePosition() == 0) {
						downImageV.setVisibility(View.VISIBLE);
						upImageV.setVisibility(View.INVISIBLE);
					}
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if (firstVisibleItem + visibleItemCount == totalItemCount) {
					// 底部
					upImageV.setVisibility(View.VISIBLE);
					downImageV.setVisibility(View.INVISIBLE);
				} else {
					downImageV.setVisibility(View.VISIBLE);
					upImageV.setVisibility(View.INVISIBLE);
				}
			}
		});
		
		videoTpyelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				adapter.setSelectedPosition(position);
				adapter.notifyDataSetInvalidated();
				Log.i("setOnItemClickListener", "+++ postion" + position);
				videotype = position;
				if(position==3)
				{
					loadLastRefreshData();
					listadapter.notifyDataSetInvalidated();
				}else if(position==1)
				{
					videoBeans.clear();
					loadData();
					listadapter.notifyDataSetInvalidated();
				}
			}

		});
		
		
		videGridlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}
		});
		
		videGridlist.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if (firstVisibleItem + visibleItemCount == totalItemCount) {
					// 底部
					if(videotype==1 && totalItemCount < 100){
						loadData();
						listadapter.notifyDataSetInvalidated();
					}
				} 
			}
		});
	}

	private void constructVideotypelist(){
		
	}
	private void loadData() {
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv1.png", "新天龙八部钟汉良版", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "一代枭雄", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "烽火家人", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "花开半夏", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "结婚时代", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv2.png", "上海滩", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv3.png", "利剑猩红", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv4.png", "五号特工组", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv5.png", "新三国演义", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv6.png", "爱情公寓", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv7.png", "龙门镖局", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv8.png", "武林外传", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv9.png", "利剑纵横", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv10.png", "神探夏洛克", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv11.png", "韩国小姐", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv12.png", "我家的春夏秋冬", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv13.png", "咱们结婚吧", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv14.png", "恋爱的那点事儿", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "完美新娘", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "女人帮", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "闺中密友", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "最美的时光", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "剩女的代价", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "新燕子李三", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "武松", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "抹布女也有春天", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "两个爸爸", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "谁是真英雄", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "终极三国", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "放羊的星星", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "吸血鬼日志", "更新至2集",""));
	}

	private void loadLastRefreshData() {
		videoBeans.clear();
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv2.png", "一代枭雄", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "烽火家人", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "花开半夏", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "上海滩", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv6.png", "爱情公寓4", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv7.png", "龙门镖局", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv9.png", "利剑纵横", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv10.png", "神探夏洛克", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv11.png", "韩国小姐", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv12.png", "我家的春夏秋冬", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv13.png", "咱们结婚吧", "更新至2集",""));
		videoBeans.add(new VideoBeans("/mnt/sdcard/DGuard/video_tv14.png", "恋爱的那点事儿", "更新至2集",""));
		videoBeans.add(new VideoBeans("", "完美新娘", "更新至2集",""));
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private class MyVideoTypeAdapter extends BaseAdapter {
		private List<String> courseString;
		private Context context;
		private int selectedPosition = 1;// 选中的位置

		public MyVideoTypeAdapter(String[] courseStrlist, Context context) {
			super();
			this.courseString = Arrays.asList(courseStrlist);
			this.context = context;
		}

		@Override
		public int getCount() {
			return courseString.size();
		}

		@Override
		public Object getItem(int arg0) {
			return courseString.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater mInflater = (LayoutInflater) ((Activity) context)
					.getLayoutInflater();
			final int index = position;
			TextView tv = null;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.list_videotype_item,
						null);
				tv = (TextView) convertView.findViewById(R.id.ItemVideo);
				// courseBeans[position].textView = tv;
				convertView.setTag(tv);
			} else {
				tv = (TextView) convertView.getTag();
			}
			tv.setText(courseString.get(position));
			// convertView.setPadding(0, 15, 0, 15);
			// Log.i("getView", "+++ postion" + selectedPosition);
			if (selectedPosition == index) {
				tv.setSelected(true);
				tv.setPressed(true);
			} else {
				tv.setSelected(false);
				tv.setPressed(false);
			}

			return convertView;
		}
	}

	private class MyListAdapter extends BaseAdapter {
		private List<VideoBeans> videobeans;
		private Context context;

		public MyListAdapter(List<VideoBeans> videobeans, Context context) {
			super();
			this.videobeans = videobeans;
			this.context = context;
		}

		@Override
		public int getCount() {
			return videobeans.size();
		}

		@Override
		public Object getItem(int arg0) {
			return videobeans.get(arg0);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater mInflater = (LayoutInflater) ((Activity) context)
					.getLayoutInflater();
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.tv_grid_item,
						null);
				holder.imageView = (TextView) convertView
						.findViewById(R.id.videotvbg);
				holder.textView = (TextView) convertView
						.findViewById(R.id.videotvName);
				// courseBeans[position].textView = tv;
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.imageView.setText(videobeans.get(position).videoDetail);
			if("".equals(videobeans.get(position).resourceUrl)!=true)
				holder.imageView.setBackgroundDrawable(Drawable.createFromPath(videobeans.get(position).resourceUrl));
			else
				holder.imageView.setBackgroundResource(R.drawable.program_loading_bg);
			holder.textView
					.setText(videobeans.get(position).videoName);
			// convertView.setPadding(0, 15, 0, 15);
			// Log.i("getView", "+++ postion" + selectedPosition);
			convertView.setPadding(15, 5, 15, 5);
			return convertView;
		}

		public class ViewHolder {
			public TextView imageView;
			public TextView textView;
		}
	}
}
