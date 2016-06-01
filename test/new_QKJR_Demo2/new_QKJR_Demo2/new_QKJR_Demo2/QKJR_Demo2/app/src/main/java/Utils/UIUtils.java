package Utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Field;

import business.BaseApplication;


/**
 * Created by zhangshixin on 2015/11/26.
 * Blog : http://blog.csdn.net/u011240877
 *
 * @description Codes there always can be better.
 */
public class UIUtils {
    public static BaseApplication getApplication() {
        return BaseApplication.getApplication();
    }

    public static Thread getMainThread() {
        return BaseApplication.getMainThread();
    }

    public static long getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    public static Toast toast;

    public static final boolean isEmail(String value) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches();
    }

    public static String getVersion(Context context)// 获取版本号
    {
        PackageInfo pi = null;
        try {
            pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pi.versionName;
    }
/*
    public static void setImageUrl(Context context, String imageUrl, ImageView view) {
        Picasso.with(context).load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(view);
    }

    public static void setImageUrl(Context context, String imageUrl, Target target) {
        Picasso.with(context).load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(target);
    }
*/
    /**
     * dip转换px
     */
    public static int dip2px(Context context, int dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        return BaseApplication.getMainThreadHandler();
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static View inflate(Context context, int resId) {
        return LayoutInflater.from(context).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(Context context, int resId) {
        return getResources(context).getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(Context context, int resId) {
        return getResources(context).getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(Context context, int resId) {
        return getResources(context).getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(Context context, int resId) {
        return getResources(context).getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(Context context, int resId) {
        return getResources(context).getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(Context context, int resId) {
        return getResources(context).getColorStateList(resId);
    }

    //判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * 用Snackbar显示toast
     * @param view
     * @param string
     */
    public static void showSnackToast(View view,String string){
        if(view != null && !TextUtils.isEmpty(string)) {
            Snackbar.make(view, string, Snackbar.LENGTH_SHORT).show();
        }
    }


    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(Context context, final int resId) {
        showToastSafe(context, getString(context, resId));
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final Context context, final String str) {
        if (isRunInMainThread()) {
            showToast(context, str);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(context, str);
                }
            });
        }
    }

    private static void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }



    /**
     * 取消NavigationView的menu的scrollbar
     * @param navigationView
     */
    public static void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    /**
     * 利用官方提供的RoundedBitmapDrawable设置图片圆角
     * @param context
     * @param imageView
     * @param resId
     */
    public static void setImageRoundCorner(Context context, ImageView imageView, int resId){
        Resources resources = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resId);
        Bitmap dst;
        //将长方形图片裁剪成正方形图片
        if (bitmap.getWidth() >= bitmap.getHeight()){
            dst = Bitmap.createBitmap(bitmap, bitmap.getWidth()/2 - bitmap.getHeight()/2, 0, bitmap.getHeight(), bitmap.getHeight()
            );
        }else{
            dst = Bitmap.createBitmap(bitmap, 0, bitmap.getHeight()/2 - bitmap.getWidth()/2, bitmap.getWidth(), bitmap.getWidth()
            );
        }
        RoundedBitmapDrawable rbDrawable = RoundedBitmapDrawableFactory.create(resources, dst);
        rbDrawable.setCircular(true);

        imageView.setImageDrawable(rbDrawable);

    }

    /**
     * 设置透明状态栏
     * @param activity
     * @param linearLayout
     */
    public static void setTranslateStatusBar(Activity activity,LinearLayout linearLayout){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            linearLayout.setVisibility(View.VISIBLE);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          //  activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                int statusHeight = getResources(activity).getDimensionPixelSize(x);
//                LogUtils.e("getStatusBarHeight(px) "," "+statusHeight);

                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
                params.height = statusHeight;
                linearLayout.setLayoutParams(params);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
    }

    public static void addUnderLine(TextView view){
        view.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG ); //下划线
        view.getPaint().setAntiAlias(true);//抗锯齿
    }
    /**
     * 对该View进行抖动动画
     * @param view
     */

//    /**
//     * 高斯模糊
//     *
//     * @param ctx
//     * @param view
//     * @param textView
//     */
//    public static void ninePatchDrawable(final Context ctx, final ImageView view, final TextView textView) {
//        Drawable drawable = view.getDrawable();
//        if (drawable instanceof BitmapDrawable) {
//            synchronized (UIUtils.class) {
//
//                view.setImageBitmap(Blur.apply(ctx, ((BitmapDrawable) view.getDrawable()).getBitmap()));
//            }
//
//
//        } else if (drawable instanceof NinePatchDrawable) {
//            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//            drawable.draw(canvas);
//            view.setImageBitmap(Blur.apply(ctx, bitmap));
//        }
//        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//
//            @Override
//            public boolean onPreDraw() {
//                view.getViewTreeObserver().removeOnPreDrawListener(this);
//                view.buildDrawingCache();
//                Bitmap bmp = view.getDrawingCache();
//                ViewUtils.blur(ctx, bmp, textView);
//                return true;
//            }
//        });
//
//    }
}


