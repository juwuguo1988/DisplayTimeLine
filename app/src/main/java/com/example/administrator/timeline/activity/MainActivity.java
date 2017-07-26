package com.example.administrator.timeline.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.timeline.R;
import com.example.administrator.timeline.adapter.TimeLineAdapter;
import com.example.administrator.timeline.common.bean.MedicDataBean;
import com.example.administrator.timeline.common.bean.MedicDayDataBean;
import com.example.administrator.timeline.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lv_medic_time_line;
    private TimeLineAdapter mTimeLineAdapter;
    private RelativeLayout rl_medic_two_weeks, rl_medic_one_mouth;
    private TextView tv_medic_two_weeks, tv_medic_one_mouth;
    private List<MedicDataBean> dataList;
    private TextView tv_start_time, tv_middle_time, tv_end_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initData();
        initView();
        setListener();
    }

    private void findViewById() {
        lv_medic_time_line = (ListView) findViewById(R.id.lv_medic_time_line);
        rl_medic_two_weeks = (RelativeLayout) findViewById(R.id.rl_medic_two_weeks);
        rl_medic_one_mouth = (RelativeLayout) findViewById(R.id.rl_medic_one_mouth);
        tv_medic_two_weeks = (TextView) findViewById(R.id.tv_medic_two_weeks);
        tv_medic_one_mouth = (TextView) findViewById(R.id.tv_medic_one_mouth);
        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        tv_middle_time = (TextView) findViewById(R.id.tv_middle_time);
        tv_end_time = (TextView) findViewById(R.id.tv_end_time);
    }

    private void initData() {
        List<MedicDataBean> mdataList = createListData();
        dataList = dealDataList(mdataList);
        mTimeLineAdapter = new TimeLineAdapter(this, dataList, TimeUtils.isWeekStartYYMMDDDay(), 14);
        lv_medic_time_line.setAdapter(mTimeLineAdapter);
    }

    //造假数据
    private List<MedicDataBean> createListData() {

        List<MedicDataBean> dataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            MedicDataBean medicDataBean = new MedicDataBean();
            List<MedicDayDataBean> mdataList = new ArrayList<>();

            MedicDayDataBean medicDayDataBean = new MedicDayDataBean();
            medicDayDataBean.setStartAt("2017-7-11");
            medicDayDataBean.setEndAt("2017-7-13");
            medicDayDataBean.setStatus("NORMAL");
            mdataList.add(medicDayDataBean);

            MedicDayDataBean medicDayDataBean2 = new MedicDayDataBean();
            medicDayDataBean2.setStartAt("2017-7-15");
            medicDayDataBean2.setEndAt("2017-7-16");
            medicDayDataBean2.setStatus("PARTIAL_EXCEPTION");
            mdataList.add(medicDayDataBean2);

            MedicDayDataBean medicDayDataBean3 = new MedicDayDataBean();
            medicDayDataBean3.setStartAt("2017-7-18");
            medicDayDataBean3.setEndAt("2017-7-20");
            medicDayDataBean3.setStatus("ABNORMAL");
            mdataList.add(medicDayDataBean3);

            MedicDayDataBean medicDayDataBean4 = new MedicDayDataBean();
            medicDayDataBean4.setStartAt("2017-7-22");
            medicDayDataBean4.setEndAt("2017-7-22");
            medicDayDataBean4.setStatus("PARTIAL_EXCEPTION");
            mdataList.add(medicDayDataBean4);

            MedicDayDataBean medicDayDataBean5 = new MedicDayDataBean();
            medicDayDataBean5.setStartAt("2017-7-23");
            medicDayDataBean5.setEndAt("2017-7-23");
            medicDayDataBean5.setStatus("ABNORMAL");
            mdataList.add(medicDayDataBean5);

            MedicDayDataBean medicDayDataBean6 = new MedicDayDataBean();
            medicDayDataBean6.setStartAt("2017-7-24");
            medicDayDataBean6.setEndAt("2017-7-24");
            medicDayDataBean6.setStatus("NORMAL");
            mdataList.add(medicDayDataBean6);

            medicDataBean.setMedicName("Item" + i);
            medicDataBean.setMedicDayDataBean(mdataList);
            dataList.add(medicDataBean);
        }
        return dataList;
    }

    //为dataList里面的数据tag字段赋值
    private List<MedicDataBean> dealDataList(List<MedicDataBean> mdataList) {
        for (MedicDataBean mdBean : mdataList) {
            List<MedicDayDataBean> medicDayDataBeens = mdBean.getMedicDayDataBean();
            int size = medicDayDataBeens.size();
            //开头：-1,中间:0,结尾:1,2:又是开头也是结尾;
            for (int i = 0; i < size; i++) {
                MedicDayDataBean mddBean = medicDayDataBeens.get(i);
                int mddBeanSize = (int) (TimeUtils.getDiffFromDay(mddBean.getStartAt(), mddBean.getEndAt()) + 1);
                mddBean.setBeanSize(mddBeanSize);
                // 如果不是最后一个，即后面还有
                if ((i + 1) < size) {
                    MedicDayDataBean next_mddBean = medicDayDataBeens.get(i + 1);
                    if (TimeUtils.getDiffFromDay(mddBean.getEndAt(), next_mddBean.getStartAt()) > 1) {
                        if (mddBean.getTag() == 1) {
                            mddBean.setTag(1);
                        } else {
                            mddBean.setTag(2);
                        }
                        next_mddBean.setTag(2);
                    } else if (TimeUtils.getDiffFromDay(mddBean.getEndAt(), next_mddBean.getStartAt()) == 1) {
                        if (mddBean.getTag() == 1) {
                            mddBean.setTag(0);
                        } else {
                            mddBean.setTag(-1);
                        }
                        next_mddBean.setTag(1);
                    }
                } else {
                    if (mddBean.getTag() == 1) {
                        mddBean.setTag(1);
                    } else {
                        mddBean.setTag(2);
                    }

                }

            }
        }
        return mdataList;
    }

    private void initView() {
        tv_start_time.setText(TimeUtils.isWeekStartDay());
        tv_middle_time.setText(TimeUtils.isWeekMiddleDay());
        tv_end_time.setText(TimeUtils.isWeekAndMonthEndDay());
    }

    protected void setListener() {
        rl_medic_two_weeks.setOnClickListener(this);
        rl_medic_one_mouth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_medic_two_weeks:
                rl_medic_two_weeks.setBackgroundResource(R.drawable.corner_l_half_blue_bg);
                tv_medic_two_weeks.setTextColor(getResources().getColor(R.color.white));
                rl_medic_one_mouth.setBackground(null);
                tv_medic_one_mouth.setTextColor(getResources().getColor(R.color.c_2bb2ba));
                if (mTimeLineAdapter != null) {
                    mTimeLineAdapter.setData(dataList, TimeUtils.isWeekStartYYMMDDDay(), 14);
                }
                tv_start_time.setText(TimeUtils.isWeekStartDay());
                tv_middle_time.setText(TimeUtils.isWeekMiddleDay());
                tv_end_time.setText(TimeUtils.isWeekAndMonthEndDay());
                break;
            case R.id.rl_medic_one_mouth:
                rl_medic_one_mouth.setBackgroundResource(R.drawable.corner_r_half_blue_bg);
                tv_medic_one_mouth.setTextColor(getResources().getColor(R.color.white));
                rl_medic_two_weeks.setBackground(null);
                tv_medic_two_weeks.setTextColor(getResources().getColor(R.color.c_2bb2ba));
                if (mTimeLineAdapter != null) {
                    mTimeLineAdapter.setData(dataList, TimeUtils.isMonthStartYYMMDDDay(), 30);
                }
                tv_start_time.setText(TimeUtils.isMonthStartDay());
                tv_middle_time.setText(TimeUtils.isMonthMiddleDay());
                tv_end_time.setText(TimeUtils.isWeekAndMonthEndDay());
                break;
        }
    }
}
