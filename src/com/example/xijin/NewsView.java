package com.example.xijin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsView extends Fragment {
	 
    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabbed, container, false);
        sectionsPagerAdapter = new SectionsPagerAdapter(
                getChildFragmentManager());
         
        viewPager = (ViewPager) v.findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
         
        return v;
    }
     
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
 
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
 
        @Override
        public Fragment getItem(int position) {
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
 
        @Override
        public int getCount() {
            return 3;
        }
 
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
            case 0:
                return getString(R.string.tab_news);
            case 1:
                return getString(R.string.tab_finance);
            case 2:
                return getString(R.string.tab_sports);
            }
            return null;
        }
    }
}
