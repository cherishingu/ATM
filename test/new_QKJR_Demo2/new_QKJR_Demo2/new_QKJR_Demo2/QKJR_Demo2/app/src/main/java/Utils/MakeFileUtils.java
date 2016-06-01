package Utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * Created by zhuzhuxia on 16/4/12.
 */
public class MakeFileUtils {
    private String rootPath;
    private Context context;

    public MakeFileUtils(Context context) {
        this.context = context;
        Log.v("TAG","MakeFile初始化...");
        makeRootPath();
    }

    private void makeRootPath() {
        rootPath =Environment.getExternalStorageDirectory().getPath().toString()+ "/QKJR/";
        File file = new File(rootPath);
        if (!file.exists()) {
            file.mkdir();
            Log.v("TAG","文件夹已经创建");
        }
    }

    public String getRootPath() {
        return rootPath;
    }

    public  void write(String fileName, InputStream is) {
        try {
            FileOutputStream fos = new FileOutputStream(rootPath + fileName);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String fileName,byte[] values){
        try {
            Log.v("TAG","values:"+values.toString());
            FileOutputStream out=new FileOutputStream(rootPath+fileName);
            out.write(values);
            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(String fileName,String content){

        File file=new File(getRootPath()+fileName);
        if(!file.exists()){
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            RandomAccessFile accessFile=new RandomAccessFile(file,"rw");
            accessFile.seek(0);
            accessFile.write(content.getBytes());
            accessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String  read(String filePath) {
        File file = new File(filePath);
        String content = "";
        if (file.exists() && file.isFile()) {
            try {
                InputStream in = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bufferReader = new BufferedReader(reader);
                String line;
                while ((line = bufferReader.readLine()) != null) {
                    content += line;
                }
                bufferReader.close();
                reader.close();
                in.close();

            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;//
    }

}
