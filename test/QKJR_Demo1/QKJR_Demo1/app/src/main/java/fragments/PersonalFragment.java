package fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;

import java.util.ArrayList;

import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import Utils.SharePreferenceUtils;
import Widgets.SimpleAdapterForQk;
import business.AddInfo1Activity;
import business.ApplyRecordActivity;
import business.Loan1Activity;
import business.LogActivity;
import business.MainActivity;
import business.MyCardsActivity;
import business.MyInfoActivity;
import business.RepayPlanActivity;
import business.ShowInfoActivity;
import business.TradeHistroryActivity;
import business.VertifyIdentifyActivity;

/**
 * Created by zhuzhuxia on 16/5/13.
 */
public class PersonalFragment extends Fragment {

    private ListView listView;
    private ImageView loginBtn;
    private LinearLayout mycardsRel,infoLinear,applyRecordLinear,repayPlanLinear,historyTradeLinear;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_personal_center, null);
        initViews(view);
        return view;
    }


    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.login_btn:

                    String str=currentUser;

                    if(str==null||str.compareTo(DataUtils.noUser)==0||currentUser.length()==0){

                        jumpToLoginWithNoUser(MainActivity.class);
                    }
                    else{
                        LogUtils.logD("设置信息");
                        jumpToActivity(MyInfoActivity.class);

                    }


                    break;
                case R.id.tv_mycards:
                    if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0) {
                        jumpToLoginWithNoUser(MyCardsActivity.class);
                    }
                    else{
                        jumpToActivity(MyCardsActivity.class);
                    }
                    break;
                case R.id.idvertify_linear:
                    if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0) {
                      jumpToLoginWithNoUser(AddInfo1Activity.class);
                    }
                    else{
                        boolean state = false;
                        try {
                            state=DataUtils.getInfoDone(getActivity(),DataUtils.info3HasDone);
                            LogUtils.logD("state:"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(!state) {
                            jumpToActivity(AddInfo1Activity.class);
                        }
                        else{
                            jumpToActivity(ShowInfoActivity.class);
                        }
                    }
                    break;

                case R.id.repayplan_linear:
                    if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0) {
                       jumpToLoginWithNoUser(RepayPlanActivity.class);
                    }
                    else{
//                        jumpToActivity();
                        jumpToActivity(RepayPlanActivity.class);
                    }
                    break;

                case R.id.history_linear:
                    if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0) {
                        jumpToLoginWithNoUser(TradeHistroryActivity.class);
                    }
                    else{
                        jumpToActivity(TradeHistroryActivity.class);
                    }
                    break;
                case R.id.record_linear:
                    if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0) {
                       jumpToLoginWithNoUser(ApplyRecordActivity.class);
                    }
                    else{
                        jumpToActivity(ApplyRecordActivity.class);
                    }
                    break;

            }
        }
    };
    private void jumpToActivity(Class activity){
        Intent intent=new Intent(getActivity(),activity);
        startActivity(intent);

    }

    private void jumpToLoginWithNoUser(Class activity){
        Intent intent = new Intent(getActivity(), LogActivity.class);
        startActivity(intent);
        ActivitySetsUtils.logToActivity= activity;
        Toast.makeText(getActivity(), "要先登录哦~", Toast.LENGTH_SHORT).show();
    }
    private String currentUser;

    private void initViews(View view){
        loginBtn= (ImageView) view.findViewById(R.id.login_btn);
        mycardsRel= (LinearLayout) view.findViewById(R.id.tv_mycards);
        infoLinear= (LinearLayout) view.findViewById(R.id.idvertify_linear);
        applyRecordLinear= (LinearLayout) view.findViewById(R.id.record_linear);
        historyTradeLinear= (LinearLayout) view.findViewById(R.id.history_linear);
        repayPlanLinear= (LinearLayout) view.findViewById(R.id.repayplan_linear);

        loginBtn.setOnClickListener(listener);
        mycardsRel.setOnClickListener(listener);
        infoLinear.setOnClickListener(listener);
        historyTradeLinear.setOnClickListener(listener);
        repayPlanLinear.setOnClickListener(listener);
        applyRecordLinear.setOnClickListener(listener);

        currentUser=DataUtils.getCurrentUser(getActivity());
        LogUtils.logD(currentUser);
        //未登录
        initImg();
    }
    private void initImg(){
        if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0){
            LogUtils.logD("未登录");
            loginBtn.setImageDrawable(getResources().getDrawable(R.mipmap.img_personcenter));
        }
        else{
            LogUtils.logD("登录");
            loginBtn.setImageDrawable(getResources().getDrawable(R.mipmap.img_face));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        currentUser=DataUtils.getCurrentUser(getActivity());
        initImg();
    }
}
