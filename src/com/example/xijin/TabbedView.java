package com.example.xijin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class TabbedView extends Fragment {
	 
    protected SectionsPagerAdapter sectionsPagerAdapter;
    protected ViewPager viewPager;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabbed, container, false);
        sectionsPagerAdapter = new SectionsPagerAdapter(
                getChildFragmentManager());
         
        viewPager = (ViewPager) v.findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
         
        return v;
    }
    
    protected abstract int getTabCount();
    
    protected abstract String getTabTitle(int position);
    
    protected abstract Fragment getTabView(int position);
     
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
 
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
 
        @Override
        public Fragment getItem(int position) {
        	return getTabView(position);
        }
 
        @Override
        public int getCount() {
            return getTabCount();
        }
 
        @Override
        public CharSequence getPageTitle(int position) {
           return getTabTitle(position);
        }
    }
}
