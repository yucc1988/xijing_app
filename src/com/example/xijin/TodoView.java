package com.example.xijin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TodoView extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		TextView view = (TextView) inflater.inflate(R.layout.drawer_list_item, container, false);
		view.setText("TODO");
		return view;
	}
	
}
