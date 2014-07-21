package com.example.xijin;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class NewsView extends TabbedView {

	@Override
	protected int getTabCount() {
		return 3;
	}

	@Override
	protected String getTabTitle(int position) {
		switch (position) {
        case 0:
            return getString(R.string.tab_news);
        case 1:
            return getString(R.string.tab_finance);
        case 2:
            return getString(R.string.tab_sports);
        default:
        	return null;
        }
	}

	@Override
	protected Fragment getTabView(int position) {
		Fragment fragment = new NewsList();
		Bundle bundle = new Bundle();
		switch (position) {
        case 0:
            bundle.putString(NewsList.RSS_URL, getResources().getString(R.string.url_news));
			break;
        case 1:
        	bundle.putString(NewsList.RSS_URL, getResources().getString(R.string.url_finance));
            break;
        case 2:
        	bundle.putString(NewsList.RSS_URL, getResources().getString(R.string.url_sports));
            break;
        }
		fragment.setArguments(bundle);
        return fragment;
	}
}
