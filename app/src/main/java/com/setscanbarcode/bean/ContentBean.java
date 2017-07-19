package com.setscanbarcode.bean;

/**
 * Created by 张明_ on 2017/4/5.
 */

public class ContentBean {
    private String title;
    private String describe;
    private boolean isCheck;
    private boolean tvVisible;
    private boolean cbVisible;
    private boolean enable;

    public boolean isTvVisible() {
        return tvVisible;
    }

    public void setTvVisible(boolean tvVisible) {
        this.tvVisible = tvVisible;
    }


    public boolean isCbVisible() {
        return cbVisible;
    }

    public void setCbVisible(boolean cbVisible) {
        this.cbVisible = cbVisible;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
