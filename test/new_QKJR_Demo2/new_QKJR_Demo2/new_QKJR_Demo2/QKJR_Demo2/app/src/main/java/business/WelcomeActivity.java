package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import com.qkjr_demo2.MainActivity;
import com.qkjr_demo2.R;
import Utils.DataBaseUtils;
import Utils.UIUtils;

/**
 * 欢迎界面
 * Created by zhuzhuxia on 16/5/11.
 */
public class WelcomeActivity  extends Activity{
    private ImageView imageView;
    /*************************************************************/
    /**
     *
     */
    public static Random random =new Random(System.currentTimeMillis());
    /**********************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        initViews();
        new DataBaseUtils(WelcomeActivity.this);

    }


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
