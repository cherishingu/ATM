package Utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Created by zhuzhuxia on 16/5/18.
 */
public class DataUtils {
    public static String currentUser="current_user";
    public static String shareName="QKJR_DEMO1";
    public static String noUser="nothing";

//    account属性
    public static String account="account";
    public static String pwd="pwd";
    public static String online="online";
    public static String cardNum="card_num";
    public static String email="email";
    public static String eduBg="edu_bg";
    public static String marrige="marrige";
    public static String houseState="houseState";
    public static String province="province";
    public static String city="city";
    public static String street="street";
    public static String neighborhood="neighborhood";
    public static String unit="unit";

//    public static String


    /**
     * 验证某个账号的密码
     * @param context
     * @param account
     * @param pwd
     * @return
     * @throws JSONException
     */

    public static boolean vertify(Context context ,String account,String pwd) throws JSONException {
        SharePreferenceUtils utils=new SharePreferenceUtils(context, DataUtils.shareName);

        String info=utils.getString(account);
        LogUtils.logD(info);
        JSONObject infoJson=new JSONObject(info);
        String password=infoJson.getString(DataUtils.pwd);
        if(password.compareTo(pwd)==0){
            return true;
        }
        return false;
    }

    /**
     * 更新某个账号的在线状态
     * @param context
     * @param account
     * @param state
     * @throws JSONException
     */
    public static void updateState(Context context,String account,boolean state) throws JSONException {
        SharePreferenceUtils utils=new SharePreferenceUtils(context, shareName);
        String info=utils.getString(account);
        JSONObject infoJson=new JSONObject(info);
        infoJson.put(online,state);
        utils.commit();
    }

    /**
     * 得到某个账号在线状态
     * @param context
     * @param account
     * @return
     * @throws JSONException
     */
    public static boolean isOnline(Context context,String account) throws JSONException {
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String info=utils.getString(account);
        JSONObject infoJson=new JSONObject(info);
        return infoJson.getBoolean(online);
    }

    /**
     * 设置当前用户的账号
     * @param context
     * @param account
     * @throws JSONException
     */
    public static void updateUser(Context context,String account) throws JSONException {
        SharePreferenceUtils utils=new SharePreferenceUtils(context, shareName);
        utils.putString(currentUser,account);
        utils.commit();
    }

    /**
     * 得到当前的账户
     * @param context
     * @return
     */
    public static String getCurrentUser(Context context){
        SharePreferenceUtils utils=new SharePreferenceUtils(context, shareName);
        return utils.getString(currentUser);
    }


    /**
     * 退出当前账号
     * @param context
     * @throws JSONException
     */
    public static void offLine(Context context) throws JSONException {
        String account=getCurrentUser(context);
        updateState(context,account,false);
        updateUser(context,noUser);
    }


//    public static void initUserData(Context context,String account,String pwd) throws JSONException {
//        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put(DataUtils.account,account);
//        jsonObject.put(DataUtils.pwd,pwd);
//        jsonObject.put(DataUtils.cardNum, "");
//        jsonObject.put(DataUtils.email, "");
//    }

    /**
     * 以map的形式把用户的信息插入到用户json中
     * @param context
     * @param account
     * @param map
     * @throws JSONException
     */
    public static void insertInfos(Context context,String account,HashMap<String,String> map) throws JSONException {
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String info=utils.getString(account);
        JSONObject infoJson=new JSONObject(info);
        Set<String> keys=map.keySet();
        for(String key:keys){
            infoJson.put(key,map.get(key));
        }
        utils.putString(account,infoJson.toString());
        utils.commit();
    }
    public static String generate6Random(){
        StringBuilder numBuild=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<6;i++){
            numBuild.append(random.nextInt(10));
        }
        return numBuild.toString();
    }

}
