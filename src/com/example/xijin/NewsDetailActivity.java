package com.example.xijin;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;

public class NewsDetailActivity extends ActionBarActivity{
	
	public static final String TITLE = "news_title";
	
	public static final String AUTHOR = "news_author";
	
	public static final String DATE = "news_date";
	
	public static final String DESCRIPTION = "news_content";
	
	public static final String LINK = "news_link";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_detail);
		ActionBar bar = getSupportActionBar();
		bar.setDisplayOptions(bar.getDisplayOptions() ^ ActionBar.DISPLAY_HOME_AS_UP,ActionBar.DISPLAY_HOME_AS_UP);
		
		Intent intent = getIntent();
		
		String title = intent.getStringExtra(TITLE);
		String author = intent.getStringExtra(AUTHOR);
		String date = intent.getStringExtra(DATE);
		String description = intent.getStringExtra(DESCRIPTION);
		String link = intent.getStringExtra(LINK);
		
		((TextView)findViewById(R.id.title)).setText(title);
		((TextView)findViewById(R.id.author)).setText(author);
		((TextView)findViewById(R.id.date)).setText(date);
		((TextView)findViewById(R.id.description)).setText(description);
		((TextView)findViewById(R.id.link)).setText(Html.fromHtml("<a href=\"" + link + "\">" + link + "</a>"));
		((TextView)findViewById(R.id.link)).setMovementMethod(LinkMovementMethod.getInstance());
	}

	@Override
	public boolean onSupportNavigateUp() {
		finish();
		return true;
	}
}
