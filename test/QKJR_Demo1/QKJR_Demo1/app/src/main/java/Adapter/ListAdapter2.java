package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Utils.DataUtils;
import Utils.LogUtils;

/**
 * Created by zhuzhuxia on 16/5/24.
 */
public class ListAdapter2 extends BaseAdapter {

    private ArrayList<HashMap<String,Object>> list=new ArrayList<>();

    private Context context;

    public ListAdapter2(Context context, ArrayList<HashMap<String, Object>> list) {
        this.context=context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private TextView danhaoTv,counteveryTv,jiqiTv,dateTv;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.listview_item2, null);

        counteveryTv= (TextView) view.findViewById(R.id.count);
        jiqiTv= (TextView) view.findViewById(R.id.repay_jiqi);
        danhaoTv= (TextView) view.findViewById(R.id.danhao);
        dateTv= (TextView) view.findViewById(R.id.apply_date3);

        HashMap<String,Object> map=list.get(position);
        dateTv.setText((String) map.get("date"));
        jiqiTv.setText(""+map.get("count"));
        danhaoTv.setText(((String)""+map.get("id")).substring(8,14));
        counteveryTv.setText(""+map.get("moneyevery"));
        return view;
    }
}
