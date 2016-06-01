package business;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.qkjr_demo2.MainActivity;
import com.qkjr_demo2.R;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import Utils.SharePreferenceUtils;
import Widgets.ActivityCollector;

/**
 * 我的信息
 * Created by Administrator on 2016/5/24.
 */

public class PersonalDetailActivity extends Activity implements View.OnClickListener {
    ////////////////////////////////////////////////////////////////
    final String FILE_NAME = "chen.bin";/////////////////////////////////////////
    public static boolean flagP = false;
    public static boolean flag3_8 = false;
    private int flags[]= new int[]{0,1,2};


    private SharePreferenceUtils personalInformation;
    public static int width,height;
    ///////////////////////////////////////////////////////////////////////////
    private ImageView personBack,img_pos,img_ops,img_hand;
    private Button personSave;
    public  static String flag;
    private EditText personalName, personalId, personalPhone, personalContact, personalAddress, workAddress,
            personalDepartment, companyAddress, creditCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);
        ActivityCollector.addActivity(this);
        initView();
        initEvents();
    }
    public void initView(){
        personBack = (ImageView)findViewById(R.id.personal_detail_back);
        personSave = (Button)findViewById(R.id.person_detail_save);
        //////////////////////////////////////////////////////////////////////
        personalName = (EditText)findViewById(R.id.personal_name);
        personalId = (EditText)findViewById(R.id.personal_id);
        personalPhone = (EditText)findViewById(R.id.personal_phone);
        personalContact = (EditText)findViewById(R.id.personal_contact);
        personalAddress = (EditText)findViewById(R.id.personal_address);
        workAddress = (EditText)findViewById(R.id.personal_work_address);
        personalDepartment = (EditText)findViewById(R.id.personal_department);
        companyAddress = (EditText)findViewById(R.id.personal_company_address);
        creditCard = (EditText)findViewById(R.id.personal_credit_card);
        img_pos=(ImageView )findViewById(R.id.personal_credit_img1);
        img_ops=(ImageView )findViewById(R.id.personal_credit_img2);
        img_hand=(ImageView )findViewById(R.id.personal_credit_img3);
        img_hand.setImageBitmap(PhotosActivity.img3);
        img_pos.setImageBitmap(PhotosActivity.img1);
        img_ops.setImageBitmap(PhotosActivity.img2);
        width = img_hand.getWidth();
        height = img_hand.getHeight();
//        show();

    }

    private void show() {
        if(flag.equals("upload")){
            img_ops.setImageBitmap(getInsertImg(PhotosActivity.imgpath));
        }
    }

        private Bitmap getInsertImg(String path) {
      File file = new File(path);
       if(!file.exists()){
           return null;
       }
       return BitmapFactory.decodeFile(path);


            }
    public void initEvents(){
        personBack.setOnClickListener(this);
        personSave.setOnClickListener(this);
        //////////////////////////////////////////////////////////////////////
        personalName.setOnClickListener(this);
        personalAddress.setOnClickListener(this);
        personalDepartment.setOnClickListener(this);
        personalContact.setOnClickListener(this);
        personalPhone.setOnClickListener(this);
        personalId.setOnClickListener(this);
        creditCard.setOnClickListener(this);
        companyAddress.setOnClickListener(this);
        workAddress.setOnClickListener(this);
        img_pos.setOnClickListener(this);
        img_hand.setOnClickListener(this);
        img_ops.setOnClickListener(this);
        if (flagP == true){
            SharePreferenceUtils sharePreferenceUtils = new SharePreferenceUtils(PersonalDetailActivity.this, "personalDetailInformation");
            personalName.setText(sharePreferenceUtils.getString("pName")+"");
            personalAddress.setText(sharePreferenceUtils.getString("pAddress")+"");
            personalDepartment.setText(sharePreferenceUtils.getString("pDepartment")+"");
            personalContact.setText(sharePreferenceUtils.getString("pContact")+"");
            personalPhone.setText(sharePreferenceUtils.getString("pPhone")+"");
            personalId.setText(sharePreferenceUtils.getString("pId")+"");
            creditCard.setText(sharePreferenceUtils.getString("pCard")+"");
            companyAddress.setText(sharePreferenceUtils.getString("pCompany")+"");
            workAddress.setText(sharePreferenceUtils.getString("pWork")+"");
        }
       /* if (flagP == true){//文件存储方式
            personalName.setText(read());

            personalId.setText(read());

            personalPhone.setText(read());

            personalContact.setText(read());

            personalAddress.setText(read());

            workAddress.setText(read());

            personalDepartment.setText(read());

            companyAddress.setText(read());

            creditCard.setText(read());
        }*/
    }

    /**
     * 数据存储
     */
    /////////////////////////////////////////////////////////////////////////////////////
    //文件存储方式
 /*   private void write(String content) {
        try{
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
            PrintStream ps = new PrintStream(fos);
            ps.println(content);
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private String read(){
        try{
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder("");
            while((hasRead = fis.read(buff)) > 0){
                sb.append(new String(buff, 0, hasRead));
            }
            fis.close();
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private void get(){
        write(personalName.getText().toString());
        personalName.setText("");
        write(personalId.getText().toString());
        personalId.setText("");
        write(personalPhone.getText().toString());
        personalPhone.setText("");
        write(personalContact.getText().toString());
        personalContact.setText("");
        write(personalAddress.getText().toString());
        personalAddress.setText("");
        write(workAddress.getText().toString());
        workAddress.setText("");
        write(personalDepartment.getText().toString());
        personalDepartment.setText("");
        write(companyAddress.getText().toString());
        companyAddress.setText("");
        write(creditCard.getText().toString());
        creditCard.setText("");
    }*/
    /////////////////////////////////////////////////////////////////////////////////
   private void getInfo(){
        personalInformation = new SharePreferenceUtils(PersonalDetailActivity.this, "personalDetailInformation");
        String name = personalName.getText().toString();
        String id = personalId.getText().toString();
        String address = personalAddress.getText().toString();
        String phone = personalPhone.getText().toString();
        String department = personalDepartment.getText().toString();
        String card = creditCard.getText().toString();
        String company = companyAddress.getText().toString();
        String work = workAddress.getText().toString();
        String contact = personalContact.getText().toString();

        personalInformation.putString("pName", name);
        personalInformation.putString("pId", id);
        personalInformation.putString("pAddress", address);
        personalInformation.putString("pPhone", phone);
        personalInformation.putString("pDepartment", department);
        personalInformation.putString("pCard", card);
        personalInformation.putString("pCompany", company);
        personalInformation.putString("pWork", work);
        personalInformation.putString("pContact", contact);

        personalInformation.commit();
    }
    /**************************************************************************/
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.personal_detail_back:
                flag3_8 = true;
                startActivity(new Intent(PersonalDetailActivity.this, MainActivity.class));
                ActivityCollector.finishAll();
                break;
            case R.id.person_detail_save:
                /****************************************************/
                getInfo();
                flagP = true;
                Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PersonalDetailActivity.this, CreditConfirmActivity.class));
                break;
            case R.id.personal_credit_img1:
                PhotosActivity.flag = flags[0];
                startActivity(new Intent(PersonalDetailActivity.this,PhotosActivity.class));

                break;
            case R.id.personal_credit_img2:
                PhotosActivity.flag = flags[1];
                startActivity(new Intent(PersonalDetailActivity.this,PhotosActivity.class));
                break;
            case R.id.personal_credit_img3:
                PhotosActivity.flag = flags[2];
                startActivity(new Intent(PersonalDetailActivity.this,PhotosActivity.class));
                break;
        }
    }
}
