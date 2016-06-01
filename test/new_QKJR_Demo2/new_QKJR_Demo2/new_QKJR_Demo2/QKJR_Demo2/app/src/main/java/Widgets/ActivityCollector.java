package Widgets;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 收集和销毁activity的公共类
 * Created by Administrator on 2016/5/26.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
