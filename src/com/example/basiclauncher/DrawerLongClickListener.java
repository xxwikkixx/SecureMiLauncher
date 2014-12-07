package com.example.basiclauncher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class DrawerLongClickListener implements OnItemLongClickListener {
	
	Context mContext;
	SlidingDrawer drawerForAdapter;
	RelativeLayout homeViewForAdapter;
	Pac[] pacsForListener;
	
	public DrawerLongClickListener(Context ctxt, SlidingDrawer slidingDrawer, RelativeLayout homeView,Pac[] pacs ){
		mContext = ctxt;
		drawerForAdapter = slidingDrawer;
		homeViewForAdapter =homeView;
		pacsForListener = pacs;
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View item, int pos,
			long arg3) {
		MainActivity.appLaunchable=false;
		LayoutParams lp = new LayoutParams(item.getWidth(),item.getHeight());
		lp.leftMargin = (int) item.getX();
		lp.topMargin = (int) item.getY();
		
		LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout ll = (LinearLayout) li.inflate(R.layout.drawer_item, null);
		
		((ImageView)ll.findViewById(R.id.icon_image)).setImageDrawable(((ImageView)item.findViewById(R.id.icon_image)).getDrawable());
		((TextView)ll.findViewById(R.id.icon_text)).setText(((TextView)item.findViewById(R.id.icon_text)).getText());
		
		ll.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				v.setOnTouchListener(new AppTouchListener());
				return false;
			}
		});
		
		
		ll.setOnClickListener(new AppClickListener(pacsForListener,mContext));
		String [] data = new String[2];
		data[0]=pacsForListener[pos].packageName;
		data[1]=pacsForListener[pos].name;
		
		ll.setTag(data);
		
		homeViewForAdapter.addView(ll, lp);
		drawerForAdapter.animateClose();
		drawerForAdapter.bringToFront();
		return false;
	}

}
