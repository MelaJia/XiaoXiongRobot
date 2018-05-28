package com.example.tulindemo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpData extends AsyncTask<String, Void, String> {
	// 请求
	private HttpClient httpClient;
	// 请求方式
	private HttpGet httpGet;
	// 请求返回
	private HttpResponse httpRespone;
	// 创建http实体
	private HttpEntity httpEnity;
	private InputStream inputStream;
	// 转换成流文件处理
	private String url;
	
	//创建接口给其他页面使用
	private HttpGetDataListener dataListener;

	public HttpData(String url,HttpGetDataListener dataListener) {
		this.url = url;
		this.dataListener=dataListener;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			httpClient = new DefaultHttpClient();
			httpGet = new HttpGet(url);
			httpRespone = httpClient.execute(httpGet);
			httpEnity = httpRespone.getEntity();
			//获取具体内容
		    inputStream=httpEnity.getContent();
			BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer sb=new StringBuffer();
			String line=null;
			if ((line=br.readLine())!=null) {
				sb.append(line);
			
				
			}
			return sb.toString();
		//	inputStream.close();
			//br.close();
			

		} catch (Exception e) {

		}
		return null;
	}
	@Override
	protected void onPostExecute(String result) {

//返回数据
		dataListener.getUrl(result);
		super.onPostExecute(result);
	}

}
