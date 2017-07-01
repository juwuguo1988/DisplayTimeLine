package com.example.administrator.timeline.common.bean;

/**
 * Created by Juwuguo on 2017/6/29.
 */

public class MedicDayDataBean {

    private String startAt;
    private String endAt;
    private String status;
    private int beanSize;
    private int tag;//开头：-1；中间0，结尾1；2,又是开头也是结尾

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getBeanSize() {
        return beanSize;
    }

    public void setBeanSize(int beanSize) {
        this.beanSize = beanSize;
    }
}
