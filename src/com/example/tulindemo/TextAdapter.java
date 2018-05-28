package com.example.tulindemo;

import java.net.ContentHandler;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextAdapter extends BaseAdapter{
	private Context mContext;
	private List<ListData> lists;
	private RelativeLayout layout;
	

  //布局方式
	//构造方法
	public TextAdapter(List<ListData> lists, Context mContext) {
		this.mContext = mContext;
		this.lists = lists;
	
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	   LayoutInflater inflater=LayoutInflater.from(mContext);
	   if (lists.get(position).getFlag()==ListData.RECEIVER) {
		   layout=(RelativeLayout) inflater.inflate(R.layout.left, null);   
		
	}
	   if (lists.get(position).getFlag()==ListData.SEND) {
		   layout=(RelativeLayout) inflater.inflate(R.layout.right, null);   
		
	}
	   TextView tv=(TextView) layout.findViewById(R.id.tv);
	   tv.setText(lists.get(position).getContent());
	   
	   TextView time=(TextView) layout.findViewById(R.id.time);
	   time.setText(lists.get(position).getTime());
		return layout;
	}

}
