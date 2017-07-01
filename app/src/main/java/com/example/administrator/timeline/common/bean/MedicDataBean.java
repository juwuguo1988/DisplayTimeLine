package com.example.administrator.timeline.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juwuguo on 2017/6/29.
 */

public class MedicDataBean {
    private String medicName;
    private List<MedicDayDataBean> mMedicDayDataBean = new ArrayList<>();

    public String getMedicName() {
        return medicName;
    }

    public void setMedicName(String medicName) {
        this.medicName = medicName;
    }

    public List<MedicDayDataBean> getMedicDayDataBean() {
        return mMedicDayDataBean;
    }

    public void setMedicDayDataBean(List<MedicDayDataBean> medicDayDataBean) {
        mMedicDayDataBean = medicDayDataBean;
    }
}
