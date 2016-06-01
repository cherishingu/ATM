package Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qkjr_demo2.R;

import java.util.ArrayList;
import java.util.List;

import Utils.SharePreferenceUtils;
import business.ShoppingDetailActivity;


/**
 * 购物
 * Created by Administrator on 2016/5/23.
 */
public class FirstFragment extends Fragment implements View.OnClickListener {
//    private int currentItem = 0;
protected int lastPointPosition = 0;
    private int [] imgIds = new int[]{
                R.mipmap.bannar_01,
               R.mipmap.bannar_02,
            R.mipmap.bannar_03
    };
  private int img_shopping[] ={
          R.mipmap.img_shopping_goods,
          R.mipmap.img_shopping_goods2,
          R.mipmap.img_shopping_goods3,
          R.mipmap.img_com_goods1,
          R.mipmap.img_com_goods2,
          R.mipmap.img_com_goods3

  };
    public static int imgid;
//   private String ID ="firstFragment_id";
//SharePreferenceUtils shared;
 public    static  String  name, money;
public static String flag = "first";
private List<ImageView>imglsits;
    private List<ImageView>dotlists;
    private boolean isAutoPlay = false;
    private ViewPager viewPager;
    private ImageView phone1,phone2,phone3,pad1,pad2,pad3,btn_img1,btn_img2,dot1,dot2,dot3;
    private TextView phone_name1,phone_name2,phone_name3,phone_money1,phone_money2,phone_money3,pad_name1,pad_name2,pad_name3,pad_money1,pad_money2,pad_money3;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_shopping,container,false);
       InitView(view);
        initData();
        InitEvents();

        return  view;
    }
    /**
     * 开始轮播图切换
     */
//    private void startPlay(){
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
//    }
    /**
     * 停止轮播图切换
     */
