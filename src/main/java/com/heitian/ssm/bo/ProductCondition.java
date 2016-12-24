package com.heitian.ssm.bo;

/**
 * Created by Lanting on 2016/11/26.
 */
public class ProductCondition {
    private String keyWord;
    private Long categoryId;

    private Double highPrice;
    private Double lowPrice;
    private int page=1;
    private int num=30;
    private int start;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start =(page-1)*num;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

}
