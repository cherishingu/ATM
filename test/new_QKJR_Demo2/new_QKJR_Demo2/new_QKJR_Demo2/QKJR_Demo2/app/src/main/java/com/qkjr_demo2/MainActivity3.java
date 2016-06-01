package com.qkjr_demo2;
import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;


import Widgets.ActivityCollector;
import Fragment.FirstFragment;
import Fragment.SecondtFragment;
import Fragment.FourFragment;
import Fragment.ThreeFragment;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MainActivity3 extends Activity implements View.OnClickListener {
    private ImageView img1,img2,img3,img4;
    private FirstFragment firstFrament;
    private SecondtFragment secondFrament;
    private ThreeFragment threeFrament;
    private FourFragment fourFrament;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        InitImg();
        InitEvents();
        select(3);
    }

    private void select(int i) {
        android.app.FragmentManager fm  = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (i)
        {
            //购物
            case  0:
                img1.setBackgroundResource(R.mipmap.img_shopping_select_icon);
                if(firstFrament==null){
                    firstFrament = new FirstFragment();
                    ft.add(R.id.main_fragment_container,firstFrament,"tag1");
                }
                else{
                    Fragment fragment = fm.findFragmentByTag("tag1");
                    ft.show(fragment);
                }
                break;
            //限时特卖
            case  1:
                img2.setBackgroundResource(R.mipmap.img_special_select_icon);
                if(secondFrament==null){
                    secondFrament = new SecondtFragment();
                    ft.add(R.id.main_fragment_container,secondFrament,"tag2");
                }
                else{
                    Fragment fragment = fm.findFragmentByTag("tag2");
                    ft.show(fragment);

                }
                break;
            //我的订单
            case  2:
                img3.setBackgroundResource(R.mipmap.img_order_select_icon);
                if(threeFrament==null){
                    threeFrament = new ThreeFragment();
                    ft.add(R.id.main_fragment_container,threeFrament,"tag3");
                }
                else{
                    Fragment fragment = fm.findFragmentByTag("tag3");
                    ft.show(fragment);
                }
                break;
            //个人中心
            case  3:
                img4.setBackgroundResource(R.mipmap.img_personal_select_icon);
                if(fourFrament ==null){
                    fourFrament = new FourFragment();
                    ft.add(R.id.main_fragment_container,fourFrament,"tag4");
                }
                else{
                    Fragment fragment = fm.findFragmentByTag("tag4");
                    ft.show(fragment);
                    /**********************************************************/
                    /**
                     * 修改
                     */
                    ActivityCollector.finishAll();///////////////////
                }
                break;
        }
        ft.commit();
    }

    private void InitEvents() {
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
    }
    private void InitView() {
        img1 = (ImageView)findViewById(R.id.img_btn_shopping);
        img2 = (ImageView)findViewById(R.id.img_btn_special);
        img3 = (ImageView)findViewById(R.id.img_btn_order);
        img4 = (ImageView)findViewById(R.id.img_btn_personal);
    }
    private void InitImg(){
        img1.setBackgroundResource(R.mipmap.img_shopping_icon);
        img2.setBackgroundResource(R.mipmap.img_special_selling);
        img3.setBackgroundResource(R.mipmap.img_my_order_icon);
        img4.setBackgroundResource(R.mipmap.img_personl_icon);
    }

    @Override
    public void onClick(View view) {
        InitImg();
        switch (view.getId())
        {
            case R.id.img_btn_shopping:
                select(0);
                break;
            case R.id.img_btn_special:
                select(1);
                break;
            case R.id.img_btn_order:
                select(2);
                break;
            case R.id.img_btn_personal:
                select(3);
                break;
        }
    }
    private void hideFragment(android.app.FragmentTransaction fragment) {
        if(firstFrament!=null){
            fragment.hide(firstFrament);
        }
        if(secondFrament!=null){
            fragment.hide(secondFrament);
        }
        if(threeFrament!=null){
            fragment.hide(threeFrament);
        }
        if(fourFrament!=null){
            fragment.hide(fourFrament);
        }
    }
}
