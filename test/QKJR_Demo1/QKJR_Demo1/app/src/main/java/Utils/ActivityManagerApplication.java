package Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhuzhuxia on 16/5/19.
 */
public class ActivityManagerApplication {
    private static Map<String,Activity> destoryMap = new HashMap<>();

    private ActivityManagerApplication() {
    }

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public static void addDestoryActivity(Activity activity,String activityName) {
        destoryMap.put(activityName,activity);
    }
    /**
     *销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet=destoryMap.keySet();

        Activity activity=destoryMap.get(activityName);
        if(activity!=null){
            activity.finish();
        }
    }

    public static String getTopActivity(Activity context)

    {

        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE) ;

        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1) ;


        if(runningTaskInfos != null) {

            LogUtils.logD(runningTaskInfos.get(0).numActivities+"activity num");
            return (runningTaskInfos.get(0).topActivity).toString();
        }

        else

            return null ;

    }
}
