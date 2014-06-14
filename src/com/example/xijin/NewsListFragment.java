package com.example.xijin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedInput;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsListFragment extends ListFragment{

	private static final String TAG = NewsListFragment.class.getName();
	
	public static final String RSS_URL = "rss_url";
	
	private static Map<String,SyndFeed> feeds = new HashMap<String, SyndFeed>();
	
	private ArrayAdapter<String> adapter;
	
	private String rssUrl;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		rssUrl = getArguments().getString(RSS_URL);
		adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new LinkedList<String>());
		
		setListAdapter(adapter);
		
		SyndFeed feed = feeds.get(rssUrl);
		if(feed == null){
			new LoadTask().execute();
		}else{
			for(Object entry : feed.getEntries()){
				String title = ((SyndEntry)entry).getTitle().trim();
				adapter.add(title);
			}
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list_fragment, container, false);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		try {
			SyndFeed feed = feeds.get(rssUrl);
			SyndEntry entry = (SyndEntry) feed.getEntries().get(position);
			Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
			intent.putExtra(NewsDetailActivity.TITLE, entry.getTitle().trim());
			intent.putExtra(NewsDetailActivity.AUTHOR, entry.getAuthor().trim());
			intent.putExtra(NewsDetailActivity.DATE, new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE).format(entry.getPublishedDate()));
			intent.putExtra(NewsDetailActivity.DESCRIPTION, entry.getDescription().getValue().trim());
			intent.putExtra(NewsDetailActivity.LINK, entry.getLink());
			startActivity(intent);
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
		
	}
	
	private class LoadTask extends AsyncTask<Void, SyndFeed, Void>{
		
		@Override
		protected Void doInBackground(Void... params) {
			InputStreamReader reader = null;
			try {
				URL url = new URL(rssUrl);
				reader = new InputStreamReader(url.openStream());
				SyndFeed feed = new SyndFeedInput().build(reader);
				feeds.put(rssUrl, feed);
				publishProgress(feed);
			} catch (Exception e) {
				Log.e(TAG, e.toString());
			}finally{
				try {
					reader.close();
				} catch (IOException e) {
				}
				
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(SyndFeed... values) {
			for(SyndFeed feed : values){
				for(Object entry : feed.getEntries()){
					String title = ((SyndEntry)entry).getTitle().trim();
					adapter.add(title);
				}
			}
		}
		
	}
}
