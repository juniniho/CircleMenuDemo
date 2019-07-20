package com.zp2.myapplication;

/**
 * Created by ychen on 2019/7/19.
 */
public enum CircleMenu {

    ALBUM("相册",R.mipmap.shop_home_new_xc3),
    VIDEO("视频",R.mipmap.shop_home_new_sp3),
    VR("720",R.mipmap.shop_home_new_7203),
    LIVE("直播",R.mipmap.shop_home_new_zb3);

    CircleMenu(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    private String name;
    private int resId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
