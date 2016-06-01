package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;

import Utils.ActivityManagerApplication;
import Utils.ActivitySetsUtils;
import Utils.DataUtils;
import Utils.SharePreferenceUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/23.
 * 主要是用来显示保存的信息,layout文件是复制的姚津的代码,
 * 变量太多,信息量太大.layout命名不规范,已经吐血到死.
 *
 *
 */

public class ShowInfoActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showinfo1);
        initViews();

        ActivityManagerApplication.destoryActivity("complete1");
        ActivityManagerApplication.destoryActivity("complete2");
        ActivityManagerApplication.destoryActivity("complete3");
        try {
            initDatas();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private TextView eduTv,emailTv,marrigeTv,houseTv,proTv,cityTv,relativeName2Tv,housePhone2Tv,mobilePhone2Tv,relativeShip2Tv,
                        wayTv,areaTv,unitTv,roomTv,relativeName1Tv,housePhone1Tv,mobilePhone1Tv,relativeShip1Tv,workPlaceTv,
                        workAddressTv,workPhoneTv,workUnitTv,workUnitTypeTv,hireTypeTv;

    private Button sureBtn;
    private ImageView backImg;

    private void initViews(){

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
        eduTv= (TextView) findViewById(R.id.edu_text);
        emailTv= (TextView) findViewById(R.id.email_text);
        marrigeTv= (TextView) findViewById(R.id.marrige_text);
        houseTv= (TextView) findViewById(R.id.house_text);
        proTv= (TextView) findViewById(R.id.pro_text);
        cityTv= (TextView) findViewById(R.id.city_text);
        wayTv= (TextView) findViewById(R.id.way_text);
        areaTv= (TextView) findViewById(R.id.area_text);
        unitTv= (TextView) findViewById(R.id.unit_text);
        roomTv= (TextView) findViewById(R.id.room_text);

        relativeName1Tv= (TextView) findViewById(R.id.relativename_text);
        housePhone1Tv= (TextView) findViewById(R.id.relativehome_text);
        mobilePhone1Tv= (TextView) findViewById(R.id.relativephone_text);
        relativeShip1Tv= (TextView) findViewById(R.id.text_relaship);


        relativeName2Tv= (TextView) findViewById(R.id.relativename_text1);
        housePhone2Tv= (TextView) findViewById(R.id.relativehome_text1);
        mobilePhone2Tv= (TextView) findViewById(R.id.relativephone_text1);
        relativeShip2Tv= (TextView) findViewById(R.id.text_relaship1);

        workPlaceTv= (TextView) findViewById(R.id.workplacename_text);
        workAddressTv= (TextView) findViewById(R.id.adressdetail_text);

        workPhoneTv= (TextView) findViewById(R.id.workplacephone_text);
        workUnitTv= (TextView) findViewById(R.id.workunit_text1);
        workUnitTypeTv= (TextView) findViewById(R.id.unittype_text);
        hireTypeTv= (TextView) findViewById(R.id.hiretype_text);

        sureBtn= (Button) findViewById(R.id.btn_sure);
        backImg= (ImageView) findViewById(R.id.back_show);

        backImg.setOnClickListener(listener);

        if(ActivitySetsUtils.showInfoFrom=="Loan"){
            sureBtn.setVisibility(View.VISIBLE);
            sureBtn.setOnClickListener(listener);
            ActivitySetsUtils.showInfoFrom="";
        }

    }

    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_sure:
                    finish();
                    Intent intent=new Intent(ShowInfoActivity.this,CompleteinfoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back_show:
                    onBackPressed();
                    break;
            }

        }
    };
    private void initDatas() throws JSONException {

        SharePreferenceUtils utils=new SharePreferenceUtils(ShowInfoActivity.this,DataUtils.shareName);
        String info=utils.getString(DataUtils.getCurrentUser(ShowInfoActivity.this));
        eduTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.eduBg));
        emailTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.email));

        marrigeTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.marrige));
        houseTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.houseState));
        proTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.province));
        cityTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.city));
        wayTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.street));
        areaTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.area));
        unitTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.unit));
        roomTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.room));



        relativeName1Tv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.relativeName1));
        relativeName2Tv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.relativeName2));
        relativeShip1Tv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.relative1));
        relativeShip2Tv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.relative2));
        housePhone1Tv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.relativeHomePhone1));
        housePhone2Tv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.relativeHomePhone2));
        mobilePhone1Tv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.relativeMobilePhone1));
        mobilePhone2Tv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.relativeMobilePhone2));

        workUnitTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.unitName));
        workPhoneTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.unitPhone));
        workPlaceTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.unit));
        workUnitTypeTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.unitType));
        workAddressTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.unitAddress));
        hireTypeTv.setText((String)DataUtils.get(ShowInfoActivity.this,DataUtils.hireType));
    }






}
