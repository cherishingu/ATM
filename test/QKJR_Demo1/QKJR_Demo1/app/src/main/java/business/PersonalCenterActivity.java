package business;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.zhuzhuxia.qkjr_demo1.R;

import java.util.ArrayList;

import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/13.
 */
public class PersonalCenterActivity extends Activity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);
        initAdapter();
        initViews();
    }

    private ArrayAdapter<String> adapter;
    private void initAdapter(){
        String [] strs={"个人信息","申请纪录","还款记录","历史交易","我的银行卡"};
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<strs.length;i++){
            list.add(strs[i]);
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
    }
    private void initViews(){
        listView= (ListView) findViewById(R.id.center_list);
        listView.setAdapter(adapter);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }


}
