package Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.qkjr_demo2.R;

import Widgets.ActivityCollector;
import business.ApplyStatusActivity;
import business.LatestPayActivity;
import business.LogActivity;
import business.MyBillActivity;
import business.PersonalDetailActivity;

/**
 *我的信息
 * Created by wangfeng on 2016/5/23.
 */
public class FourFragment extends Fragment implements View.OnClickListener{
    private ImageView person_head;
    Button person_repay, person_apply, person_repay_record, person_information;
    private Button log_out;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_personal_center,container,false);
        ActivityCollector.addActivity(getActivity());
        initView(view);
        initEvents();
        return  view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    private void initEvents() {
        person_head.setOnClickListener(this);
        person_repay.setOnClickListener(this);
        person_apply.setOnClickListener(this);
        person_repay_record.setOnClickListener(this);
        person_information.setOnClickListener(this);
        log_out.setOnClickListener(this);
    }
    private void initView(View view) {
        person_head = (ImageView) view.findViewById(R.id.person_head);
        person_repay = (Button) view.findViewById(R.id.person_repay);
        person_apply = (Button) view.findViewById(R.id.person_apply);
        person_repay_record = (Button) view.findViewById(R.id.person_repay_record);
        person_information = (Button) view.findViewById(R.id.person_information);
        log_out = (Button) view.findViewById(R.id.log_out);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case  R.id.person_head:
                startActivity(new Intent(getActivity(), LogActivity.class));
                ActivityCollector.removeActivity(getActivity());
                break;
            case R.id.log_out:
                startActivity(new Intent(getActivity(), LogActivity.class));
                ActivityCollector.removeActivity(getActivity());
                break;
            case R.id.person_repay:
                startActivity(new Intent(getActivity(), LatestPayActivity.class));
                ActivityCollector.removeActivity(getActivity());
                break;
            case R.id.person_apply:
                startActivity(new Intent(getActivity(), ApplyStatusActivity.class));
                ActivityCollector.removeActivity(getActivity());
                break;
            case R.id.person_repay_record:
                startActivity(new Intent(getActivity(), MyBillActivity.class));
                ActivityCollector.removeActivity(getActivity());
                break;
            case R.id.person_information:
                startActivity(new Intent(getActivity(), PersonalDetailActivity.class));
                ActivityCollector.removeActivity(getActivity());
                break;
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        ActivityCollector.finishAll();
    }
}
