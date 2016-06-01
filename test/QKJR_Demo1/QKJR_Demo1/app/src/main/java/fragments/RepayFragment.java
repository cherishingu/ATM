package fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MyHistoryListAdapter;
import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import business.ApplyRecordActivity;
import business.Loan1Activity;
import business.LogActivity;
import business.MainActivity;
import business.Repay1Activity;
import business.SimpleApplication;

/**
 * Created by zhuzhuxia on 16/5/13.
 */
public class RepayFragment extends Fragment {
    private ListView repayHistoryList;//*************************************
    private MyHistoryListAdapter adapter;//****************************************
    private List list;//**********************************
    private TextView historyRecentRecordTev;//************************************
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_repay, null);
        initViews(view);
        return view;
    }

    private void initViews(View view){
//        repayBtn=(Button)view.findViewById(R.id.repay_now);
//        repayBtn.setOnClickListener(listener);
        historyRecentRecordTev=(TextView)view.findViewById(R.id.hitory_recent_record_tev);//*******************
        repayHistoryList=(ListView)view.findViewById(R.id.repayhistory_list);//***********************
        initData();//*************************************
        initDatas();//*******************************
    }

    /**
     * $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4
     */
    private void initData(){
        LogUtils.logD("得到记录方法返回值之前");
        JSONArray jsonArray=DataUtils.getRepayHistoryRecentRecord(getActivity(), DataUtils.getCurrentUser(getActivity()));
        LogUtils.logD("得到记录方法返回值之后");
        if (jsonArray==null) {
            historyRecentRecordTev.setText("无还款记录");
            return;
        }
        ArrayList<HashMap<String,Object>> list=DataUtils.JSONOArrayToArrayList(getActivity(), jsonArray);
        String danhao= (String) ((Map<String, Object>) list.get(list.size()- 1)).get(DataUtils.loanCountNum);
        LogUtils.logD("得道成的单号为：");
        if (danhao==null) {
            LogUtils.logD("无还款记录");
            historyRecentRecordTev.setText("无还款记录");
        }
        else
        {
            LogUtils.logD("还款成功的单号为"+danhao);
            historyRecentRecordTev.setText(danhao + "还款成功");
        }

    }

    /**
     * *********************************************************
     */
    private void initDatas(){
        ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();//***********************************
        ArrayList<HashMap<String,Object>> list1=new ArrayList<HashMap<String,Object>>();//***********************************
        JSONArray array=DataUtils.getJsonArrayFromShared(getActivity(), DataUtils.getCurrentUser(getActivity()));//************************
        if(array==null){
            LogUtils.logD("array is null");
        }
        else {
            LogUtils.logD(array.toString());
            list = DataUtils.JSONOArrayToArrayList(getActivity(), array);//****************************
            for(int i=0;i<list.size();i++){
                SimpleApplication simpleApplication=(SimpleApplication)getActivity().getApplication();
                long isended=simpleApplication.getTime(((Map<String, Object>) list.get(i)).get(DataUtils.loanCountNum).toString());
                if(isended==0){
                    DataUtils.putSimpleJSONValueToShared(getActivity(),DataUtils.getCurrentUser(getActivity()),DataUtils.state,"0",i);
                    list1.add(list.get(i));
                }
            }
            adapter = new MyHistoryListAdapter(getActivity(), list1, getActivity());//******************************************
            repayHistoryList.setAdapter(adapter);
        }
    }

    /**
     * ******************************************************
     */
    public void onResume() {
        super.onResume();
        initData();//************************
        initDatas();//************************
        LogUtils.logD("onResume的启动");
    }
//    private Button repayBtn;
//    private String currentUser;
//    private View.OnClickListener listener=new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            currentUser = DataUtils.getCurrentUser(getActivity());
//
//            Intent intent = new Intent(getActivity(), Repay1Activity.class);
//            startActivity(intent);
//
//        }
//    };
}
