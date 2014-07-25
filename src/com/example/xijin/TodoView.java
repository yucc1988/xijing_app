package com.example.xijin;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;


public class TodoView extends Fragment{
	  public static final String SHHJJYS_URL="http://114.215.102.36/news_and_market/market/shhjjys/item_new.json";
	  private TextView view;
	  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = (TextView) inflater.inflate(R.layout.drawer_list_item, container, false);
		view.setText("TODO");
		
		new RequestTask().execute(); //4.0后不允许在主线程执行httpget
		return view;
	}
	  
	  private class RequestTask extends AsyncTask<Void, Void, String> {
			
			@Override
			protected String doInBackground(Void... params) {
		
				return RequestData(SHHJJYS_URL);
			}  
			/**
			 * onPostExecute方法主要是主线程中的数据更新。
			 */
			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);
				if (result != null) {
					JSONAnalysis(result);
				} else if (result == null) {
					view.setText("行情被吃了。。。");
				}
			}
		
		
		public String RequestData(String url) {
			HttpGet get = new HttpGet(url);
			HttpClient client = new DefaultHttpClient();
			StringBuilder builder = null;
			try {
				HttpResponse response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == 200) {
					InputStream inputStream = response.getEntity().getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(inputStream));
					builder = new StringBuilder();
					String s = null;
					for (s = reader.readLine(); s != null; s = reader.readLine()) {
						builder.append(s);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return builder.toString();
		}
		
		/**
		 * 对请求回来的数据进行JSON解析。
		 * @param result
		 */
		public void JSONAnalysis(String result) {
	
			JSONArray array = null;
			try {
				array = new JSONArray(result);
			} catch (JSONException e) {
				e.printStackTrace();
				view.setText("JSONArray......"+ e.toString());
			}
		
          for(int i=0;i<array.length();i++)
          {
        	  try
        	  {
        	  JSONObject oj = array.getJSONObject(i);
        	  //TODO 第一个oj作为标题（unicode）;其余为内容。
        	  String update_time = oj.getString("update_time");
        	  String variety = oj.getString("variety");
        	  String latest_price = oj.getString("latest_price");
        	  String total_volume = oj.getString("total_volume");
        	  String open_price = oj.getString("open_price");
        	  String yesterday_price = oj.getString("yesterday_price");
        	  String up_or_down_point = oj.getString("up_or_down_point");
        	  String lowest_price = oj.getString("lowest_price");
        	  String highest_price = oj.getString("highest_price");
        	  
        	  //TODO : 此处修改添加cardlib视图;求助拓拓了
        	  view.setText("{ update_time:"+update_time+",variety:"+variety + " }");
        	  }
        	  catch (JSONException e) {
  				e.printStackTrace();
  				view.setText("JSONObject......"+ e.toString());
  				return;
  			}
        	  
        	  
          }

			
		}
	  
	  }
  
	
	
}
