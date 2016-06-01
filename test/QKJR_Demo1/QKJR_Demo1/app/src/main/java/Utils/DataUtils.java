package Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import business.LogActivity;

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
    public static String area="area";
    public static String room="room";

    public  static String bankcardId="bank_card";
    public static String bankName="bank_name";



    public static String info1HasDone="info_done1";
    public static String info2HasDone="info_done2";
    public static String info3HasDone="info_done3";


    public static String relativeHomePhone1="relative_home_phone1";
    public static String relativeName1="relative_name1";
    public static String relativeMobilePhone1="relative_mobile_phone1";


    public static String relativeHomePhone2="relative_home_phone2";
    public static String relativeName2="relative_name2";
    public static String relativeMobilePhone2="relative_mobile_phone2";

    public static String relative1="relative1";
    public static String relative2="relative2";

//    public static String
    public static String unitType="unit_type";
    public static String hireType="hire_type";
    public static String oprateTime="oprate_time";

    public static String unitName="unit_name";
    public static String unitAddress="unit_address";
    public static String unitDepartment="unit_department";

    public static String unitScope="unit_scope";
    public static String unitPhone="unit_phone";

    public static String loanAmount="loan_amount";

    public static String loanTimes="loan_times";
    public static String repayOnce="repay_once";
    public static String loanTimesExtra="extra_loan_times";//剩余还款期数**********************************************
    public static String loanCountNum="loan_count_num";//账单号唯一标识账单**********************************
    public static String TemporaryData="temprorary";//临时存储数据*************************

    public static String loanDateTime="loan_date_time";//申请日期************************
    public static String loanTimeCount="loan_time_count";//每个申请记录多对应的倒计时计时器
    public static String state="state";//账户状态


    /**
     * 存储完成的还款记录
     * @param context
     * @param list
     * @param account
     */
    public static void putRepayHistoryRecentRecord(Context context,ArrayList<HashMap<String, Object>> list,String account){
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String arrayValue=utils.getString(account+"repayhistory");
        if(arrayValue==null||arrayValue.length()==0){
            JSONArray array=new JSONArray();
            for(HashMap<String ,Object> map:list){
                JSONObject object=new JSONObject();
                Set<String> keys=map.keySet();
                for(String key:keys){
                    try {
                        object.put(key, map.get(key));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                array.put(object);
            }
            utils.putString(account+"repayhistory",array.toString());
            utils.commit();
        }
        else{

            JSONArray array= null;
            try {
                array = new JSONArray(arrayValue);
                for(HashMap<String ,Object> map:list){
                    JSONObject object=new JSONObject();
                    Set<String> keys=map.keySet();
                    for(String key:keys){
                        object.put(key, map.get(key));
                    }
                    array.put(object);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            utils.putString(account+"repayhistory",array.toString());
            utils.commit();
        }
    }

    /**
     * 得到还款成功的交易记录
     * @param context
     * @param account
     * @return
     */

    public static JSONArray getRepayHistoryRecentRecord(Context context,String account)  {
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String arrayValue=utils.getString(account+"repayhistory");
        if(arrayValue==null||arrayValue.length()==0)
            return null;
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(arrayValue);
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }

    /**
     * 得到还款记录中的某个记录的jsonobject对象
     * @param context
     * @param account
     * @param position
     * @return
     */
    public  static JSONObject getJSONObject(Context context,String account,int position ){
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String value=utils.getString(account+"loan");
        JSONObject jsonObject=null;
        if(value==null||value.length()==0)
            return null ;
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(value);
            jsonObject= (JSONObject) jsonArray.get(position);
        }
        catch (JSONException e){
            e.printStackTrace();
            return null ;
        }
        return jsonObject;
    }




    /**
     * 存储临时账单数据
     * @param context
     * @param map
     */
    public static  void saveTempraroyDateToShared(Context context,HashMap<String, Object> map){
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String mapValue=utils.getString(TemporaryData);
        if(mapValue==null||mapValue.length()==0) {
            JSONObject jsonObject=new JSONObject();
            Set<String> keys=map.keySet();
            for(String key:keys){
                try {
                    jsonObject.put(key,map.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            utils.putString(TemporaryData,jsonObject.toString());
            utils.commit();
        }
        else{
            JSONObject jsonObject1= null;
            try {
                jsonObject1 = new JSONObject(mapValue);
                Set<String> keys=map.keySet();
                for(String key:keys){
                    jsonObject1.put(key,map.get(key));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            utils.putString(TemporaryData,jsonObject1.toString());
            utils.commit();
        }

    }

    /**
     * 得到临时账单数据的jsonObject对象
     * @param context
     * @return
     */
    public static JSONObject getJsonObjectTempteproraryData(Context context){
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String value=utils.getString(TemporaryData);
        if(value==null||value.length()==0){
            return null;
        }
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(value);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return  jsonObject;
    }

    public static JSONArray getJsonArrayFromShared(Context context,String account)  {
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String arrayValue=utils.getString(account+"loan");
        if(arrayValue==null||arrayValue.length()==0)
            return null;
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(arrayValue);
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return jsonArray;
    }

    /**
     *
     * @param context
     * @param list
     * @param account
     * @throws JSONException
     */
    public static void putJsonArrayToShared(Context context,ArrayList<HashMap<String, Object>> list,String account) throws JSONException {

        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String arrayValue=utils.getString(account+"loan");

        if(arrayValue==null||arrayValue.length()==0){
            JSONArray array=new JSONArray();
            for(HashMap<String ,Object> map:list){
                JSONObject object=new JSONObject();
                Set<String> keys=map.keySet();
                for(String key:keys){
                    object.put(key, map.get(key));
                }
                array.put(object);
            }
            utils.putString(account+"loan",array.toString());
            utils.commit();
        }
        else{

            JSONArray array=new JSONArray(arrayValue);
            for(HashMap<String ,Object> map:list){
                JSONObject object=new JSONObject();
                Set<String> keys=map.keySet();
                for(String key:keys){
                    object.put(key, map.get(key));
                }
                array.put(object);
            }
            utils.putString(account+"loan",array.toString());
            utils.commit();
        }

    }


    /**
     *
     * @param context
     * @param account
     * @return
     * @throws JSONException
     */
    public static ArrayList<HashMap<String,Object>> getListFromShared(Context context,String account) throws JSONException {

        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String arrayValue=utils.getString(account+"loan");

        if(arrayValue!=null&&arrayValue.length()!=0){
            JSONArray array=new JSONArray(arrayValue);
            HashMap<String,Object> map;
            ArrayList<HashMap<String,Object>> list=new ArrayList<>();

            for(int i=0;i<array.length();i++){
                int count=Integer.parseInt(array.getJSONObject(i).getString(DataUtils.loanTimes));//总期数
                int countExtra=Integer.parseInt(array.getJSONObject(i).getString(DataUtils.loanTimesExtra));//剩余期数
                int moneyEvery=Integer.parseInt(array.getJSONObject(i).getString(DataUtils.repayOnce));//每期多钱
                String date=array.getJSONObject(i).getString(DataUtils.loanDateTime);//申请日期
                String id=array.getJSONObject(i).getString(DataUtils.loanCountNum);//单号
                int countMoney=Integer.parseInt(array.getJSONObject(i).getString(DataUtils.loanAmount));


                if(count-countExtra>0) {
                    for(int j=0;j<count-countExtra;j++) {
                        map = new HashMap<>();
                        map.put("count", j+1);
                        map.put("amount", countMoney);
                        map.put("id", id);
                        map.put("moneyevery", moneyEvery);
                        map.put("date", date);
                        LogUtils.logD(map.toString());
                        list.add(map);
                    }
                }
                else{
                    continue;
                }
            }
            return list;
        }
        else{
            return null;//无纪录
        }
    }



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

    /**
     * 可以适用于简单的插入各种类型的用户的信息.
     * @param context
     * @param account
     * @param key
     * @param value
     * @throws JSONException
     */
    public static void insert(Context context,String account, String key,Object value) throws JSONException {

        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String info=utils.getString(account);
        LogUtils.logD("info:"+info);
        JSONObject infoJson=new JSONObject(info);

        infoJson.put(key,value);
        utils.putString(account, infoJson.toString());
        utils.commit();
        utils=new SharePreferenceUtils(context,shareName);
        info=utils.getString(account);
        LogUtils.logD("infoafter:" + info);
    }

    /**
     * 信息是否完善.
     * @param context
     * @return
     * @throws JSONException
     */
    public static boolean getInfoDone(Context context,String infoKey) throws JSONException {
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String info=utils.getString(getCurrentUser(context));
        JSONObject infoJson=new JSONObject(info);
        Iterator keys=infoJson.keys();
        boolean result=false;
        while((keys.hasNext())){
            String key= (String) keys.next();
            LogUtils.logD("key"+key);
            if(key.compareTo(infoKey)==0){
                result=infoJson.getBoolean(key);
                break;
            }
        }
        return result;

    }

    public static Object get(Context context,String infoKey) throws JSONException {
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String info=utils.getString(getCurrentUser(context));
        LogUtils.logD(info);
        JSONObject infoJson=new JSONObject(info);
        LogUtils.logD("" + infoJson.get(infoKey));
        return infoJson.get(infoKey);
    }

    /**
     * JSONObject转hashmap*************************************************************
     * @param context
     * @param jsonObject
     * @return
     */
    public static HashMap<String,Object> JSONObjToHashMap(Context context,JSONObject jsonObject){
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (jsonObject!=null)
        {
            for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext(); ) {
                String key = iterator.next();
                try {
                    map.put(key, jsonObject.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return map;
        }
        else {
            return null;
        }
    }

    /**
     * 插入单个的value到sharedPreference的贷款记录中*************************************
     * @param context
     * @param account
     * @param key
     * @param value
     */
    public static void putSimpleJSONValueToShared(Context context,String account,String key,String value,int position){
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String arrayValue=utils.getString(account+"loan");
        if(arrayValue==null||arrayValue.length()==0)
            return ;
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(arrayValue);
            JSONObject jsonObject= (JSONObject) jsonArray.get(position);
            jsonObject.put(key,value);
        }
        catch (JSONException e){
            e.printStackTrace();
            return ;
        }
        utils.putString(account+"loan",jsonArray.toString());
        utils.commit();
    }

    /**'
     * 删除JSONArray中的positon位置的jsonobject对象******************************
     * @param context
     * @param acount
     * @param position
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void removeJSONObjectFromJSONArray(Context context,String acount,int position){
        SharePreferenceUtils utils=new SharePreferenceUtils(context,shareName);
        String arrayValue=utils.getString(acount+"loan");
        if(arrayValue==null||arrayValue.length()==0){
            return ;
        }
        JSONArray jsonArray=null;
        try{
            jsonArray=new JSONArray(arrayValue);
            jsonArray.remove(position);
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        utils.putString(acount+"loan",jsonArray.toString());
        utils.commit();
    }
    /**
     * JSONArray转ArrayList*************************************************************
     * @param context
     * @param array
     * @return
     */
    public static ArrayList<HashMap<String,Object>> JSONOArrayToArrayList(Context context,JSONArray array) {
        ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
        if (array != null) {
            for(int i=0;i<array.length();i++)
            {
                HashMap<String,Object> map=new HashMap<String, Object>() ;
                try {
                    LogUtils.logD(""+array.get(i).toString());
                    JSONObject jsonObject= (JSONObject) array.get(i);
                    for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext();) {
                        String key = iterator.next();
                        map.put(key, jsonObject.get(key));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
                list.add(map);
            }
            return list;
        }
        else
        {
            return null;
        }
    }



}
