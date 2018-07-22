package com.api.view.store;

import com.paidang.dao.model.GoodsAuction;
import com.paidang.daoEx.model.GoodsAuctionEx;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 绝当商城商品
 */
public class AppJdGoods {
    @ApiModelProperty(value="商品id")
    private Integer id;
    @ApiModelProperty(value="商品图片")
    private String images;
    @ApiModelProperty(value="商品名字")
    private String goodsName;
    @ApiModelProperty(value="鉴定价--起价")
    private String authPrice;
    @ApiModelProperty(value="当前价")
    private String price;
    @ApiModelProperty(value="倒计时")
    private String time;
    @ApiModelProperty(value="竞拍记录")
    private List<GoodsAuctionEx> goodsAuctionList;
    @ApiModelProperty(value="物品描述")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAuthPrice() {
        return authPrice;
    }

    public void setAuthPrice(String authPrice) {
        this.authPrice = authPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<GoodsAuctionEx> getGoodsAuctionList() {
        return goodsAuctionList;
    }

    public void setGoodsAuctionList(List<GoodsAuctionEx> goodsAuctionList) {
        this.goodsAuctionList = goodsAuctionList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
