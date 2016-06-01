package Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qkjr_demo2.R;

import business.ApplyReturnAcitvity;

/**
 * 我的订单
 * Created by wangfeng on 2016/5/23.
 */
public class ThreeFragment extends Fragment implements View.OnClickListener{
    private Button btn_myorder1,btn_myorder2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order,container,false);
        initView(view);
        initView();
        return view;
    }

    private void initView() {
        btn_myorder1.setOnClickListener(this);
        btn_myorder2.setOnClickListener(this);
    }

    private void initView(View view) {
        btn_myorder1 = (Button)view.findViewById(R.id.myorder_apply);
        btn_myorder2 = (Button)view.findViewById(R.id.myorder_apply2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case  R.id.myorder_apply:
                startActivity(new Intent(getActivity(), ApplyReturnAcitvity.class));
                break;
            case R.id.myorder_apply2:
                startActivity(new Intent(getActivity(), ApplyReturnAcitvity.class));
                break;
        }

    }
}
