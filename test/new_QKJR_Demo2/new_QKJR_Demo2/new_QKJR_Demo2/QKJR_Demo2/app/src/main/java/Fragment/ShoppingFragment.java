package Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkjr_demo2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import Utils.LogUtils;
import Utils.SharePreferenceUtils;
import Widgets.ActivityCollector;
import business.PersonalDetailActivity;
import business.ShoppingDetailActivity;
import business.SimpleApplication;
import business.SubmitOrderActivity;
import business.SubmitStagingOrderActivity;

import static android.content.Context.SEARCH_SERVICE;
import static business.WelcomeActivity.random;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ShoppingFragment extends Fragment implements View.OnClickListener {
    private Button btn_buy,btn_buy_staging,btn_staging12,btn_staging15,btn_staging18,btn_staging24;
    private TextView hourTv, minuteTv, secondTv,goods_name,goods_money;
    private ImageView goods_img;
    private Timer timer=new Timer();
    private SimpleApplication application;
    public static boolean toApplystatus = false;
//    SharePreferenceUtils shared;
    //接收来自FirstFragment的数据
//    private String NAME = "firstFragment_name";
//    private String MONEY = "firstFragment_money";
//    private String ID ="firstFragment_id";

   private double sale;

    public  static String name,money;
    public  static int id,number;
    public static double paysale1,paysale2,paysale3,paysale4,paysale;
    public static String flag;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoppingimg,container,false );
        /************************************************************************************/
        ActivityCollector.addActivity(getActivity());
        /**************************************************************************************************/
        initView(view);
        initEvents();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
