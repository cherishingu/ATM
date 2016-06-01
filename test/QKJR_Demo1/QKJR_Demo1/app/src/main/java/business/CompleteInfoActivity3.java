package business;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;

import java.util.HashMap;

import Utils.ActivityManagerApplication;
import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;

/**
 * 修改的姚津2016/05/20
 */
public class CompleteInfoActivity3 extends AppCompatActivity {

    private  String [] unitTypeArray={"事业","机关","国有","私企","股份制","外企","个体","部队","武警","医院"};
    private  String [] hireTypeArray={"自雇","受薪"};
    private  String [] oprateTimeArray={"2年以内","3年","4年","5年","5年以上"};

    private Button btn;
    private ImageView backImg;
    private Spinner unitTypeSpin;
    private Spinner hireTypeSpin;
    private Spinner oprateTimeSpin;
    private ArrayAdapter unitTypeAdapter;
    private ArrayAdapter hireTypeAdapter;
    private ArrayAdapter oprateTimeAdapter;

    private String unitType,hireType,oprateTime;

    private EditText unitNameEdit,unitAddressEdit,unitPhoneEdit,unitDepartMentEdit,unitScopeEdit;

    private  View lineLay,hireTypeLinelay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completeinfo3);
        initViews();
        initListenersAndAdapter();
        ActivityManagerApplication.addDestoryActivity(CompleteInfoActivity3.this, "complete3");


    }

    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()){
                case R.id.back_info3:
                    onBackPressed();
                    break;
                case R.id.next_btn:
                    submit();
                    break;
            }


        }
    };

    private void submit(){
        if(unitType==null||unitType.length()==0){
            unitType=unitTypeArray[0];
            LogUtils.logD("unittype 不符合要求");
        }
        if(hireType==null||hireType.length()==0){
            hireType=hireTypeArray[0];
            LogUtils.logD("hiretype 不符合要求");
        }
        if(unitNameEdit.getText().length()*unitAddressEdit.getText().toString().length()*unitPhoneEdit.getText().toString().length()*
                unitDepartMentEdit.getText().toString().length()!=0){

            try {
                saveInfos();
                //改变这个页面完善信息的情况.
                DataUtils.insert(CompleteInfoActivity3.this,DataUtils.getCurrentUser(CompleteInfoActivity3.this)
                        ,DataUtils.info3HasDone,true);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            LogUtils.logD(ActivitySetsUtils.showInfoFrom+"**************");
            if(ActivitySetsUtils.showInfoFrom=="Loan") {
                Intent it = new Intent(CompleteInfoActivity3.this, CompleteinfoActivity.class);

                startActivity(it);
                // finish();
            }
            else{
                Intent it = new Intent(CompleteInfoActivity3.this, ShowInfoActivity.class);
                startActivity(it);
                // finish();
            }
        }
        else{
            Toast.makeText(CompleteInfoActivity3.this, "请完善信息!", Toast.LENGTH_SHORT).show();

        }
    }
    private void initViews(){

        backImg= (ImageView) findViewById(R.id.back_info3);
        backImg.setOnClickListener(clickListener);
        btn=(Button)findViewById(R.id.next_btn);
        btn.setOnClickListener(clickListener);
        unitTypeSpin=(Spinner)findViewById(R.id.unittype_spin);
        hireTypeSpin=(Spinner)findViewById(R.id.hiretype_spin);
        oprateTimeSpin=(Spinner)findViewById(R.id.timeoprate_spin);
        lineLay=findViewById(R.id.hireselfchoose_linelay);
        hireTypeLinelay=findViewById(R.id.hiretype_linelay);
        unitAddressEdit= (EditText) findViewById(R.id.adressdetail_edit);
        unitNameEdit= (EditText) findViewById(R.id.workplacename_edit);
        unitPhoneEdit= (EditText) findViewById(R.id.workplacephone_edit);
        unitDepartMentEdit= (EditText) findViewById(R.id.workunit_edit1);
        unitScopeEdit= (EditText) findViewById(R.id.areaoprate_edit);

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }


    private void initListenersAndAdapter(){

        unitTypeAdapter=new ArrayAdapter(CompleteInfoActivity3.this,android.R.layout.simple_list_item_1,unitTypeArray);
        hireTypeAdapter=new ArrayAdapter(CompleteInfoActivity3.this,android.R.layout.simple_list_item_1,hireTypeArray);
        oprateTimeAdapter=new ArrayAdapter(CompleteInfoActivity3.this,android.R.layout.simple_list_item_1,oprateTimeArray);
        unitTypeSpin.setAdapter(unitTypeAdapter);
        hireTypeSpin.setAdapter(hireTypeAdapter);
        oprateTimeSpin.setAdapter(oprateTimeAdapter);
        hireTypeSpin.setSelection(1);
        unitTypeSpin.setOnItemSelectedListener(unitListener);
        oprateTimeSpin.setOnItemSelectedListener(oprateTimeListener);
        hireTypeSpin.setOnItemSelectedListener(hireListener);
    }


    private void saveInfos() throws JSONException {
        HashMap<String,String > map=new HashMap<>();
        map.put(DataUtils.unitName,unitNameEdit.getText().toString());
        map.put(DataUtils.unitAddress,unitAddressEdit.getText().toString());
        map.put(DataUtils.unitPhone,unitPhoneEdit.getText().toString());
        map.put(DataUtils.unitDepartment,unitDepartMentEdit.getText().toString());
        map.put(DataUtils.unitType,unitType);
        map.put(DataUtils.hireType,hireType);

        DataUtils.insertInfos(CompleteInfoActivity3.this, DataUtils.getCurrentUser(CompleteInfoActivity3.this),map);

    }



    AdapterView.OnItemSelectedListener unitListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            unitType=unitTypeArray[position];
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            unitType=unitTypeArray[0];
        }
    };

    AdapterView.OnItemSelectedListener hireListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position == 0) {
                lineLay.setVisibility(View.VISIBLE);
            }
            if(position==1) {
                lineLay.setVisibility(View.INVISIBLE);
            }
            hireType=hireTypeArray[position];
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            hireType=hireTypeArray[0];

        }
    };
    AdapterView.OnItemSelectedListener oprateTimeListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            oprateTime=oprateTimeArray[position];
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            oprateTime=oprateTimeArray[0];
        }
    };

}
