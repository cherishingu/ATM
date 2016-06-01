package business;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.qkjr_demo2.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class PhotosActivity  extends Activity {
    public static Bitmap img1, img2, img3;
    public static String URI1,URI2,URI3;
    private Button btn_img;
    //    int i = 0;
    public static int flag;
    public static String imgpath;
    private String path = "/mnt/sdcard";
   private String send_flag = "upload";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        btn_img = (Button) findViewById(R.id.btn_img_choose);
        Button button = (Button) findViewById(R.id.b01);
        button.setText("选择图片");
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               PersonalDetailActivity.flag = send_flag;
                startActivity(new Intent(PhotosActivity.this, PersonalDetailActivity.class));

            }
        });
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            System.out.println("++++++++++++" + uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 220, 220, true);
                if (flag == 0) {
                   img1 = bitmap1;
                    String img_name = "img0";
                    URI1 =  uri.toString();
//                     saveFile(URI1,path+img_name);
//                    saveBitmap(img1,img_name);
                }
                if (flag == 1) {
                    img2 = bitmap1;
                    String img_name = "img1";
//                    saveBitmap(img2,img_name);
                }
                if (flag == 2) {
                    img3 = bitmap1;
                    String img_name = "img2";
//                    saveBitmap(img3,img_name);
                }

                ImageView imageView = (ImageView) findViewById(R.id.iv01);
                /* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 　　* 保存文件
     * 　　* @param toSaveString
     * 　　* @param filePath
     *
//     */
    public void saveFile(String toSaveString, String filePath) throws IOException {

        File saveFile = new File(filePath);
        imgpath =filePath;
        if (!saveFile.exists()) {
            File dir = new File(saveFile.getParent());
            dir.mkdirs();
            saveFile.createNewFile();
        }

        FileOutputStream outStream = new FileOutputStream(saveFile);
        outStream.write(toSaveString.getBytes());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++文件已经保存");
        outStream.close();

    }

}