//        paysale = sale/number;
//        System.out.println("------------------paysale"+paysale);
    }

    private void initEvents() {
        btn_buy.setOnClickListener(this);
        btn_buy_staging.setOnClickListener(this);
        btn_staging12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = 12;
              paysale = paysale1;
                btn_staging15.setBackgroundResource(R.mipmap.product_detail);
                btn_staging18.setBackgroundResource(R.mipmap.product_detail);
                btn_staging24.setBackgroundResource(R.mipmap.product_detail);
                btn_staging12.setBackgroundResource(R.mipmap.product_detail2);
            }
        });
        btn_staging15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = 15;
                paysale = paysale2;
                btn_staging18.setBackgroundResource(R.mipmap.product_detail);
                btn_staging12.setBackgroundResource(R.mipmap.product_detail);
                btn_staging24.setBackgroundResource(R.mipmap.product_detail);
                btn_staging15.setBackgroundResource(R.mipmap.product_detail2);
            }
        });
        btn_staging18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = 18;
                paysale = paysale3;
                btn_staging12.setBackgroundResource(R.mipmap.product_detail);
                btn_staging15.setBackgroundResource(R.mipmap.product_detail);
                btn_staging24.setBackgroundResource(R.mipmap.product_detail);
                btn_staging18.setBackgroundResource(R.mipmap.product_detail2);
            }
        });
        btn_staging24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = 24;
                paysale = paysale4;
                btn_staging12.setBackgroundResource(R.mipmap.product_detail);
                btn_staging15.setBackgroundResource(R.mipmap.product_detail);
                btn_staging18.setBackgroundResource(R.mipmap.product_detail);
                btn_staging24.setBackgroundResource(R.mipmap.product_detail2);
            }
        });


    }
    long seconds;
    private void initView(View view) {
        goods_img = (ImageView)view.findViewById(R.id.shopping_container) ;
        btn_buy = (Button)view.findViewById(R.id.product_detail_buy);
        btn_buy_staging =(Button)view.findViewById(R.id.apply_stage_buy);
        btn_staging12 = (Button)view.findViewById(R.id.product_detail12);
        btn_staging15 = (Button)view.findViewById(R.id.product_detail15);
        btn_staging18 = (Button)view.findViewById(R.id.product_detail18);
        btn_staging24 = (Button)view.findViewById(R.id.product_detail24);

        hourTv = (TextView)view.findViewById(R.id.pro_hour);
        minuteTv = (TextView)view.findViewById(R.id.pro_minute);
        secondTv = (TextView)view.findViewById(R.id.pro_second);
        goods_money = (TextView)view.findViewById(R.id.text_shoppingimgmoney) ;
        goods_name = (TextView)view.findViewById(R.id.text_shoppingimgname) ;

        application=(SimpleApplication)getActivity().getApplication();
        show();


    /*    application=(SimpleApplication)getActivity().getApplication();
        HashMap<String,Long> map=new HashMap<>();
        /**********************************************************************/
        /*****************************************************************************/
      /*      long seconds=random.nextInt(36000)+100;
            LogUtils.logD("随机数"+seconds);
            map.put(""+0,seconds);
        application.startTime(map);
        get=new Get();
        new Thread(get).start();
    }
    Get get;
    long a;
    private ArrayList<HashMap<String,Long>> valueList;
    class Get implements Runnable{
        @Override
        public void run() {
            valueList=new ArrayList<>();
                long seconds=application.getTime(""+0);
                LogUtils.logD("取出seconds1 "+application.getTime(""+1)+"seconds2 "+application.getTime(""+2));
                valueList.add(getMap(seconds));
            a= application.getTime("1");
            LogUtils.logD("倒计时："+a);
            handler.sendEmptyMessage(200);
            handler.postDelayed(get,1000);
        }
    }
    private HashMap<String,Long> getMap(long seconds){
        HashMap<String,Long>valueMap=new HashMap<>();
        long hours=  (seconds/3600);
        long minutes= ((seconds-hours*3600)/60);
        long secondValue=seconds-hours*3600-minutes*60;
        valueMap.put("hours",hours);
        valueMap.put("minutes",minutes);
        valueMap.put("seconds",secondValue);
        return valueMap;*/
        seconds=random.nextInt(36000)+100;

        get=new Get();
        new Thread(get).start();
    }

    private void show() {
//        shared  = new SharePreferenceUtils(getActivity(),"firstfragment");
        if(flag.equals("first")){
            name =FirstFragment.name;
            money = FirstFragment.money;
            id = FirstFragment.imgid;
        }
        else {
            name =SecondtFragment.name2;
            money = SecondtFragment.money2;
            id = SecondtFragment.id2;
        }
      sale = Double.parseDouble(money.substring(1));

        System.out.println("--------------------sale="+sale);
        paysale1 = Math.rint(sale/12);
        paysale2 = Math.rint(sale/15);
        paysale3  = Math.rint(sale/18);
        paysale4 = Math.rint(sale/24);
        btn_staging12.setText(paysale1+"×12期\n（含手续费）");
        btn_staging15.setText(paysale2+"×15期\n（含手续费）");
        btn_staging18.setText(paysale3+"×18期\n（含手续费）");
        btn_staging24.setText(paysale4+"×24期\n（含手续费）");
        goods_name.setText(name);
        goods_money.setText(money);
        goods_img.setBackgroundResource(id);


    }

    Get get;
    class Get implements Runnable{
        @Override
        public void run() {
            handler.sendEmptyMessage(200);
            handler.postDelayed(get,1000);
            long hours=  (seconds/3600);
            long minutes= ((seconds-hours*3600)/60);
            long secondValue=seconds-hours*3600-minutes*60;
            //   HashMap<String, Long> map = valueList.get(0);
            //   minutes = valueList.get(0).get("minutes");
            //  seconds = valueList.get(0).get("seconds");
            //  hours = valueList.get(0).get("hours");
            minuteTv.setText(minutes + "");
            secondTv.setText(secondValue + "");
            hourTv.setText(hours + "");
        }
    }

    private android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };




    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case  R.id.product_detail_buy:
                startActivity(new Intent(getActivity(), SubmitOrderActivity.class));
                break;
            case R.id.apply_stage_buy:
                startActivity(new Intent(getActivity(), PersonalDetailActivity.class));
                break;
        }

    }
}
