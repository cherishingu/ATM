package Utils;

import android.app.Activity;
import android.app.Fragment;

import business.MainActivity;

/**
 * Created by zhuzhuxia on 16/5/19.
 */
public class ActivitySetsUtils {
    //对于未登录的时候使用其他功能,登录后要跳转到这个Activity.默认是MainActivity.class
    public static Class logToActivity= MainActivity.class;

    public static Fragment fragment;

    public static int fragmentValue=0;

    public static String mainkey="Main";

    public static String showInfoFrom="";

    public static String vertify="";

}
