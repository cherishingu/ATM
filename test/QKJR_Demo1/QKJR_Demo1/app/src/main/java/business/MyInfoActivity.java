package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;

import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/18.
 */
public class MyInfoActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);
        initViews();
    }

    private Button quitBtn;
    private ImageView backImg;
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.quit_to_log:
                    currentUser=DataUtils.getCurrentUser(MyInfoActivity.this);
                    if(currentUser==null||currentUser.compareTo(DataUtils.noUser)==0||currentUser.length()==0){
                        finish();
                    }
                    else{
                        //已登录,退出账号
                        try {
                            //在数据中更新当前用户以及用户的状态.
                            DataUtils.updateState(MyInfoActivity.this,DataUtils.getCurrentUser(MyInfoActivity.this),false);
                            DataUtils.updateUser(MyInfoActivity.this,DataUtils.noUser);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent=new Intent(MyInfoActivity.this, LogActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    break;
                case R.id.back_myinfo:
                    onBackPressed();
                    break;
            }
        }
    };
    private void initViews(){
        quitBtn= (Button) findViewById(R.id.quit_to_log);
        backImg= (ImageView) findViewById(R.id.back_myinfo);
        quitBtn.setOnClickListener(listener);
        backImg.setOnClickListener(listener);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }
    private String currentUser;
}
