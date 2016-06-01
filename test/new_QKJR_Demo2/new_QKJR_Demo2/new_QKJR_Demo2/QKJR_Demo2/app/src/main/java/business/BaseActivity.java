package business;

import android.app.Activity;
import android.os.Bundle;

import Widgets.ActivityCollector;

/**
 * 项目中的所有activity都继承此类
 * Created by Administrator on 2016/5/26.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}