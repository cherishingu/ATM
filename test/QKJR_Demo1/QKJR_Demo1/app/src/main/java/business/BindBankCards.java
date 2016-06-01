package business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.zhuzhuxia.qkjr_demo1.R;

import org.json.JSONException;

import Utils.DataUtils;
import Utils.UIUtils;

/**
 * Created by zhuzhuxia on 16/5/11.
 */
public class BindBankCards extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        initViews();
        initAdapters();

    }
    private Button submitBtn;
    private EditText cardIdTv;
    private ImageView backImg;
    private View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.commit_btn:
                    try {
                        saveInfos();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finish();
                    Intent intent = new Intent(BindBankCards.this, BindCardFinishActivity.class);
                    startActivity(intent);
                    break;
                case R.id.back_card:
                    onBackPressed();
                    break;
            }
        }
    };
    private void initViews(){
        backImg= (ImageView) findViewById(R.id.back_card);
        submitBtn= (Button) findViewById(R.id.commit_btn);
        bankSpinner= (Spinner) findViewById(R.id.spinner);
        cardIdTv= (EditText) findViewById(R.id.cardnum_edit);
        submitBtn.setOnClickListener(listener);
        backImg.setOnClickListener(listener);

        LinearLayout llStatus = (LinearLayout) findViewById(R.id.ll_status_bar);
        UIUtils.setTranslateStatusBar(this, llStatus);
    }


    private Spinner bankSpinner;
    private String[] banks={"华夏银行"};
    private String bankName;
    private void initAdapters(){
        ArrayAdapter<String >adapter=new ArrayAdapter(BindBankCards.this,android.R.layout.simple_list_item_1,banks);
        bankSpinner.setAdapter(adapter);
    }



    private AdapterView.OnItemSelectedListener selListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            bankName=banks[position];
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

            bankName=banks[0];
        }
    };



    private void saveInfos() throws JSONException {
        DataUtils.insert(BindBankCards.this,DataUtils.getCurrentUser(BindBankCards.this),DataUtils.bankcardId,cardIdTv.getText().toString());

        if(bankName==null||bankName.length()==0) {
            DataUtils.insert(BindBankCards.this, DataUtils.getCurrentUser(BindBankCards.this), DataUtils.bankName,banks[0]);

        }
        else{
            DataUtils.insert(BindBankCards.this, DataUtils.getCurrentUser(BindBankCards.this), DataUtils.bankName,bankName);
        }
    }


}
