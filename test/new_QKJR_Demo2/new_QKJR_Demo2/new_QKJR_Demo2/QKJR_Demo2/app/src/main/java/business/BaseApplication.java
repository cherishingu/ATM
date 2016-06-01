package business;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;


/**
 * Created by sxzhang on 2016/2/23.
 * Codes can never be perfect!
 */

public class BaseApplication extends Application {
    public static final String TAG = "VolleyPatterns";

    private static BaseApplication mInstance;
    private static int mMainThreadId = -1;
    private static Thread mMainThread;
    private static Handler mMainThreadHandler;
    private static Looper mMainLooper;

    private static SQLiteDatabase db;


    public static Application getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        mInstance = this;

        mMainThreadId = android.os.Process.myTid();
        mMainThread = Thread.currentThread();
        mMainThreadHandler = new Handler();
        mMainLooper = getMainLooper();

        setUpDataBase();

        super.onCreate();


        if (checkIfIsAppRunning(getPackageName())) {
//            initialize();
        }
    }

    /**
     * 初始化数据库
     */
    private void setUpDataBase() {


    }

    public static SQLiteDatabase getDb() {
        return db;
    }



//    private void initialize() {
//        //TODO 设置Buggly 渠道 和 App的版本
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
////        strategy.setAppChannel(AppUtils.getAppChannel(this));
//        strategy.setAppVersion(AppUtils.getVersionName(this));
//        CrashReport.initCrashReport(this, "900010286", BuildConfig.DEBUG, strategy);
//
//        setUncaughtExcept();//全局捕获异常
//
//    }


    private void setUncaughtExcept() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                try {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);

                    Field[] fileds = Build.class.getDeclaredFields();
                    for (Field filed : fileds) {

                        sw.write(filed.getName() + "--" + filed.get(null) + "\n");
                    }
                    ex.printStackTrace(pw);
                    File file = new File(Environment.getExternalStorageDirectory(), "log.txt");
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(sw.toString().getBytes());
                    fos.close();
                    pw.close();
                    sw.close();

                    android.os.Process.killProcess(android.os.Process.myPid());

                } catch (Exception e) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    e.printStackTrace();
                }
            }
        });

    }

    public static void showToast(View view,String string){
        if(view != null && !TextUtils.isEmpty(string)) {

        }
    }


    public static void showToast(Context context,String string){
        if(!TextUtils.isEmpty(string)) {

        }
    }

    public static BaseApplication getApplication() {
        return mInstance;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    /**
     * volley 请求对列
     *
     * @return
     */


    /**
     * 判断程序是否运行
     *
     * @param processName
     * @return
     */
    private boolean checkIfIsAppRunning(String processName) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> mRunningList = am.getRunningAppProcesses();
        Iterator<RunningAppProcessInfo> iterator = mRunningList.iterator();
        while (iterator.hasNext()) {
            RunningAppProcessInfo info = (RunningAppProcessInfo) iterator.next();
            if (info.pid == pid) {
                if (processName.equals(info.processName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
