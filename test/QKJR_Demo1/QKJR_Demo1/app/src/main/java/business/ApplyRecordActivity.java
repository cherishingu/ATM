package business;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/19.
 */
public class ApplyRecordActivity  extends Activity{
    ListView repayListView;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyrecord);
        initViews();
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_apply:
                    onBackPressed();
                    break;
            }
        }
    };
    private ImageView backImg;
    private void initViews(){
        backImg= (ImageView) findViewById(R.id.back_apply);
        backImg.setOnClickListener(listener);
        repayListView=(ListView)findViewById(R.id.repay_record_listview);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);

        ArrayList<HashMap<String, Object>> list= new  ArrayList<HashMap<String, Object>>();
        ArrayList<HashMap<String, Object>> list1= new  ArrayList<HashMap<String, Object>>();
        JSONArray jsonArray= DataUtils.getJsonArrayFromShared(ApplyRecordActivity.this, DataUtils.getCurrentUser(ApplyRecordActivity.this));
        if(jsonArray==null){
            LogUtils.logD("array is null");
        }
        else {
            list1=DataUtils.JSONOArrayToArrayList(ApplyRecordActivity.this,jsonArray);

        }
        for(int i=0;i<list1.size();i++){
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put(DataUtils.loanDateTime, ((HashMap<String, Object>) list1.get(i)).get(DataUtils.loanDateTime));
            map.put(DataUtils.loanTimes, ((Map<String, Object>) list1.get(i)).get(DataUtils.loanTimes));
            map.put(DataUtils.loanAmount, ((Map<String, Object>) list1.get(i)).get(DataUtils.loanAmount));
            SimpleApplication  simpleApplication=(SimpleApplication)getApplication();
            String key=((Map<String, Object>) list1.get(i)).get(DataUtils.loanCountNum).toString();
            long isended=simpleApplication.getTime(key);
            if(isended==0){
                DataUtils.putSimpleJSONValueToShared(ApplyRecordActivity.this,DataUtils.getCurrentUser(ApplyRecordActivity.this),DataUtils.state,"0",i);
                map.put(DataUtils.state, "账户正常");

            }
            else {
                map.put(DataUtils.state,"审核中");
            }
            list.add(map);
        }
        adapter=new SimpleAdapter(ApplyRecordActivity.this,list,R.layout.repayrecord_listitem,new String[]{DataUtils.loanDateTime,DataUtils.loanTimes,DataUtils.loanAmount,DataUtils.state},new int[]{R.id.date0_tev, R.id.perid1_tev ,R.id.mount2_tev ,R.id.state3_tev});
        repayListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViews();
    }
}
