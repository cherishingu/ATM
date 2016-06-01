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

import Utils.DataUtils;
import Utils.LogUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/16.
 */

public class Repay2Activity extends Activity {
    private TextView repayOnceTev;//***********************
    private int selState=0;
    Bundle data;//***********************
    Bundle bundle;//**************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repay2);
        data=getIntent().getBundleExtra("data");//****************************
        initViews();

    }

    private ImageView alipaySelImg,weChatSelImg,unionSelImg,backImag;
    private LinearLayout aliLinear,wechatLinear,unionLinear;
    private Button nextStepBtn;

    private void initViews(){
        bundle=new Bundle();//******************************
        alipaySelImg= (ImageView) findViewById(R.id.alipay_sel);
        unionSelImg=(ImageView)findViewById(R.id.unionpay_sel);
        weChatSelImg=(ImageView)findViewById(R.id.wechat_sel);
        repayOnceTev=(TextView)findViewById(R.id.repayonce_tev);//*******************
        backImag= (ImageView) findViewById(R.id.back_repay2);

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);

        if(data==null)
        {
            LogUtils.logD("DATA错误");
            //******************
        }
        else{
            LogUtils.logD("DATAno错误");
        }
        if(repayOnceTev==null){
            LogUtils.logD("textview is null");
        }
        else{
            LogUtils.logD("textview is not null");
        }
        repayOnceTev.setText(data.getString(DataUtils.repayOnce));//*************************

        changeToAlipayStyle();

        alipaySelImg.setOnClickListener(listener);
        weChatSelImg.setOnClickListener(listener);
        unionSelImg.setOnClickListener(listener);

        backImag.setOnClickListener(listener);
        nextStepBtn= (Button) findViewById(R.id.repay_2_next);

        aliLinear= (LinearLayout) findViewById(R.id.alipay_linear);
        wechatLinear= (LinearLayout) findViewById(R.id.wechatpay_linear);
        unionLinear= (LinearLayout) findViewById(R.id.unionpay_linear);

        aliLinear.setOnClickListener(listener);
        wechatLinear.setOnClickListener(listener);
        unionLinear.setOnClickListener(listener);


        nextStepBtn.setOnClickListener(listener);
    }


    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.repay_2_next:
                    /**
                     * 整个swich语句**********************************************
                     */
                    switch (selState){
                        case 0:
                            bundle.putString("payway","支付宝支付");
                            break;
                        case 1:
                            bundle.putString("payway", "微信支付");
                            break;
                        case 2:
                            bundle.putString("payway","银联支付");
                            break;
                    }
                    bundle.putInt("position", data.getInt("position"));//**************************
                    bundle.putString(DataUtils.repayOnce, data.getString(DataUtils.repayOnce));//*****************************
                    bundle.putString(DataUtils.loanTimesExtra,data.getString(DataUtils.loanTimesExtra));//*********************
                    Intent intent=new Intent(Repay2Activity.this,Repay3Activity.class);
                    intent.putExtra("data",bundle);//********************************
                    startActivity(intent);
                    finish();
                    break;


                case R.id.alipay_sel:
                    if(selState!=0){
                        changeToAlipayStyle();
                        selState=0;
                    }
                    break;
                case R.id.wechat_sel:
                    if(selState!=1) {
                        changeToWechatPayStyle();
                        selState=1;
                    }
                    break;
                case R.id.unionpay_sel:
                    if(selState!=2) {
                        changeToUnionPayStyle();
                        selState=2;
                    }
                    break;
                case R.id.alipay_linear:
                    if(selState!=0){
                        changeToAlipayStyle();
                        selState=0;
                    }
                    break;

                case R.id.wechatpay_linear:
                    if(selState!=1) {
                        changeToWechatPayStyle();
                        selState=1;
                    }
                    break;
                case R.id.unionpay_linear:
                    if(selState!=2) {
                        changeToUnionPayStyle();
                        selState=2;
                    }
                    break;
                case R.id.back_repay2:
                    onBackPressed();
                    break;

            }
        }
    };
    private void changeToAlipayStyle(){
        alipaySelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_sel));
        weChatSelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_unsel));
        unionSelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_unsel));
    }

    private void changeToUnionPayStyle(){
        unionSelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_sel));
        weChatSelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_unsel));
        alipaySelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_unsel));
    }

    private void changeToWechatPayStyle(){
        weChatSelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_sel));
        unionSelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_unsel));
        alipaySelImg.setBackgroundDrawable(getResources().getDrawable(R.mipmap.img_unsel));
    }
}
