package com.example.xijin;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.view.CardView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xijin.StockCard.StockData;

public class MarketPriceView extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_marketprice, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		initStockCard();
		initStockDetail1();
		initStockDetail2();
	}
	
	private void initStockCard(){
		//Card
		StockCard card = new StockCard(getActivity(),"北京石油",new StockData("ULP1",18.62f,-0.24f,-1.27f),new StockData("ULP2",6.47f,15.19f,0.09f));
		card.init();

		// Set card in the cardView
		CardView cardView = (CardView) getActivity().findViewById(R.id.stock_card);
        cardView.setCard(card);
	}
	
	private void initStockDetail1(){
		 //Create a Card
        Card card = new Card(getActivity(),R.layout.stock_detail);

        //Create a CardHeader
        CardHeader header = new CardHeader(getActivity());

        //Set the header title
        header.setTitle("ULP1");
        
        //Add a popup menu. This method set OverFlow button to visible
        header.setPopupMenu(R.menu.popupmain, new CardHeaderPopupMenuListener());

        card.addCardHeader(header);

        //Set the card inner text
        card.setTitle("detail");

        //Set card in the cardView
        CardView cardView = (CardView) getActivity().findViewById(R.id.stock_detail1);
        cardView.setCard(card);
	}
	
	private void initStockDetail2(){
		//Create a Card
        Card card = new Card(getActivity(),R.layout.stock_detail);

        //Create a CardHeader
        CardHeader header = new CardHeader(getActivity());

        //Set the header title
        header.setTitle("ULP2");
        
        //Add a popup menu. This method set OverFlow button to visible
        header.setPopupMenu(R.menu.popupmain, new CardHeaderPopupMenuListener());

        card.addCardHeader(header);

        //Set the card inner text
        card.setTitle("detail");

        //Set card in the cardView
        CardView cardView = (CardView) getActivity().findViewById(R.id.stock_detail2);
        cardView.setCard(card);
	}
	
	private class CardHeaderPopupMenuListener implements CardHeader.OnClickCardHeaderPopupMenuListener{

		@Override
		public void onMenuItemClick(BaseCard card, MenuItem item) {
			FragmentManager fm = getFragmentManager();
	        FragmentTransaction ft = fm.beginTransaction();
	        Fragment prev = fm.findFragmentByTag("dialog_about");
	        if (prev != null) {
	            ft.remove(prev);
	        }
	        ft.addToBackStack(null);

	        new TradeDialog().show(ft,"dialog_about");
		}
		
	}
	
	public static class TradeDialog extends DialogFragment{
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            View rootView = layoutInflater.inflate(R.layout.dialog_trade, null);
            TextView titleView = (TextView) rootView.findViewById(
                    R.id.title);
            titleView.setText(getString(R.string.dialog_trade_title));
            return new AlertDialog.Builder(getActivity())
            .setView(rootView)
            .setPositiveButton(R.string.dialog_trade_ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    }
            )
            .setNegativeButton(R.string.dialog_trade_cancel,
            		new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.dismiss();
						}
					})
            .create();
		}
		
	}
}
