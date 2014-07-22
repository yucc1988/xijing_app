package com.example.xijin;

import android.support.v4.app.Fragment;

public class PekingOilView extends TabbedView{

		@Override
		protected int getTabCount() {
			return 2;
		}

		@Override
		protected String getTabTitle(int position) {
			switch (position) {
            case 0:
                return getString(R.string.tab_market_price);
            case 1:
                return getString(R.string.tab_candle_chart);
            default:
            	return null;
            }
		}

		@Override
		protected Fragment getTabView(int position) {
			switch (position) {
            case 0:
                return new MarketPriceView();
            case 1:
            	return new CandleChartView();
            default:
            	return null;
            }
		}
	
}
