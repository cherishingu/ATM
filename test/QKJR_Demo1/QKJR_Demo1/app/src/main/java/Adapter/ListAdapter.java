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

import static Utils.DataUtils.loanTimesExtra;

/**
 * Created by zhuzhuxia on 16/5/24.
 */
public class ListAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,Object>> list=new ArrayList<>();

    private Context context;
    private static String amount="amount",repayYear="repay_year",repayMonth="repay_month",repayDeadLine="dead_line";

    public ListAdapter(Context context, ArrayList<HashMap<String, Object>> list) {


        this.context=context;

        this.list.clear();

        LogUtils.logD(""+list.toString());

        for(HashMap<String,Object> map:list){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date= null;//获取日期
            try {
                LogUtils.logD("日期"+map.get(DataUtils.loanCountNum));
                date = sdf.parse((String) map.get(DataUtils.loanCountNum));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
            LogUtils.logD("年"+date.getYear());
            int year=date.getYear()+1900;
            int month=date.getMonth()+1;
            int day=date.getDay();
            String repayNum = (String) map.get(DataUtils.repayOnce);// 每期还钱;

            int max=Integer.parseInt((String)map.get(DataUtils.loanTimesExtra));
            HashMap<String ,Object> m;
            for(int i=0;i<max;i++){//剩余期数;

                month++;
                if(month>12){
                    month=1;
                    year++;
                }
                m=new HashMap<>();
                LogUtils.logD(""+month+" "+year);
                m.put(ListAdapter.amount,repayNum);
                m.put(ListAdapter.repayYear,year);
                m.put(ListAdapter.repayMonth,month);
                m.put(ListAdapter.repayDeadLine,10);
                this.list.add(m);
            }
        }
        LogUtils.logD(this.list.toString());
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

    private TextView amountTv,repayYearTv,repayMonthTv,repayDeadlineTv;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.listview_item, null);

        amountTv= (TextView) view.findViewById(R.id.month_amount);
        repayDeadlineTv = (TextView) view.findViewById(R.id.repay_deadline);
        repayYearTv = (TextView) view.findViewById(R.id.repay_year);
        repayMonthTv = (TextView) view.findViewById(R.id.repay_month);
        HashMap<String,Object> map=list.get(position);


        LogUtils.logD(""+map.get(ListAdapter.amount)+" "+map.get(ListAdapter.repayYear)
                +map.get(ListAdapter.repayMonth)+map.get(ListAdapter.repayDeadLine));
        amountTv.setText(""+map.get(ListAdapter.amount));
        repayYearTv.setText(""+map.get(ListAdapter.repayYear));
        repayMonthTv.setText(""+map.get(ListAdapter.repayMonth));
        repayDeadlineTv.setText(""+map.get(ListAdapter.repayDeadLine));


        return view;
    }
}
