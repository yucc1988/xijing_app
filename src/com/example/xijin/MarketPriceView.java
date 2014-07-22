package com.example.xijin;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.base.BaseCard;
import it.gmariotti.cardslib.library.view.CardView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
		StockCard card = new StockCard(getActivity());
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
        header.setPopupMenu(R.menu.popupmain, new CardHeader.OnClickCardHeaderPopupMenuListener() {
            @Override
            public void onMenuItemClick(BaseCard card, MenuItem item) {
                Toast.makeText(getActivity(), "Click on " + item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

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
        header.setPopupMenu(R.menu.popupmain, new CardHeader.OnClickCardHeaderPopupMenuListener() {
            @Override
            public void onMenuItemClick(BaseCard card, MenuItem item) {
                Toast.makeText(getActivity(), "Click on " + item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        card.addCardHeader(header);

        //Set the card inner text
        card.setTitle("detail");

        //Set card in the cardView
        CardView cardView = (CardView) getActivity().findViewById(R.id.stock_detail2);
        cardView.setCard(card);
	}
}
