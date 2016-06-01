package Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhuzhuxia.qkjr_demo1.R;

import java.util.List;
import java.util.Map;

import Utils.DataUtils;
import business.Repay1Activity;
import business.SimpleApplication;

/**
 * Created by jin on 2016/5/20.
 */
public class MyHistoryListAdapter extends BaseAdapter {
    class viewHolder{
        TextView tableList;
        Button repayNowBtn;
        TextView historyDetail;
    }
    int sum=0;
    private  Context context;
    private  List list;
    private  Activity activity;
   private LayoutInflater container;
    public MyHistoryListAdapter(Context context, List list, Activity activity) {
        this.context=context;
        this.list=list;
        this.activity=activity;
        container=LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        viewHolder listitem=null;
        if (convertView==null) {
             listitem=new viewHolder();
             convertView=container.inflate(R.layout.repay_history_listitem,null);
            listitem.tableList=(TextView)convertView.findViewById(R.id.tablelist);
           listitem.historyDetail=(TextView)convertView.findViewById(R.id.history_detail);
            listitem.repayNowBtn=(Button)convertView.findViewById(R.id.repay_now_btn);
            convertView.setTag(listitem);
        } else {
            listitem=(viewHolder)convertView.getTag();
        }
        listitem.repayNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position1 = position;
                String currentUser = DataUtils.getCurrentUser(activity);

                Bundle data = new Bundle();
                Intent intent = new Intent(activity, Repay1Activity.class);
                data.putString(DataUtils.repayOnce, ((Map<String, Object>) list.get(position1)).get(DataUtils.repayOnce).toString());
                data.putInt("position", position);
                data.putString(DataUtils.loanTimesExtra,((Map<String,Object>)list.get(position)).get(DataUtils.loanTimesExtra).toString());
                intent.putExtra("data", data);
                activity.startActivity(intent);
            }
        });
        listitem.tableList.setText("账单" +(position+1));
//            DataUtils.putSimpleJSONValueToShared(context,DataUtils.getCurrentUser(activity),DataUtils.loanTimesExtra,"1111",position);


        listitem.historyDetail.setText("单号:" + ((Map<String, Object>) list.get(position)).get(DataUtils.loanCountNum) + "  贷款总额:" + ((Map<String, Object>) list.get(position)).get(DataUtils.loanAmount) + "  期数:" + ((Map<String, Object>) list.get(position)).get(DataUtils.loanTimes) + "  本期还款金额:" + ((Map<String, Object>) list.get(position)).get(DataUtils.repayOnce) + "  剩余还款期数:" + ((Map<String, Object>) list.get(position)).get(DataUtils.loanTimesExtra));

//            listitem.repayNowBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context,"还款成功",Toast.LENGTH_SHORT);
//                }
//            });
        return convertView;
    }
}
