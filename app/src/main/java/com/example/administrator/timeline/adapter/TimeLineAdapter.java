package com.example.administrator.timeline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.administrator.timeline.R;
import com.example.administrator.timeline.common.MedicTimeLineView;
import com.example.administrator.timeline.common.bean.MedicDataBean;
import com.example.administrator.timeline.common.bean.MedicDayDataBean;
import com.example.administrator.timeline.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juwuguo on 2017/6/27.
 */

public class TimeLineAdapter extends BaseAdapter {

    private Context mContext;
    private List<MedicDataBean> datalist;
    private int daySize;
    private String startDay;

    public TimeLineAdapter(Context mContext, List<MedicDataBean> datalist, String startDay, int daySize) {
        this.mContext = mContext;
        this.datalist = datalist;
        this.startDay = startDay;
        this.daySize = daySize;
    }

    public void setData(List<MedicDataBean> datalist, String startDay, int daySize) {
        this.datalist = datalist;
        this.daySize = daySize;
        this.startDay = startDay;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewContentHolder vcholder;
        if (convertView == null) {
            vcholder = new ViewContentHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.progress_layout, null);
            vcholder.mtlv_view = (MedicTimeLineView) convertView.findViewById(R.id.mtlv_view);
            convertView.setTag(vcholder);
        } else {
            vcholder = (ViewContentHolder) convertView.getTag();
        }

        List<Integer> listday = new ArrayList<>();//一个月或者两周里面有数据的天数集合
        List<Integer> listSize = new ArrayList<>();//一个月或者两周里面有数据的天数大小
        List<String> listStatus = new ArrayList<>();//有数据的天数每天对应的三种状态
        List<Integer> listTag = new ArrayList<>();//每一天的开头结尾状态
        for (MedicDayDataBean mddBean : datalist.get(position).getMedicDayDataBean()) {
            listday.add((int) TimeUtils.getDiffFromDay(startDay, mddBean.getStartAt()));
            listSize.add(mddBean.getBeanSize());
            listStatus.add(mddBean.getStatus());
            listTag.add(mddBean.getTag());
        }
        vcholder.mtlv_view.setData(datalist.get(position).getMedicName(), listday,listSize, listStatus, listTag, daySize);
        return convertView;
    }

    static class ViewContentHolder {

        MedicTimeLineView mtlv_view;
    }

}
