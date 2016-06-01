package Widgets;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhuzhuxia on 16/5/13.
 */
public class SimpleAdapterForQk extends BaseAdapter {
    private ArrayList<String> list;
    private Context context;
    public SimpleAdapterForQk(Context context,ArrayList<String> list){
        this.list=list;
        this.context=context;
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

    private TextView textView;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.item_simple_list, null);
        textView=(TextView)view.findViewById(R.id.simple_list_text);
        textView.setText(list.get(position));
        return view;
    }
}
