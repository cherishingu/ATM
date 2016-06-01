package business;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;

/**
 * Created by 姚津 on 16/5/16.
 */
public class Repay3Activity extends Activity {
    TextView repayTev;//***********************
    TextView repayTimeTev;//***********************
    TextView repayWayTev;//***********************
    TextView repayNumTev;//***********************
    Bundle bundle;//***********************
    private ImageView backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay3);
        load();//************************
    }
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back_repay3:
                    onBackPressed();
                    break;
            }
        }
    };
    private  void  load()
    {
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
        backImg= (ImageView) findViewById(R.id.back_repay3);
        backImg.setOnClickListener(listener);
        LogUtils.logD("********88???????????????????????????????????????????????????????");
        bundle=getIntent().getBundleExtra("data");//*****************************
        repayTev=(TextView)findViewById(R.id.repay_tev);//***********************
        repayTimeTev=(TextView)findViewById(R.id.repay_time_tev);//*********************
        repayNumTev=(TextView)findViewById(R.id.repay_num_tev);//***********************
        repayWayTev=(TextView)findViewById(R.id.repay_way_tev);//***********************

        LogUtils.logD(bundle.getString(DataUtils.repayOnce));//****************************
        repayTev.setText(bundle.getString(DataUtils.repayOnce)+"元");//**************************

        SimpleDateFormat dateFormater1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//**************************
        long date=System.currentTimeMillis();//**************************
        String dateStr=dateFormater1.format(date);//**************************
        repayTimeTev.setText(dateStr);//**************************
        repayWayTev.setText(bundle.getString("payway"));//**************************
        SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyyMMddHHmmss");//**************************
        long repayNum=System.currentTimeMillis();//**************************
        String repayNumStr=dateFormater2.format(repayNum);//**************************
        repayNumTev.setText(repayNumStr);//**************************
        repayWayTev.setText(bundle.getString("payway"));//********************************
        ;
        int loanExtraValue=(int)Integer.parseInt( bundle.getString(DataUtils.loanTimesExtra).toString())-1;//************************************
        LogUtils.logD(loanExtraValue + "");//*****************************************
        DataUtils.putSimpleJSONValueToShared(this, DataUtils.getCurrentUser(this), DataUtils.loanTimesExtra, loanExtraValue+"",bundle.getInt("position"));//**************************
        if(loanExtraValue==0){
            JSONArray jsonArray=DataUtils.getJsonArrayFromShared(Repay3Activity.this, DataUtils.getCurrentUser(Repay3Activity.this));
            if (jsonArray==null){
                return ;
            }
//            ArrayList<HashMap<String,Object>> list=DataUtils.JSONOArrayToArrayList(Repay3Activity.this,jsonArray);
            JSONObject jsonObject=DataUtils.getJSONObject(Repay3Activity.this, DataUtils.getCurrentUser(Repay3Activity.this), bundle.getInt("position"));
            HashMap<String,Object> map=DataUtils.JSONObjToHashMap(Repay3Activity.this, jsonObject);
            ArrayList<HashMap<String,Object>> list1=new ArrayList<HashMap<String,Object>>();
            list1.add(map);
            DataUtils.putRepayHistoryRecentRecord(Repay3Activity.this,list1,DataUtils.getCurrentUser(Repay3Activity.this));
            DataUtils.removeJSONObjectFromJSONArray(this, DataUtils.getCurrentUser(this), bundle.getInt("position"));

        }
    }


}
