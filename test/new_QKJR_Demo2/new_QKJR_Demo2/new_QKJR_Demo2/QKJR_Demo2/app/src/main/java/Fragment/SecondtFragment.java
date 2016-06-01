package Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkjr_demo2.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import Utils.LogUtils;
import business.ShoppingDetailActivity;
import business.SimpleApplication;

import static business.WelcomeActivity.random;


/**
 * 限时购物
 * Created by wangfeng on 2016/5/23.
 */
public class SecondtFragment extends Fragment implements View.OnClickListener {
    private String flag = "second";
    public  static  String name2,money2;
    public  static int id2;
    private TextView text_name1,text_name2,text_name3,text_name4,text_name5,text_name6,text_name7,text_name8,text_name9;
    private TextView text_money1,text_money2,text_money3,text_money4,text_money5,text_money6,text_money7,text_money8,text_money9;
    private ImageView special_img1,special_img2,specail_img3,specail_img4,specail_img5,specail_img6,specail_img7,specail_img8,specail_img9;
    private int []imgid = {
        R.mipmap.img_shopping_goods,
            R.mipmap.img_shopping_goods2,
            R.mipmap.img_shopping_goods3,
            R.mipmap.img_shopping_goods4,
            R.mipmap.img_shopping_goods5,
            R.mipmap.img_shopping_goods6,
            R.mipmap.img_shopping_goods7,
            R.mipmap.img_shopping_goods8,
            R.mipmap.img_shopping_goods9,


    };
    private TextView secondsTv1,secondsTv2,secondsTv3,secondsTv4,secondsTv5;
    private TextView secondsTv6,secondsTv7,secondsTv8,secondsTv9,hoursTv1,hoursTv2,hoursTv3;
    private TextView hoursTv4,hoursTv5,hoursTv6,hoursTv7,hoursTv8,hoursTv9;
    private TextView minutes1,minutes2,minutes3,minutes4,minutes5,minutes6,minutes7,minutes8,minutes9;
    private TextView [] secondsTvs={secondsTv1,secondsTv2,secondsTv3,secondsTv4,secondsTv5,secondsTv6,secondsTv7,secondsTv8,secondsTv9};
    private TextView [] hoursTvs={hoursTv1,hoursTv2,hoursTv3,hoursTv4,hoursTv5,hoursTv6,hoursTv7,hoursTv8,hoursTv9};
    private TextView [] minutesTvs={minutes1,minutes2,minutes3,minutes4,minutes5,minutes6,minutes7,minutes8,minutes9};
    private int[] secondsIds={R.id.seconds1,R.id.seconds2,R.id.seconds3,R.id.seconds4,R.id.seconds5,R.id.seconds6,R.id.seconds7,R.id.seconds8,R.id.seconds9};
    private int[] minuteIds={R.id.minutes1,R.id.minutes2,R.id.minutes3,R.id.minutes4,R.id.minutes5,R.id.minutes6,R.id.minutes7,R.id.minutes8,R.id.minutes9};
    private int[] hoursIds={R.id.hours1,R.id.hours2,R.id.hours3,R.id.hours4,R.id.hours5,R.id.hours6,R.id.hours7,R.id.hours8,R.id.hours9};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_special,container,false);
        initView(view);
        initLIsteners();
        return view;
    }


    private void initLIsteners() {
        special_img1.setOnClickListener(this);
        special_img2.setOnClickListener(this);
        specail_img3.setOnClickListener(this);
        specail_img4.setOnClickListener(this);
        specail_img5.setOnClickListener(this);
        specail_img6.setOnClickListener(this);
        specail_img7.setOnClickListener(this);
        specail_img8.setOnClickListener(this);
        specail_img9.setOnClickListener(this);
    }

    private void initView(View view){
        special_img1 = (ImageView) view.findViewById(R.id.special_good1);
        special_img2 = (ImageView) view.findViewById(R.id.special_good2);
        specail_img3 = (ImageView) view.findViewById(R.id.special_good3);
        specail_img4 = (ImageView) view.findViewById(R.id.special_good4);
        specail_img5 = (ImageView) view.findViewById(R.id.special_good5);
        specail_img6 = (ImageView) view.findViewById(R.id.special_good6);
        specail_img7 = (ImageView) view.findViewById(R.id.special_good7);
        specail_img8 = (ImageView) view.findViewById(R.id.special_good8);
        specail_img9 = (ImageView) view.findViewById(R.id.special_good9);
        text_name1 = (TextView)view.findViewById(R.id.special_name1);
        text_name2 = (TextView)view.findViewById(R.id.special_name2);
        text_name3 = (TextView)view.findViewById(R.id.special_name3);
        text_name4 = (TextView)view.findViewById(R.id.special_name4);
        text_name5 = (TextView)view.findViewById(R.id.special_name5);
        text_name6 = (TextView)view.findViewById(R.id.special_name6);
        text_name7 = (TextView)view.findViewById(R.id.special_name7);
        text_name8 = (TextView)view.findViewById(R.id.special_name8);
        text_name9 = (TextView)view.findViewById(R.id.special_name9);
        text_money1 = (TextView)view.findViewById(R.id.special_money1);
        text_money2 = (TextView)view.findViewById(R.id.special_money2);
        text_money3 = (TextView)view.findViewById(R.id.special_money3);
        text_money4 = (TextView)view.findViewById(R.id.special_money4);
        text_money5 = (TextView)view.findViewById(R.id.special_money5);
        text_money6 = (TextView)view.findViewById(R.id.special_money6);
        text_money7 = (TextView)view.findViewById(R.id.special_money7);
        text_money8 = (TextView)view.findViewById(R.id.special_money8);
        text_money9 = (TextView)view.findViewById(R.id.special_money9);


        for(int i=0;i<9;i++){
            secondsTvs[i]= (TextView) view.findViewById(secondsIds[i]);
            hoursTvs[i]= (TextView) view.findViewById(hoursIds[i]);
            minutesTvs[i]= (TextView) view.findViewById(minuteIds[i]);
        }




        application=(SimpleApplication)getActivity().getApplication();
        HashMap<String,Long> map=new HashMap<>();
        /**********************************************************************/

        /*****************************************************************************/
        for(int i=0;i<9;i++){
            long seconds=random.nextInt(36000)+100;
            LogUtils.logD("随机数"+seconds);
            map.put(""+i,seconds);
        }
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
            for(int i=0;i<9;i++) {
                long seconds=application.getTime(""+i);
                LogUtils.logD("取出seconds1 "+application.getTime(""+1)+"seconds2 "+application.getTime(""+2));
                valueList.add(getMap(seconds));
            }
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
        return valueMap;
    };
    private Timer timer=new Timer();
    private SimpleApplication application;
    private ArrayList<Long> list=new ArrayList<>();

    private android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    LogUtils.logD(a+"");

                    Long minutes,hours,seconds;
                    for(int i=0;i<9;i++){
                        HashMap<String,Long> map=valueList.get(i);
                        minutes=valueList.get(i).get("minutes");
                        seconds=valueList.get(i).get("seconds");
                        hours=valueList.get(i).get("hours");
                        minutesTvs[i].setText(minutes+"");
                        secondsTvs[i].setText(seconds+"");
                        hoursTvs[i].setText(hours+"");
                    }

                    break;
            }
        }
    };




    @Override
    public void onClick(View view) {
        ShoppingFragment.flag = flag;
        switch (view.getId()){
            case R.id.special_good1:
                name2 =text_name1.getText().toString();
                money2 = text_money1.getText().toString();
                id2 = imgid[0];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.special_good2:
                name2 =text_name2.getText().toString();
                money2 = text_money2.getText().toString();
                id2 = imgid[1];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.special_good3:
                name2 =text_name3.getText().toString();
                money2 = text_money3.getText().toString();
                id2= imgid[2];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.special_good4:
                name2 =text_name4.getText().toString();
                money2 = text_money4.getText().toString();
                id2 = imgid[3];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.special_good5:
                name2 =text_name5.getText().toString();
                money2 = text_money5.getText().toString();
                id2 = imgid[4];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.special_good6:
                name2 =text_name6.getText().toString();
                money2 = text_money6.getText().toString();
                id2 = imgid[5];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.special_good7:
                name2 =text_name7.getText().toString();
                money2 = text_money7.getText().toString();
                id2 = imgid[6];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.special_good8:
                name2 =text_name8.getText().toString();
                money2 = text_money8.getText().toString();
                id2 = imgid[7];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.special_good9:
                name2 =text_name9.getText().toString();
                money2 = text_money9.getText().toString();
                id2 = imgid[8];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
        }

    }
}
