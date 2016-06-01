package business;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import Utils.LogUtils;

/**
 * Created by ad on 16/5/25.
 */
public class SimpleApplication extends Application {

    private static Timer timer;

    private static long second=34;//默认是34秒;

    private static HashMap<String,Long> secondMap=new HashMap<>();

    private static TimerTask task =new TimerTask() {
        @Override
        public void run() {
            //更新所有的倒计时.
            Set<String> keys=secondMap.keySet();

            LogUtils.logD("输入seconds1 "+secondMap.get("1")+"seconds2 "+secondMap.get("2"));
            for(String key:keys){
                long second=secondMap.get(key);

                if(second>0){
                    second--;
                }
                secondMap.put(key,second);
            }
        }
    };

    /**
     * 开启倒计时,并为其分配键值;
     * @param key
     */
    public void startTime(String key){

        secondMap.put(key,second);
        timer=new Timer();
        timer.schedule(task, 1000, 1000);
    }

    /**
     * 开启倒计时,并为其分配键值与倒计时时间.
     * @param key
     * @param time
     */
    public  void startTime(String key,long time){

        second=time;
        secondMap.put(key,second);
        timer=new Timer();
        timer.schedule(task, 1000, 1000);
        second=34;
    }

    public void startTime(HashMap<String,Long> map){
        secondMap.putAll(map);
        Set<String> keys=map.keySet();
        for(String s:keys){
            LogUtils.logD(""+map.get(s));
        }


        timer=new Timer();
        timer.schedule(task, 1000, 1000);
    }
    /**
     * 得到某个键值的剩余秒数.
     * @param key
     * @return
     */
    public  long getTime(String key){
        if(secondMap.keySet().contains(key)) {
            return secondMap.get(key);
        }
        else{
            //时间已经被remove或者并没有添加.
            return 0;
        }
    }

    /**
     * 设置倒计时默认时间.
     * @param time
     */
    public void setDefaultTime(long time){
        SimpleApplication.second=time;
    }

    /**
     * 把结束的时间remove掉.
     */
    public void removeTime(String key){
        secondMap.remove(key);
    }
}
