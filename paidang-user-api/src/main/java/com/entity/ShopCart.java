package com.entity;

/**
 * @param
 * @Auther: xuwenwei
 * @Date: 2018/7/23 13:28
 * @Description:
 */
public class ShopCart {

    private int userId;

    private int goodsId;

    private int num;

    private int orgId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
}
