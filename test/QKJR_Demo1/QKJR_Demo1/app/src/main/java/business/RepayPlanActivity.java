package business;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import java.util.ArrayList;
import java.util.HashMap;

import Adapter.ListAdapter;
import Utils.DataUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/19.
 */
public class RepayPlanActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repayplan1);
        initViews();
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back_repay_plan:
                    onBackPressed();
                    break;
            }
        }
    };

    private ListView listview;
    private ListAdapter adapter;
    private ImageView backImg;
    private void initViews(){
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
        backImg= (ImageView) findViewById(R.id.back_repay_plan);
        listview= (ListView) findViewById(R.id.listview);

        backImg.setOnClickListener(listener);
        Context con=RepayPlanActivity.this;
        ArrayList<HashMap<String,Object>> list=DataUtils.JSONOArrayToArrayList(con, DataUtils.getJsonArrayFromShared
                            (con, DataUtils.getCurrentUser(con)));
        if(list==null){
            listview.setVisibility(View.INVISIBLE);
        }
        else {
            adapter = new ListAdapter(con, list);
            listview.setAdapter(adapter);
        }
    }
}
