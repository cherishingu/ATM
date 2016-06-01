package Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by zhuzhuxia on 16/5/12.
 * 用于处理sharepreference.
 */
public class SharePreferenceUtils {


    private SharedPreferences sharedPreferences;
    private Editor editor;

    public SharePreferenceUtils(Context context, String fileName){
        sharedPreferences=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public void putString(String key,String value){
        editor.putString(key,value);
    }

    /**
     * put一个map,把map里面的每一个string都存储到本地.
     * @param map
     */
    public void putStringMap(HashMap<String,String> map){
        Set<String> keys=map.keySet();
        for(String key:keys){
            putString(key,map.get(key).toString());
        }
    }

    public String getString(String key){
        return sharedPreferences.getString(key,"");
    }

    /**
     * 本类的put操作之后,都要进行commit.
     */
    public void commit(){
        editor.commit();
    }
}
