package business;

import android.app.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.qkjr_demo2.MainActivity;
import com.qkjr_demo2.R;

import Fragment.DetailFragment;
import Fragment.FirstFragment;
import Fragment.FourFragment;
import Fragment.SecondtFragment;
import Fragment.ShoppingFragment;
import Fragment.ThreeFragment;
import Utils.SharePreferenceUtils;

/**
 * 商品详情
 * Created by wangfeng on 2016/5/24.
 */
public class ShoppingDetailActivity extends Activity  implements View.OnClickListener{
    private Button btn_goods,btn_goodsdetil;
    private ImageView shopping_back;
    private DetailFragment detailFragment;
    private ShoppingFragment shoppingFragment;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        initView();
        initEvents();
        select(0);

    }

    private void initEvents() {

        shopping_back.setOnClickListener(this);
        btn_goods.setOnClickListener(this);
        btn_goodsdetil.setOnClickListener(this);

    }
    private void initView(){

        shopping_back = (ImageView)findViewById(R.id.product_detail_back);
        btn_goods = (Button)findViewById(R.id.btn_goods);
        btn_goodsdetil = (Button)findViewById(R.id.btn_goodsdetail);



    }
    private void hideFragment(android.app.FragmentTransaction fragment) {
        if(detailFragment!=null){
            fragment.hide(detailFragment);
        }
        if(shoppingFragment!=null){
            fragment.hide(shoppingFragment);
        }


    }
    private void select(int i) {

        android.app.FragmentManager fm  = getFragmentManager();
        android.app.FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);

        switch (i)
        {

            case  0:
                if(shoppingFragment==null){
                    shoppingFragment = new ShoppingFragment();
                    ft.add(R.id.shopping_container,shoppingFragment,"tag1");

                }
                else{
                    Fragment fragment = fm.findFragmentByTag("tag1");
                    ft.show(fragment);

                }
                break;

            case  1:


                if(detailFragment==null){
                    detailFragment = new DetailFragment();
                    ft.add(R.id.shopping_container,detailFragment,"tag2");
                }
                else{
                    Fragment fragment = fm.findFragmentByTag("tag2");
                    ft.show(fragment);


                }

        }
        ft.commit();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.btn_goods:
                select(0);
                break;
            case R.id.btn_goodsdetail:
                select(1);
                break;
            case R.id.product_detail_back:
                startActivity(new Intent(ShoppingDetailActivity.this,MainActivity.class));
                break;



        }

    }
}
