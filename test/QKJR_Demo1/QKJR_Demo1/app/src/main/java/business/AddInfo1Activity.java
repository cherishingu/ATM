package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;

import Utils.ActivityManagerApplication;
import Utils.DataBaseUtils;
import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/17.
 */
public class AddInfo1Activity extends Activity {

    private ArrayList<HashMap<String,Object>> proMaps;
    private ArrayList<String> citis;
    private ArrayAdapter<String> arrayAdapter,houseAdapter,eduAdapter,marrigeAdapter;
    private Spinner spinnerPro,spinnerCity,eduSpinner,marrigeSpinner,houseSpinner;
    private Button nextStepBtn;
    private ImageView backImg;
    private String email,eduBg,marrigeState,houseState,area,
                    province,city,street,neighboor,unit,room;


    private DataBaseUtils utils=new DataBaseUtils(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManagerApplication.addDestoryActivity(AddInfo1Activity.this, "complete1");
        LogUtils.logD("activity start ........");
        setContentView(R.layout.activity_completeinfo);
        doInNoInfo();



        try {
            if(DataUtils.getInfoDone(AddInfo1Activity.this,DataUtils.info1HasDone)==true){

                showInfo();
                LogUtils.logV("show info1................................");
            }
            else{

                LogUtils.logV("edit info1................................");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showInfo(){

    }
    private void doInNoInfo(){
        setContentView(R.layout.activity_completeinfo);
        initData();
        initViews();
        initListenersAndAdapters();
    }

    private EditText emailEdit,areaEdit,unitEdit,roomEdit,streetEdit;

    private void initViews(){

        backImg= (ImageView) findViewById(R.id.back_info1);
        spinnerCity= (Spinner) findViewById(R.id.spinner_city);
        spinnerPro= (Spinner) findViewById(R.id.spinner_pro);

        nextStepBtn= (Button) findViewById(R.id.commit_btn);

        eduSpinner= (Spinner) findViewById(R.id.spinner_edu);
        houseSpinner= (Spinner) findViewById(R.id.spinner_live);
        marrigeSpinner= (Spinner) findViewById(R.id.spinner_marry);

        emailEdit = (EditText) findViewById(R.id.email_edit);
        areaEdit= (EditText) findViewById(R.id.whicharea_edit);
        unitEdit= (EditText) findViewById(R.id.whichunit_edit);
        streetEdit= (EditText) findViewById(R.id.whichway_edit);
        roomEdit= (EditText) findViewById(R.id.whichroom_edit);

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);

    }

    private void initListenersAndAdapters(){
        spinnerPro.setAdapter(arrayAdapter);
        houseSpinner.setAdapter(houseAdapter);
        eduSpinner.setAdapter(eduAdapter);
        marrigeSpinner.setAdapter(marrigeAdapter);


        eduSpinner.setOnItemSelectedListener(eduListener);
        houseSpinner.setOnItemSelectedListener(houseListener);
        marrigeSpinner.setOnItemSelectedListener(marrigeListener);

        spinnerPro.setOnItemSelectedListener(selListener);
        nextStepBtn.setOnClickListener(listener);
        backImg.setOnClickListener(listener);
    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.commit_btn:
                    //存储信息
                    email=emailEdit.getText().toString();
                    street=streetEdit.getText().toString();
                    room=roomEdit.getText().toString();
                    area=areaEdit.getText().toString();
                    unit=unitEdit.getText().toString();

                    if(marrigeState==null){
                        marrigeState=marrigeList.get(0);
                    }
                    if(houseState==null){
                        houseState=houseList.get(0);
                    }
                    if(province==null){
                        province=pros.get(0);
                        initCitis(0);
                    }
                    if(city==null){
                        city=citis.get(0);
                    }
                    if(eduBg==null){
                        eduBg=eduList.get(0);
                    }

                    if(email.length()*street.length()*room.length()*unit.length()*area.length()*eduBg.length()*
                            marrigeState.length()*houseState.length()*province.length()*city.length()!=0) {

                        HashMap<String, String> map = new HashMap<>();
                        map.put(DataUtils.email, email);
                        map.put(DataUtils.street, street);
                        map.put(DataUtils.unit, unit);
                        map.put(DataUtils.room, room);
                        map.put(DataUtils.area, area);
                        map.put(DataUtils.eduBg, eduBg);
                        map.put(DataUtils.marrige, marrigeState);
                        map.put(DataUtils.houseState, houseState);
                        map.put(DataUtils.province, province);
                        map.put(DataUtils.city, city);
                        try {
                            DataUtils.insertInfos(AddInfo1Activity.this, DataUtils.getCurrentUser(AddInfo1Activity.this), map);
                            DataUtils.insert(AddInfo1Activity.this, DataUtils.getCurrentUser(AddInfo1Activity.this), DataUtils.info1HasDone, true);
                            DataUtils.insert(AddInfo1Activity.this, DataUtils.getCurrentUser(AddInfo1Activity.this), DataUtils.info3HasDone, false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(AddInfo1Activity.this, CompleteInfoActivity2.class);
                        //finish();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(AddInfo1Activity.this, "请输入完整信息~", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.back_info1:
                    onBackPressed();
                    break;
            }
        }
    };






    ArrayList<String> pros=new ArrayList<>();
    ArrayList<String> eduList=new ArrayList<>();
    ArrayList<String> houseList=new ArrayList<>();
    ArrayList<String> marrigeList=new ArrayList<>();

    private void initData(){

        proMaps=utils.getProSet();

        for(HashMap<String,Object> m:proMaps){
            pros.add((String) m.get("pname"));
            LogUtils.logD("" +m.get("pname"));
        }
        arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, pros);


        houseList.add("租赁");
        houseList.add("自有非按揭");
        houseList.add("按揭");
        houseList.add("与父母同住");
        houseAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, houseList);


        eduList.add("大专");
        eduList.add("本科");
        eduList.add("硕士");
        eduList.add("博士");

        eduAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, eduList);


        marrigeList.add("未婚");
        marrigeList.add("已婚");
        marrigeList.add("离异");
        marrigeList.add("再婚");

        marrigeAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, marrigeList);


    }

    private void initCitis(int proId){
        citis=utils.getCitSet(proId);
        for(String s:citis){
            LogUtils.logD(s);
        }
        ArrayAdapter array= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, citis);
        spinnerCity.setAdapter(array);
    }

    private int proId,cityId;

    private AdapterView.OnItemSelectedListener selListener=new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                initCitis(position);
                proId=position;
                province=pros.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            province=pros.get(0);
            initCitis(0);
        }
    };

    private AdapterView.OnItemSelectedListener cityListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            cityId=position;
            city=citis.get(cityId);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            city=citis.get(0);
        }
    };
    private AdapterView.OnItemSelectedListener eduListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            eduBg=eduList.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            eduBg=eduList.get(0);
        }
    };
    private AdapterView.OnItemSelectedListener marrigeListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            marrigeState=marrigeList.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            marrigeState=marrigeList.get(0);
        }
    };
    private AdapterView.OnItemSelectedListener houseListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            houseState=houseList.get(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            houseState=houseList.get(0);

        }
    };


}

