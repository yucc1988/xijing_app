package com.example.xijin;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class NewsActivity extends ActionBarActivity implements ActionBar.TabListener{
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		for(int id : new int[]{R.string.tab_news,R.string.tab_finance,R.string.tab_sports}){
			actionBar.addTab(actionBar.newTab().setText(getResources().getString(id)).setTabListener(this));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
	    return true;
	}
	
	@Override
	  public void onRestoreInstanceState(Bundle savedInstanceState) {
	    // Restore the previously serialized current tab position.
	    if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
	      getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
	    }
	  }

	  @Override
	  public void onSaveInstanceState(Bundle outState) {
	    // Serialize the current tab position.
	    outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
	  }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_refresh:
			break;
		case R.id.action_settings:
			break;
		default:
			break;
		}
		
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Fragment fragment = new NewsListFragment();
		Bundle bundle = new Bundle();
		if(tab.getText().equals(getResources().getString(R.string.tab_news))){
			bundle.putString(NewsListFragment.RSS_URL, getResources().getString(R.string.url_news));
			fragment.setArguments(bundle);
		}else if(tab.getText().equals(getResources().getString(R.string.tab_finance))){
			bundle.putString(NewsListFragment.RSS_URL, getResources().getString(R.string.url_finance));
			fragment.setArguments(bundle);
		}else if(tab.getText().equals(getResources().getString(R.string.tab_sports))){
			bundle.putString(NewsListFragment.RSS_URL, getResources().getString(R.string.url_sports));
			fragment.setArguments(bundle);
		}
		getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
