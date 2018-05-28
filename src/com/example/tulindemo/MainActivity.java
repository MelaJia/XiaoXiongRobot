package com.example.tulindemo;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements HttpGetDataListener,OnClickListener {
	private HttpData httpData;// 异步请求对象
	private EditText getText;
	private Button send_bt;
	private List<ListData> lists;
	private ListView lv;
	//用户输入的内容
	private String content_str;
	private TextAdapter adapter;
	private String [] welcome_array;
	private double currentTime,oldTime=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}
	private void initView(){
		getText=(EditText) findViewById(R.id.sendText);
		send_bt=(Button) findViewById(R.id.send_btn);
		lv=(ListView) findViewById(R.id.lv);
		lists=new ArrayList<ListData>();
		send_bt.setOnClickListener(this);
		
		adapter=new TextAdapter(lists, this);
		lv.setAdapter(adapter);
		ListData listData;
		listData=new ListData(getRandomWelcomeTips(),ListData.RECEIVER,getTime());
		lists.add(listData);
		
	}
	private String getRandomWelcomeTips() {
		String welcome_tipsString=null;
		welcome_array=this.getResources().getStringArray(R.array.welcome_tips);
		int index=(int) (Math.random()*(welcome_array.length-1));
		welcome_tipsString=welcome_array[index];
		return welcome_tipsString;
		
	}
	private String getTime(){
		currentTime=System.currentTimeMillis();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date curDate=new Date();
		String str=simpleDateFormat.format(curDate);
		//判断3分钟内是否有发送，没有发送就有时间
		if(currentTime-oldTime>=3*60*1000){
			oldTime=currentTime;
			return str;
		}else{
			return "";
			
		}
	}
	

	

	@Override
	public void getUrl(String data) {
		parseText(data);
	//	jsonObject.put("code", value)
//		
//		Log.i("tt", httpData.toString());
//		ok.setText(httpData.toString());

	}
	//系统返回数据
	public void parseText(String str) {
		//Json解析
		try {
			JSONObject jsonObject=new JSONObject(str);
		//JSONArray jArray=new JSONArray();
//			Log.i("code",jsonObject.getString("code"));
//			Log.i("text",jsonObject.getString("text"));
			ListData listData;
			listData=new ListData(jsonObject.getString("text"),ListData.RECEIVER,getTime());
			lists.add(listData);
			adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onClick(View v) {
		getTime();
		content_str=getText.getText().toString();
		// TODO Auto-generated method stub
		getText.setText("");
		//去掉空格
		String dropk=content_str.replace(" ", "");
		//去掉回车
		String drophString=dropk.replace("\n", "");
		ListData listData;
		listData=new ListData(content_str,ListData.SEND,getTime());
		lists.add(listData);
		//超过20条记录，就移除
//		if(lists.size()>20){
//			for (int i = 0; i < lists.size(); i++) {
//				lists.remove(i);
//				
//			}
//			
//		}
		adapter.notifyDataSetChanged();
		httpData = (HttpData) new HttpData(
				"http://www.tuling123.com/openapi/api?key=20649ef9df1046f58110cb0266452cd9&info="+drophString,
				this).execute();
	
		
	}

}

