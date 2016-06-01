package fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import business.Loan1Activity;
import business.LogActivity;
import business.Repay1Activity;
import business.SimpleApplication;

/**
 * Created by zhuzhuxia on 16/5/13.
 */
public class LoanFragment extends Fragment {

    private SeekBar bar;
//    private ImageView vernier;
    private TextView loanNumTv;
    private int verWidth=60;
    private  TextView repayMoneyTev;//***********************
    private static  String datePerid="";//*******************************************************************************
    private static    String repayMoney="";//*******************************************************************************
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_loan, null);
        initViews(view);
        return view;
    }
    int lastX = 0,lastY = 0;


    private Button time3Btn,time6Btn,time12Btn,applyBtn;
    private boolean time3sel,time6sel,time12sel,timesel;
    private void initViews(View view){

        LogUtils.logD(verWidth+" width");
        repayMoneyTev=(TextView)view.findViewById(R.id.repayonce_tev);//****************************
        bar= (SeekBar) view.findViewById(R.id.seekbar);
        bar.setOnSeekBarChangeListener(seekListener);
        loanNumTv= (TextView) view.findViewById(R.id.loan_num);
        applyBtn= (Button) view.findViewById(R.id.btn_apply_loan);
        applyBtn.setOnClickListener(listener);

        time3Btn= (Button) view.findViewById(R.id.three_time);
        time12Btn=(Button)view.findViewById(R.id.twelve_time);
        time6Btn=(Button)view.findViewById(R.id.six_time);

        time6Btn.setOnClickListener(listener);
        time12Btn.setOnClickListener(listener);
        time3Btn.setOnClickListener(listener);
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.three_time:
                    //选中
                    if(!time3sel) {
                        time3sel = true;
                        datePerid="3";//*******************************************************************************
                        /**整个if部分
                         * //*******************************************************************************
                         */
                        if(!datePerid.equals("")&&!loanNumTv.getText().toString().equals("")){
                            showRepayNum(datePerid,loanNumTv.getText().toString());
                            repayMoneyTev.setText(repayMoney);
                        }
                        time6sel=false;
                        time12sel=false;
                        time3Btn.setBackgroundColor(Color.parseColor("#cccccc"));
                        time6Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time12Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                    }
                    else {
                        time3sel = false;
                        time12Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time6Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time3Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                    }

                    break;
                case R.id.six_time:
                    if(!time6sel) {
                        time6sel = true;
                        datePerid="6";//*****************************************************************
                        /**整个if部分
                         * //*******************************************************************************
                         */
                        if(!datePerid.equals("")&&!loanNumTv.getText().toString().equals("")){
                            showRepayNum(datePerid,loanNumTv.getText().toString());
                            repayMoneyTev.setText(repayMoney);
                        }
                        time3sel=false;
                        time12sel=false;
                        time6Btn.setBackgroundColor(Color.parseColor("#cccccc"));
                        time3Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time12Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                    }
                    else {
                        time6sel = false;
                        time12Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time6Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time3Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));


                    }

                    break;
                case R.id.twelve_time:

                    if(!time12sel) {

                        time12sel = true;
                        datePerid="12";//************************************************************
                        /**整个if部分
                         * //*******************************************************************************
                         */
                        if(!datePerid.equals("")&&!loanNumTv.getText().toString().equals("")){
                            showRepayNum(datePerid,loanNumTv.getText().toString());
                            repayMoneyTev.setText(repayMoney);
                        }
                        time3sel=false;
                        time6sel=false;
                        time12Btn.setBackgroundColor(Color.parseColor("#cccccc"));
                        time6Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time3Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                    }
                    else {
                        time12sel = false;
                        time12Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time6Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                        time3Btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_shape_black));
                    }
                    break;
                case R.id.btn_apply_loan:
                    currentUser= DataUtils.getCurrentUser(getActivity());
                    if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0) {
                        jumpToLoginWithNoUser(Loan1Activity.class);
                    }
                    else {
                        /**
                         * 整个if部分*******************************************************************
                         */
                        if (time3sel || time6sel || time12sel) {
                            if (!datePerid.equals("") && !loanNumTv.getText().toString().equals("")) {
                                try {

                                    SimpleDateFormat dateFormater1 = new SimpleDateFormat("yyyy年MM月dd");//**************************
                                    long repayNum1 = System.currentTimeMillis();//**************************
                                    String repayNumStr1 = dateFormater1.format(repayNum1);//**************************

                                    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddHHmmss");//**************************
                                    long repayNum = System.currentTimeMillis();//**************************
                                    String repayNumStr = dateFormater.format(repayNum);//**************************
//                                ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
                                    HashMap<String, Object> map = new HashMap<String, Object>();
                                    map.put(DataUtils.loanAmount, loanNumTv.getText().toString());//总额
                                    map.put(DataUtils.loanTimes, datePerid);//期数
                                    map.put(DataUtils.repayOnce, repayMoney);//每期还款
                                    map.put(DataUtils.loanTimesExtra, datePerid);//剩余期数
                                    map.put(DataUtils.loanCountNum, repayNumStr);//单号
                                    map.put(DataUtils.loanDateTime, repayNumStr1);//申请日期
                                    map.put(DataUtils.state, "1");//审核状态
//                                map.put(DataUtils.loanTimeCount,)

                                    DataUtils.saveTempraroyDateToShared(getActivity(), map);
//                                list.add(map);
//                                DataUtils.putJsonArrayToShared(getActivity(), list, DataUtils.getCurrentUser(getActivity()));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            Intent intent = new Intent(getActivity(), Loan1Activity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getActivity(), "请先选择期数~~~", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
        }
    };

    private String currentUser;
    private int progress1;

    private SeekBar.OnSeekBarChangeListener seekListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            progress1= progress;
            if(progress<10){
                loanNumTv.setText("500");
                /**整个if部分
                 * //*******************************************************************************
                 */
                if(!datePerid.equals("")&&!loanNumTv.getText().toString().equals("")){
                    showRepayNum(datePerid,loanNumTv.getText().toString());
                    repayMoneyTev.setText(repayMoney);
                }
            }
            else if(progress>290){
                loanNumTv.setText("5000");
                /**整个if部分
                 * //*******************************************************************************
                 */
                if(!datePerid.equals("")&&!loanNumTv.getText().toString().equals("")){
                    showRepayNum(datePerid,loanNumTv.getText().toString());
                    repayMoneyTev.setText(repayMoney);
                }
            }
            else{
                loanNumTv.setText("1000");
                /**整个if部分
                 * //*******************************************************************************
                 */
                if(!datePerid.equals("")&&!loanNumTv.getText().toString().equals("")){
                    showRepayNum(datePerid,loanNumTv.getText().toString());
                    repayMoneyTev.setText(repayMoney);
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {


        }
    };



    /**
     * 得到每月还款金额函数*********************************************************************************************************************************
     * @param loanPerid
     * @param loanNum
     */
    private void   showRepayNum(String loanPerid,String loanNum ){

        switch (loanPerid)
        {
            case "3":
                switch (loanNum){
                    case "500":
                        repayMoney="200";
                        break;
                    case "1000":
                        repayMoney="400";
                        break;
                    case "5000":
                        repayMoney="2000";
                        break;
                }
                break;
            case "6":
                switch (loanNum){
                    case "500":
                        repayMoney="100";
                        break;
                    case "1000":
                        repayMoney="200";
                        break;
                    case "5000":
                        repayMoney="1000";
                        break;
                }
                break;
            case "12":
                switch (loanNum){
                    case "500":
                        repayMoney="50";
                        break;
                    case "1000":
                        repayMoney="100";
                        break;
                    case "5000":
                        repayMoney="500";
                        break;
                }
                break;

        }
    }

    private void jumpToLoginWithNoUser(Class activity){

        ActivitySetsUtils.logToActivity=activity;
        Intent intent = new Intent(getActivity(), LogActivity.class);
        startActivity(intent);
        Toast.makeText(getActivity(), "要先登录哦~", Toast.LENGTH_SHORT).show();
        //设置登录后跳转的页面.

    }
    @Override
    public void onResume() {
        super.onResume();
        if(time3sel){
            time3Btn.setBackgroundColor(Color.parseColor("#cccccc"));

        }
        else if(time12sel){
            time12Btn.setBackgroundColor(Color.parseColor("#cccccc"));
        }
        else if(time6sel){
            time6Btn.setBackgroundColor(Color.parseColor("#cccccc"));
        }


    }
}
