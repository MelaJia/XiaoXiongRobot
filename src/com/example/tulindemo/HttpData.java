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
	// ����
	private HttpClient httpClient;
	// ����ʽ
	private HttpGet httpGet;
	// ���󷵻�
	private HttpResponse httpRespone;
	// ����httpʵ��
	private HttpEntity httpEnity;
	private InputStream inputStream;
	// ת�������ļ�����
	private String url;
	
	//�����ӿڸ�����ҳ��ʹ��
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
			//��ȡ��������
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

//��������
		dataListener.getUrl(result);
		super.onPostExecute(result);
	}

}
