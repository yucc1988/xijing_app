package com.example.xijin;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.view.CardView;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import com.example.xijin.StockCard.StockData;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.AsyncTask;
import android.os.Bundle;

public class RTPriceView extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new RequestTask().execute(); // 4.0后不允许在主线程执行httpget
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.rt_price, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//Card
		StockCard card = new StockCard(getActivity(),"黄金价格");
		card.init();

		// Set card in the cardView
		CardView cardView = (CardView) getActivity().findViewById(R.id.rt_card);
        cardView.setCard(card);
	}

	private class RequestTask extends AsyncTask<Void, StockData, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				HttpGet get = new HttpGet(getString(R.string.shhjjys_url));
				HttpClient client = new DefaultHttpClient();
				HttpResponse response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == 200) {
					return EntityUtils.toString(response.getEntity());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
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
				//view.setText("行情被吃了。。。");
			}
		}
		
		@Override
		protected void onProgressUpdate(StockData... values) {
			CardView cardView = (CardView) getActivity().findViewById(R.id.rt_card);
	        ((StockCard)cardView.getCard()).addData(values);
		}

		/**
		 * 对请求回来的数据进行JSON解析。
		 * 
		 * @param result
		 */
		public void JSONAnalysis(String result) {

			try {
				JSONArray array = new JSONArray(result);
				List<StockData> data = new ArrayList<StockData>();
				for (int i = 1; i < array.length(); i++) {
					try {
						JSONObject oj = array.getJSONObject(i);
						// TODO 第一个oj作为标题（unicode）;其余为内容。
						//String update_time = oj.getString("update_time");
						String variety = oj.getString("variety");
						float latest_price = Float.valueOf(oj.getString("latest_price"));
						//String total_volume = oj.getString("total_volume");
						//String open_price = oj.getString("open_price");
						//String yesterday_price = oj.getString("yesterday_price");
						float up_or_down_point = Float.valueOf(oj.getString("up_or_down_point").replace("%", ""));
						//String lowest_price = oj.getString("lowest_price");
						//String highest_price = oj.getString("highest_price");
						if(latest_price <= 0)
							break;
						data.add(new StockData(variety, latest_price, up_or_down_point * latest_price, up_or_down_point));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				this.publishProgress(data.toArray(new StockData[0]));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}

}