//    private void stopPlay(){
//        scheduledExecutorService.shutdown();
//    }

    private void initData() {

        imglsits = new ArrayList<>();
        dotlists = new ArrayList<>();
        dotlists.add(dot1);
        dotlists.add(dot2);
        dotlists.add(dot3);
        for (int i = 0; i < imgIds.length; i++) {
            ImageView img = new ImageView(getActivity());
            img.setBackgroundResource(imgIds[i]);
            imglsits.add(img);

        }

        viewPager.setAdapter(new MyPagerAdapter());

        viewPager.setFocusable(true);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        isAutoPlay = true;
        handler.sendEmptyMessageDelayed(0, 2000);

    }
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            // 滑动到下一页
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);

            if(isAutoPlay) {
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        };
    };


    public class MyPageChangeListener implements ViewPager.OnPageChangeListener
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % imglsits.size();
                lastPointPosition = position;

                for (int i = 0; i < dotlists.size(); i++) {
                    if (i == position) {
                        ((View) dotlists.get(position)).setBackgroundResource(R.mipmap.img_shopping_dot_focus);
                    } else {
                        ((View) dotlists.get(i)).setBackgroundResource(R.mipmap.img_shopping_dot_blur);
                    }

                }
            }


                @Override
                public void onPageScrollStateChanged ( int state){
//                switch (state)
//                {
//                    case 1:// 手势滑动，空闲中
//                        isAutoPlay = false;
//                        break;
//                    case 2:// 界面切换中
//                        isAutoPlay = true;
//                        break;
//                    case 0:// 滑动结束，即切换完毕或者加载完毕
//                        // 当前为最后一张，此时从右向左滑，则切换到第一张
//                        if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
//                            viewPager.setCurrentItem(0);
//                        }
//                        // 当前为第一张，此时从左向右滑，则切换到最后一张
//                        else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
//                            viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
//                        }
//                        break;
//
//                }
                }

            }
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return  view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
         container.addView(imglsits.get(position%imglsits.size()));
            return imglsits.get(position%imglsits.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
            object =null;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void InitEvents() {
        phone1.setOnClickListener(this);
        phone2.setOnClickListener(this);
        phone3.setOnClickListener(this);
        pad1.setOnClickListener(this);
        pad2.setOnClickListener(this);
        pad3.setOnClickListener(this);


    }

    private void InitView(View view) {
        phone1 =(ImageView) view.findViewById(R.id.shopping_phone1);
        phone2 =(ImageView) view.findViewById(R.id.shopping_phone2);
        phone3 =(ImageView) view.findViewById(R.id.shopping_phone3);
        pad1 =(ImageView) view.findViewById(R.id.shopping_com1);
        pad2 =(ImageView) view.findViewById(R.id.shopping_com2);
        pad3 =(ImageView) view.findViewById(R.id.shopping_com3);
        btn_img1 = (ImageView)view.findViewById(R.id.btn_shoping_phone_detail);
        btn_img2 = (ImageView)view.findViewById(R.id.btn_shoping_computer_detail);
         viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        dot1 = (ImageView)view.findViewById(R.id.v_dot1);
        dot2 = (ImageView)view.findViewById(R.id.v_dot2);
        dot3 = (ImageView)view.findViewById(R.id.v_dot3);
        phone_name1 = (TextView)view.findViewById(R.id.text_first_phonename1);
        phone_name2 = (TextView)view.findViewById(R.id.text_first_phonename2);
        phone_name3 = (TextView)view.findViewById(R.id.text_first_phonename3);
        phone_money1 = (TextView)view.findViewById(R.id.text_first_phonemoney1);
        phone_money2 = (TextView)view.findViewById(R.id.text_first_phonemoney2);
        phone_money3 = (TextView)view.findViewById(R.id.text_first_phonemoney3);
        pad_name1=(TextView)view.findViewById(R.id.text_first_comname1);
        pad_name2=(TextView)view.findViewById(R.id.text_first_comname2);
        pad_name3=(TextView)view.findViewById(R.id.text_first_comname3);
        pad_money1 = (TextView)view.findViewById(R.id.text_first_commoney1);
        pad_money2 = (TextView)view.findViewById(R.id.text_first_commoney2);
        pad_money3 = (TextView)view.findViewById(R.id.text_first_commoney3);

    }


    @Override
    public void onClick(View view) {
//        shared = new SharePreferenceUtils(getActivity(),"firstfragment");
        ShoppingFragment.flag = flag;
        switch (view.getId())
        {
            case  R.id.shopping_phone1:

                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                name = phone_name1.getText().toString();
                money = phone_money1.getText().toString();
//                id = "1";
//                shared.putString(NAME,name);
//                shared.putString(MONEY,money);
//                shared.putString(ID,id);
                imgid=img_shopping[0];

//                shared.commit();
                break;
            case R.id.shopping_phone2:
                name = phone_name2.getText().toString();
                money = phone_money2.getText().toString();
//                id = "2";
//                shared.putString(ID,id);
//                shared.putString(NAME,name);
//                shared.putString(MONEY,money);
                imgid=img_shopping[1];


//                shared.commit();
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));

                break;
            case R.id.shopping_phone3:
                name = phone_name3.getText().toString();
                money = phone_money3.getText().toString();
//                id = "3";
//                shared.putString(ID,id);
//                shared.putString(NAME,name);
//                shared.putString(MONEY,money);
                imgid=img_shopping[2];

//                shared.commit();
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.shopping_com1:
                name = pad_name1.getText().toString();
                money = pad_money1.getText().toString();
//                id = "4";
//                shared.putString(ID,id);
////                shared.putString(NAME,name);
////                shared.putString(MONEY,money);
//                shared.commit();
                imgid=img_shopping[3];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.shopping_com2:
                name = pad_name2.getText().toString();
                money = pad_money2.getText().toString();
//                id = "5";
//                shared.putString(ID,id);
////                shared.putString(NAME,name);
////                shared.putString(MONEY,money);
//                shared.commit();
                imgid=img_shopping[4];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.shopping_com3:
                name = pad_name3.getText().toString();
                money = pad_money3.getText().toString();
//                id = "6";
//                shared.putString(ID,id);
////                shared.putString(NAME,name);
////                shared.putString(MONEY,money);
//                shared.commit();
                imgid=img_shopping[5];
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.btn_shoping_phone_detail:
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;
            case R.id.btn_shoping_computer_detail:
                startActivity(new Intent(getActivity(), ShoppingDetailActivity.class));
                break;





        }

    }

    @Override
    public void onDestroy() {
        isAutoPlay=false;
        super.onDestroy();
    }
//    private class SlideShowTask implements Runnable{
//
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            synchronized (viewPager) {
//                currentItem = (currentItem+1)%imglsits.size();
//                handler.obtainMessage().sendToTarget();
//            }
//        }
//
//    }
}
