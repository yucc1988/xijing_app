package com.example.xijin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.prototypes.CardWithList;
import it.gmariotti.cardslib.library.prototypes.LinearListView;

public class StockCard extends CardWithList {

	private StockData[] initData;
	
	private String title;
	
    public StockCard(Context context,String title) {
        this(context,title,new StockData[0]);
    }
    
    public StockCard(Context context,String title,StockData... data){
    	super(context);
    	this.initData = data;
    	this.title = title;
    }
    
    public void addData(StockData... data){
    	for(StockData item : data){
    		StockObject s = new StockObject(this);
    		s.code = item.code;
    		s.value = item.value;
    		s.delta = item.delta;
    		s.deltaPerc = item.deltaPerc;
    		mLinearListAdapter.add(s);
    	}
    }

    @Override
    protected CardHeader initCardHeader() {

        //Add Header
        CardHeader header = new CardHeader(getContext(), R.layout.stock_card_header) {

            @Override
            public void setupInnerViewElements(ViewGroup parent, View view) {
                super.setupInnerViewElements(parent, view);
                TextView subTitle = (TextView) view.findViewById(R.id.carddemo_googlenow_main_inner_lastupdate);
                if (subTitle != null) {
                    subTitle.setText("最后更新: 23 分钟前");  //Should use strings.xml
                }
            }
        };
        header.setTitle(title);
        return header;
    }

    @Override
    protected void initCard() {

//        //Set the whole card as swipeable
//        setSwipeable(true);
//        setOnSwipeListener(new OnSwipeListener() {
//            @Override
//            public void onSwipe(Card card) {
//                Toast.makeText(getContext(), "Swipe on " + card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }


    @Override
    protected List<ListObject> initChildren() {

        //Init the list
        List<ListObject> list = new ArrayList<ListObject>();

        
        for(StockData item : initData){
        	StockObject s = new StockObject(this);
            s.code = item.code;
            s.value = item.value;
            s.delta = item.delta;
            s.deltaPerc = item.deltaPerc;
            list.add(s);
        }

        return list;
    }

    @Override
    public View setupChildView(int childPosition, ListObject object, View convertView, ViewGroup parent) {

        //Setup the ui elements inside the item
        TextView textViewCode = (TextView) convertView.findViewById(R.id.textViewCode);
        TextView textViewValue = (TextView) convertView.findViewById(R.id.textViewValue);
        TextView textViewDelta = (TextView) convertView.findViewById(R.id.textViewDelta);
        TextView textViewDeltaPerc = (TextView) convertView.findViewById(R.id.textViewPerc);

        //Retrieve the values from the object
        StockObject stockObject = (StockObject) object;
        textViewCode.setText(stockObject.code);
        textViewValue.setText(String.format("%.1f", stockObject.value));
        textViewDelta.setText(String.format("%.1f", stockObject.delta));
        textViewDeltaPerc.setText(String.format("%.1f", stockObject.deltaPerc) + "%");

        if (stockObject.delta<0){
            textViewDelta.setTextColor(getContext().getResources().getColor(R.color.stock_negative));
            textViewDeltaPerc.setTextColor(getContext().getResources().getColor(R.color.stock_negative));
        }else{
            textViewDelta.setTextColor(getContext().getResources().getColor(R.color.stock_positive));
            textViewDeltaPerc.setTextColor(getContext().getResources().getColor(R.color.stock_positive));
        }

        return convertView;
    }

    @Override
    public int getChildLayoutId() {
        return R.layout.stock_inner_main;
    }
    
    public static class StockData{
    	public final String code;
    	public final float value;
    	public final float delta;
    	public final float deltaPerc;
    	
    	public StockData(String code,float value,float delta,float deltaPerc){
    		this.code = code;
    		this.value = value;
    		this.delta = delta;
    		this.deltaPerc = deltaPerc;
    	}
    }

    private class StockObject extends DefaultListObject {

    	private String code;
    	private float value;
    	private float delta;
    	private float deltaPerc;

        public StockObject(Card parentCard) {
            super(parentCard);
            init();
        }

        private void init() {
            //OnClick Listener
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(LinearListView parent, View view, int position, ListObject object) {
                    Toast.makeText(getContext(), "Click on " + getObjectId(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public String getObjectId() {
            return code;
        }
    }

}
