package business;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;

import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.SmsContent;
import Utils.SmsUtils;
import Utils.UIUtils;
import Widgets.TimeCountView;

public class AuthenticationActivity extends AppCompatActivity {
    CheckBox checkBox;
    Button commitBtn;
    EditText smscodeEdit;
    private String str1="已给您的银行预留手机号",str2="发送了短信验证码，请检验后编写";
    boolean checked=true;
    private TimeCountView countView;
    private ImageView imgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        commitBtn=(Button)findViewById(R.id.authentication_commit_btn);
        smscodeEdit=(EditText)findViewById(R.id.smscode_edit);
        imgBtn=(ImageView)findViewById(R.id.back_ver1);
        imgBtn.setOnClickListener(listener);
        countView= (TimeCountView) findViewById(R.id.send_sms_btn);
        countView.setOnClickListener(listener);

        commitBtn.setOnClickListener(new MynextBtnListner());
        checkBox.setOnCheckedChangeListener(new MyCheckListener());

//        SmsContent content=new SmsContent(AuthenticationActivity.this,new Handler(),smscodeEdit);
//        AuthenticationActivity.this.getContentResolver().registerContentObserver
//                (Uri.parse("content://sms/"), true, content);
        topView= (TextView) findViewById(R.id.top_tev);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);

    }




    private String vertify;
    private TextView topView;
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch(v.getId()){

                case R.id.send_sms_btn:

                    topView.setText(str1+DataUtils.getCurrentUser(AuthenticationActivity.this)+str2);
                    topView.setVisibility(View.VISIBLE);
                    vertify=DataUtils.generate6Random();
                    countView.start();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SmsUtils.request(DataUtils.getCurrentUser(AuthenticationActivity.this), "请勿告诉他人，验证码：" +
                                    vertify + "。【乾康金融】");
                        }
                    }).start();
                    break;
                case R.id.back_ver1:
                    onBackPressed();
                    break;

            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();


    }

    private class  MyCheckListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            checked=isChecked;
        }
    }
    private class MynextBtnListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(smscodeEdit.getText().toString().equals("")||smscodeEdit.getText().toString()==null) {
                Toast.makeText(AuthenticationActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            }
            else {


                if (vertify!=null&&vertify.compareTo(smscodeEdit.getText().toString().trim()) == 0) {
                    topView.setVisibility(View.INVISIBLE);
                    try {
                        if (DataUtils.getInfoDone(AuthenticationActivity.this, DataUtils.info1HasDone) == true) {
                            ActivitySetsUtils.showInfoFrom = "Loan";
                            finish();
                            Intent it = new Intent(AuthenticationActivity.this, ShowInfoActivity.class);
                            startActivity(it);
                        } else {
                            ActivitySetsUtils.showInfoFrom = "Loan";
                            finish();
                            Intent it = new Intent(AuthenticationActivity.this, AddInfo1Activity.class);
                            startActivity(it);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(AuthenticationActivity.this, "验证码错误.....", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
