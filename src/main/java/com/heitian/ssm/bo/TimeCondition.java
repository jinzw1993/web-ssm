package com.heitian.ssm.bo;

/**
 * Created by oasis on 2016/12/15.
 */
//这个类的处理在OrderServiceImpl中的setTimeCon方法里
public class TimeCondition {
    private Integer id = 0;//time的值 0天 1周 2月 3年
    private Integer year; //年份
    private Integer month; //月份
    private Integer week; //当前周为1，前一周为2，依次类推
    private Integer day; //天
    private int page =1;
    private int count = 30;
    private int start;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer time) {
        this.id = time;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = (page-1)*count;
    }
}
