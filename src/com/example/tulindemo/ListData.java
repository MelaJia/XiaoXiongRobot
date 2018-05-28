package com.example.tulindemo;

public class ListData {
	//封装获取的数据
	private String content;
	public static final int SEND=1;
	public static final int RECEIVER=2;
	public int flag;
	private String time;
  
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getFlag() {
		return flag;
	}
	public ListData(String content,int flag,String time) {
		setContent(content);
		setFlag(flag);
		setTime(time);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	
	
	
	

}
