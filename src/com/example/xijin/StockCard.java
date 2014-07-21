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

    public StockCard(Context context) {
        super(context);
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
        header.setTitle("北京石油"); //should use strings.xml
        return header;
    }

    @Override
    protected void initCard() {

        //Set the whole card as swipeable
        setSwipeable(true);
        setOnSwipeListener(new OnSwipeListener() {
            @Override
            public void onSwipe(Card card) {
                Toast.makeText(getContext(), "Swipe on " + card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected List<ListObject> initChildren() {

        //Init the list
        List<ListObject> mObjects = new ArrayList<ListObject>();

        //Add an object to the list
        StockObject s1 = new StockObject(this);
        s1.code = "ULP1";
        s1.value = 18.62f;
        s1.delta = -0.24f;
        s1.deltaPerc = -1.27f;
        mObjects.add(s1);

        StockObject s2 = new StockObject(this);
        s2.code = "ULP2";
        s2.value = 6.47f;
        s2.delta = 15.19f;
        s2.deltaPerc = 0.09f;
        mObjects.add(s2);

//        StockObject s3 = new StockObject(this);
//        s3.code = "Down Jones";
//        s3.value = 16738f;
//        s3.delta = 15.19f;
//        s3.deltaPerc = 0.09f;
//        mObjects.add(s3);
//
//        StockObject s4 = new StockObject(this);
//        s4.code = "GOOG";
//        s4.value = 544.66f;
//        s4.delta = 0.28f;
//        s4.deltaPerc = 0.05f;
//        mObjects.add(s4);

        return mObjects;
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
        textViewValue.setText(""+stockObject.value);
        textViewDelta.setText(""+stockObject.delta);
        textViewDeltaPerc.setText(""+stockObject.deltaPerc+"%");

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


    // -------------------------------------------------------------
    // Weather Object
    // -------------------------------------------------------------

    public class StockObject extends DefaultListObject {

        public String code;
        public float value;
        public float delta;
        public float deltaPerc;

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
