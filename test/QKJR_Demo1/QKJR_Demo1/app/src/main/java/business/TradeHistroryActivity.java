package business;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

import Adapter.ListAdapter2;
import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/18.
 */
public class TradeHistroryActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_history);
        initViews();
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_tradehistrory:
                    onBackPressed();
                    break;
            }
        }
    };
    private ImageView backImg;
    private ImageView imgBg;
    private ListView listView;
    private TextView textview;
    private void initViews(){
        textview= (TextView) findViewById(R.id.text_v);
        listView= (ListView) findViewById(R.id.listview);
        backImg= (ImageView) findViewById(R.id.back_tradehistrory);
        backImg.setOnClickListener(listener);
        imgBg= (ImageView) findViewById(R.id.img_bg);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);





        initData();
    }


    private void initData(){
        LogUtils.logD("得到记录方法返回值之前");

        LogUtils.logD("得到记录方法返回值之后");

        ArrayList<HashMap<String,Object>> list= null;
        try {
            list = DataUtils.getListFromShared(TradeHistroryActivity.this, DataUtils.getCurrentUser(TradeHistroryActivity.this));

            if(list!=null&&list.size()>0){
                LogUtils.logD(list.toString());
                listView.setAdapter(new ListAdapter2(TradeHistroryActivity.this,list));
            }
            else{
                LogUtils.logD("list长度不够或者时null");

                imgBg.setVisibility(View.VISIBLE);
                textview.setVisibility(View.VISIBLE);

            }
        }

        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
