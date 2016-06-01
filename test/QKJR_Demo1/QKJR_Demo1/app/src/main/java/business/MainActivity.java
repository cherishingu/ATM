package business;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import Utils.ActivityManagerApplication;
import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;
import Widgets.ImageViewWithText;
import fragments.LoanFragment;
import fragments.PersonalFragment;
import fragments.RepayFragment;

/**
 * Created by zhuzhuxia on 16/5/11.
 */
public class MainActivity extends Activity {

    private ImageViewWithText toPersonalBtn,toLoanBtn,toRepayBtn;
    private Fragment personalFragment,loanFragment,repayFragment;
    private FragmentManager fragmentManager;

    private FragmentTransaction transaction;
//    private static Context main=MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initViews();
        initListener();


    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.loan_btn:
                    changeToLoan();
                    break;
                case R.id.repay_btn:
                    currentUser= DataUtils.getCurrentUser(MainActivity.this);
                    //未登录
                    if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0) {
                        jumpToLoginWithNoUser();
                        ActivityManagerApplication.addDestoryActivity(MainActivity.this, ActivitySetsUtils.mainkey);
                    }
                    //已登录
                    else {
                        changeToRepay();
                    }
                    break;
                case R.id.personal_center_btn:
                    changeToPersonal();
                    break;
            }

        }
    };
    private void jumpToLoginWithNoUser(){

        ActivitySetsUtils.logToActivity= MainActivity.class;

        //修改fragment的当前值
        ActivitySetsUtils.fragmentValue=1;


        Intent intent = new Intent(MainActivity.this, LogActivity.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "要先登录哦~", Toast.LENGTH_SHORT).show();
//        finish();
        //设置登录后跳转的页面.
    }
    private String currentUser;
    private void initListener(){
        toRepayBtn.setOnClickListener(listener);
        toLoanBtn.setOnClickListener(listener);
        toPersonalBtn.setOnClickListener(listener);
    }

    private void initViews(){
        toPersonalBtn= (ImageViewWithText) findViewById(R.id.personal_center_btn);
        toLoanBtn=(ImageViewWithText)findViewById(R.id.loan_btn);
        toRepayBtn=(ImageViewWithText)findViewById(R.id.repay_btn);

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);

        if(ActivitySetsUtils.fragmentValue==0){
            changeToLoan();
        }
        else{
            changeToRepay();
            ActivitySetsUtils.fragmentValue=0;
        }

    }

    private void initFragments() {
        fragmentManager=getFragmentManager();
        personalFragment=new PersonalFragment();
        repayFragment=new RepayFragment();
        loanFragment=new LoanFragment();
    }
    private void changeToPersonal() {
        transaction=null;
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.content, personalFragment);
        toPersonalBtn.setImg(R.mipmap.img_personal_sel);
        toLoanBtn.setImg(R.mipmap.img_loan_unsel);
        toRepayBtn.setImg(R.mipmap.img_repay_unsel);
        transaction.commit();
    }
    private void changeToLoan() {
        transaction=null;
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.content, loanFragment);
        toPersonalBtn.setImg(R.mipmap.img_personal_unsel);
        toLoanBtn.setImg(R.mipmap.img_loan_sel);
        toRepayBtn.setImg(R.mipmap.img_repay_unsel);
        transaction.commit();
    }
    private void changeToRepay() {
        transaction=null;
        transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.content, repayFragment);
        toPersonalBtn.setImg(R.mipmap.img_personal_unsel);
        toLoanBtn.setImg(R.mipmap.img_loan_unsel);
        toRepayBtn.setImg(R.mipmap.img_repay_sel);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.logD(ActivityManagerApplication.getTopActivity(MainActivity.this));
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }
}
