package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;

import java.util.Timer;
import java.util.TimerTask;

import Utils.DataBaseUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/11.
 */
public class WelcomeActivity  extends Activity{
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        initViews();
        new DataBaseUtils(WelcomeActivity.this);
        try {
            DataUtils.insert(WelcomeActivity.this,DataUtils.getCurrentUser(WelcomeActivity.this),DataUtils.info3HasDone,true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser= DataUtils.getCurrentUser(WelcomeActivity.this);
        if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0) {
//            ActivityManagerApplication.addDestoryActivity(MainActivity.this, ActivitySetsUtils.mainkey);
        }
        //已登录
        else {
            LogUtils.logD("已经登录.....跳转到main");
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }


    private String currentUser;
    private Animation animation;

    LinearLayout llStatus;
    private void initViews(){
        llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
        deleteNavigationBar();
        imageView= (ImageView) findViewById(R.id.welcome_img);
        initTimer();
    }

    private void deleteNavigationBar(){
        View decorView = getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        int uiOptions2 = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions2);
    }



    private android.os.Handler handler=new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 200:
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };
    private Timer timer;
    private TimerTask task;
    private void initTimer(){
        timer=new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(200);
            }
        };
        timer.schedule(task,1000,5000000);
    }
}
