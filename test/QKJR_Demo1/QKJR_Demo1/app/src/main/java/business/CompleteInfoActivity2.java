package business;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;

import java.util.HashMap;

import Utils.ActivityManagerApplication;
import Utils.DataUtils;
import Utils.UIUtils;
import Widgets.CustemDialog1;
import Widgets.CustomDialog2;

public class CompleteInfoActivity2 extends AppCompatActivity {

    public static EditText relashipEdit;
    public static EditText relaship1Edit;
    Button nextBtn;
    boolean is1=false;
    boolean is2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completeinfo2);
        initViews();
        ActivityManagerApplication.addDestoryActivity(CompleteInfoActivity2.this, "complete2");
        relaship1Edit=(EditText)findViewById(R.id.edit_relaship1);
        relaship1Edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (is2 == false) {
                        Log.d("relashipEdit", "获得焦点");
                        InputMethodManager imm = (InputMethodManager) CompleteInfoActivity2.this.getSystemService(CompleteInfoActivity2.this.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(relashipEdit.getWindowToken(), 0);
                        CustomDialog2 CustomDialog2 = new CustomDialog2(CompleteInfoActivity2.this, R.style.dialog, R.layout.contact_other_relaship);
                        CustomDialog2.show();
                        Log.d("relashipEdit", "得到焦点时自定义dialog显示");
                        imm.showSoftInputFromInputMethod(relashipEdit.getWindowToken(), 0);
                        is2 = !is2;
                    }
                }
            }
        });
        relaship1Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if ((listnerTime) % 2 == 0) {
                if (is2 == true) {
                    Log.d("relashipEdit", "获得点击");
                    InputMethodManager imm = (InputMethodManager) CompleteInfoActivity2.this.getSystemService(CompleteInfoActivity2.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(relashipEdit.getWindowToken(), 0);
                    CustomDialog2 custemDialog2 = new CustomDialog2(CompleteInfoActivity2.this, R.style.dialog, R.layout.contact_other_relaship);
                    custemDialog2.show();
                    Log.d("relashipEdit", "点击自定义dialog显示");
                    imm.showSoftInputFromInputMethod(relashipEdit.getWindowToken(), 0);
                    is2 = !is2;
                } else {
                    is2 = !is2;
                }
            }
        });
        relashipEdit=(EditText)findViewById(R.id.edit_relaship);
        relashipEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (is1 == false) {
                        Log.d("relashipEdit", "获得焦点");
                        InputMethodManager imm = (InputMethodManager) CompleteInfoActivity2.this.getSystemService(CompleteInfoActivity2.this.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(relashipEdit.getWindowToken(), 0);
                        CustemDialog1 custemDialog1 = new CustemDialog1(CompleteInfoActivity2.this, R.style.dialog, R.layout.contact_relaship);
                        custemDialog1.show();
                        Log.d("relashipEdit", "得到焦点时自定义dialog显示");
                        imm.showSoftInputFromInputMethod(relashipEdit.getWindowToken(), 0);
                        is1 = !is1;
                    }
                }
            }
        });
        relashipEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (is1 == true) {

                    Log.d("relashipEdit", "获得点击");
                    InputMethodManager imm = (InputMethodManager) CompleteInfoActivity2.this.getSystemService(CompleteInfoActivity2.this.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(relashipEdit.getWindowToken(), 0);
                    CustemDialog1 custemDialog1 = new CustemDialog1(CompleteInfoActivity2.this, R.style.dialog, R.layout.contact_relaship);
                    custemDialog1.show();
                    Log.d("relashipEdit", "点击自定义dialog显示");
                    imm.showSoftInputFromInputMethod(relashipEdit.getWindowToken(), 0);
                    is1 = !is1;
                } else {
                    is1 = !is1;
                }
            }
        });

    }


    private Button nextStepBtn;
    private ImageView backImg;
    private void initViews(){
        nextStepBtn= (Button) findViewById(R.id.add2_next_step);
        nextStepBtn.setOnClickListener(listener);
        name1Edit= (EditText) findViewById(R.id.relativename_edit);
        housePhone1Edit= (EditText) findViewById(R.id.relativehome_edit);
        mobilePhone1Edit= (EditText) findViewById(R.id.relativephone_edit);

        name2Edit= (EditText) findViewById(R.id.relativename_edit1);
        housePhone2Edit= (EditText) findViewById(R.id.relativehome_edit1);
        mobilePhone2Edit= (EditText) findViewById(R.id.relativephone_edit1);

        backImg= (ImageView) findViewById(R.id.back_info2);
        backImg.setOnClickListener(listener);

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);

    }

    /**************************************************************************
     *
     *
     * edit by gulidong at 2016/5/20.
     * 以下主要修改姚津的代码.
     * 1,增加了按钮的监听,主要是获取填写的数据并存储.
     * 用到的工具类:DataUtils
     *
     ***************************************************************************
     */
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add2_next_step:

                    if(name1Edit.getText().length()*housePhone1Edit.getText().length()*mobilePhone1Edit.getText().length()
                            *relaship1Edit.getText().length()*mobilePhone2Edit.getText().length()*name2Edit.getText().length()
                                                        *housePhone2Edit.getText().length()*relashipEdit.getText().length()==0){

                        Toast.makeText(CompleteInfoActivity2.this, "请输入完整信息!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            saveInfos();
                            DataUtils.insert(CompleteInfoActivity2.this, DataUtils.getCurrentUser(CompleteInfoActivity2.this)
                                    , DataUtils.info2HasDone, true);
                            DataUtils.insert(CompleteInfoActivity2.this, DataUtils.getCurrentUser(CompleteInfoActivity2.this), DataUtils.info3HasDone, false);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent it = new Intent(CompleteInfoActivity2.this, CompleteInfoActivity3.class);
                        startActivity(it);
                    }
                    break;
                case R.id.back_info2:
                    onBackPressed();
                    break;
            }
        }
    };

    private EditText name1Edit,housePhone1Edit,mobilePhone1Edit,
                    name2Edit,housePhone2Edit,mobilePhone2Edit;

    /**
     * 保存信息.
     * @throws JSONException
     */
    private void saveInfos() throws JSONException {



        HashMap<String ,String> map=new HashMap<>();
        map.put(DataUtils.relativeMobilePhone1,mobilePhone1Edit.getText().toString());
        map.put(DataUtils.relativeHomePhone1,housePhone1Edit.getText().toString());
        map.put(DataUtils.relativeName1,name1Edit.getText().toString());

        map.put(DataUtils.relativeMobilePhone2,mobilePhone2Edit.getText().toString());
        map.put(DataUtils.relativeHomePhone2,housePhone2Edit.getText().toString());
        map.put(DataUtils.relativeName2,name2Edit.getText().toString());

        map.put(DataUtils.relative1,relashipEdit.getText().toString());
        map.put(DataUtils.relative2,relaship1Edit.getText().toString());

        DataUtils.insertInfos(CompleteInfoActivity2.this,DataUtils.getCurrentUser(CompleteInfoActivity2.this),map);

    }


}
