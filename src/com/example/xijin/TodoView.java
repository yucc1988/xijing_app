package com.example.xijin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xijin.util.*;

public class TodoView extends Fragment{
	  public static final String SHHJJYS_JSON="http://114.215.102.36/news_and_market/market/shhjjys/item_new.json";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView view = (TextView) inflater.inflate(R.layout.drawer_list_item, container, false);
		view.setText("TODO");
		test_get_json(SHHJJYS_JSON);
		return view;
	}
	private void test_get_json(String uri_string)
	{
		HttpJson httpJson = new HttpJson();
		httpJson.getJSONObjectByGet(uri_string);
	}
}
