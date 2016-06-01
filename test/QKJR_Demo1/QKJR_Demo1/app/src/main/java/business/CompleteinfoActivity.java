package business;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utils.DataUtils;
import Utils.UIUtils;
import Widgets.CustomDialog;

public class CompleteinfoActivity extends AppCompatActivity {

        Button nextBtn;
        CheckBox checkBox;
        boolean isCheck = false;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_creditcertification);
            initViews();

            nextBtn = (Button) findViewById(R.id.next_btn);
            checkBox = (CheckBox) findViewById(R.id.checkbox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    isCheck = isChecked;
                }
            });
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCheck == true) {
                        ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();//***************************
                        JSONObject jsonObject= DataUtils.getJsonObjectTempteproraryData(CompleteinfoActivity.this);//**************************
                        HashMap<String,Object>map=DataUtils.JSONObjToHashMap(CompleteinfoActivity.this, jsonObject);//**************************
                        SimpleApplication simpleApplication=(SimpleApplication)getApplication();
                        simpleApplication.startTime(map.get(DataUtils.loanCountNum).toString());

                        list.add(map);//***************************************
                        try {//***************************************
                            DataUtils.putJsonArrayToShared(CompleteinfoActivity.this,list,DataUtils.getCurrentUser(CompleteinfoActivity.this));//***************************************
                        } catch (JSONException e) {//***************************************
                            e.printStackTrace();//***************************************
                        }
                        CustomDialog dialog=new CustomDialog(CompleteinfoActivity.this,CompleteinfoActivity.this,R.style.dialog,R.layout.dialog);
                        dialog.show();
                    }
                }
            });
        }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back_info4:
                    onBackPressed();
                    break;
            }
        }
    };
    private ImageView backImg;
    private void initViews(){
        backImg= (ImageView) findViewById(R.id.back_info4);
        backImg.setOnClickListener(listener);
        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }

}
