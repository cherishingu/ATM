package Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qkjr_demo2.R;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhuzhuxia on 16/5/12.
 * 用于操作数据库,全国行政单位的信息都存储在数据库.
 *
 */
public class DataBaseUtils {
    private File file;
    public DataBaseUtils(Context context) {
        MakeFileUtils utils=new MakeFileUtils(context);

        file=new File(utils.getRootPath()+"citydb");
        if(file.exists()){
            LogUtils.logD("数据库文件已经创建完毕");
        }
        else{
            InputStream in=context.getResources().openRawResource(R.raw.db_weather);
            utils.write("citydb", in);
            file=new File(utils.getRootPath()+"citydb");
        }

//        getProSet();
//        getCitSet(0);


    }

    /**
     * 得到所有的省份的id与name的集合的list
     * @return
     */
    public ArrayList<HashMap<String,Object>> getProSet(){
        //打开数据库

        SQLiteDatabase db1 = SQLiteDatabase.openOrCreateDatabase(file, null);
        Cursor cursor=db1.query("provinces", null, null, null, null, null, null);

        HashMap<String ,Object> map;
        ArrayList<HashMap<String ,Object>> proset = new ArrayList<>();

        while(cursor.moveToNext()){
            String pro=cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String pid=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
//            proset.add(pro);
            LogUtils.logD(pid + pro);
            map=new HashMap<>();
            map.put("pid", pid);
            map.put("pname",pro);
            proset.add(map);
        }
        cursor.close();
        db1.close();
        return proset;
    }

    /**
     * pro_id得到某个省的全部的城市.pro_id要比之前得到的省份id小于1;
     * @param pro_id
     * @return
     */
    public ArrayList<String> getCitSet(int pro_id){
        ArrayList<String> citset=new ArrayList<>();
        //打开数据库
        SQLiteDatabase db1 = SQLiteDatabase.openOrCreateDatabase(file, null);
        Cursor cursor=db1.query("citys", null, "province_id="+pro_id, null, null, null, null);
        while(cursor.moveToNext()){
            String city=cursor.getString(cursor.getColumnIndexOrThrow("name"));
            citset.add(city);
           // LogUtils.logD(city);
        }
        cursor.close();
        db1.close();
        return citset;
    }
}
