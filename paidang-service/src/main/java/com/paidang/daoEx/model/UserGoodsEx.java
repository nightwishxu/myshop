package com.paidang.daoEx.model;

import com.paidang.dao.model.UserGoods;

import java.math.BigDecimal;
import java.util.Date;

/**
@author sun
*/
public class UserGoodsEx extends UserGoods {
	private String oldAccount;

	private String nickName;

	private String account;

	private String cateName;

	//用户姓名
	private String userName;
	//用户联系方式
	private String userPhone;
	//用户身份证
    private String idCard;

    //用户卖给平台  1卖给平台2平台确认
    private Integer platformState;

    //用户卖给平台，平台的打款凭证
    private String platformTicket;

    //真实邮寄状态
    private Integer expressState;

    //当号
    private String pawnTicketCode;

    //机构名称
    private String orgName;

    private Integer userState;

    private Integer userPawnId;

    private Integer userPawnState;

    private Integer userPawnCount;

    private Integer userPawnUserState;

    //管理员账号--0普通管理员 1审核管理员
    private Integer power;

    //专家评定内容
    private String experterInfo;

    //专家姓名
    private String experterName;

    //用户寄给平台
    private String expressData;
    //平台寄给用户
    private String expressData2;

    private Integer expressType;

    /**
     * 竞拍次数
     */
    private Integer cnt;

    private Integer pawnId;
    /**
     * 订单状态-1已取消1待付款2已付款3已发货4确认收货5已评价
     */
    private Integer orderState;


    private Integer orderStateEx;

    private Date useDate;

    private Integer collectUserId;
    /**
     * 0点赞1收藏
     */
    private Integer collectPraiseType;

    private String orderCode;

    private String expressName;

    private String expressCode;

    private String sellPawnCodeInfo;

    private Date orderPayTime;

    private BigDecimal orderPrice;


    private String headImg;

    public Integer getPawnId() {
        return pawnId;
    }

    public void setPawnId(Integer pawnId) {
        this.pawnId = pawnId;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getOldAccount() {
        return oldAccount;
    }

    public void setOldAccount(String oldAccount) {
        this.oldAccount = oldAccount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getPlatformState() {
        return platformState;
    }

    public void setPlatformState(Integer platformState) {
        this.platformState = platformState;
    }

    public String getPlatformTicket() {
        return platformTicket;
    }

    public void setPlatformTicket(String platformTicket) {
        this.platformTicket = platformTicket;
    }

    public Integer getExpressState() {
        return expressState;
    }

    public void setExpressState(Integer expressState) {
        this.expressState = expressState;
    }

    public String getPawnTicketCode() {
        return pawnTicketCode;
    }

    public void setPawnTicketCode(String pawnTicketCode) {
        this.pawnTicketCode = pawnTicketCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Integer getUserPawnId() {
        return userPawnId;
    }

    public void setUserPawnId(Integer userPawnId) {
        this.userPawnId = userPawnId;
    }

    public Integer getUserPawnState() {
        return userPawnState;
    }

    public void setUserPawnState(Integer userPawnState) {
        this.userPawnState = userPawnState;
    }

    public Integer getUserPawnCount() {
        return userPawnCount;
    }

    public void setUserPawnCount(Integer userPawnCount) {
        this.userPawnCount = userPawnCount;
    }

    public Integer getUserPawnUserState() {
        return userPawnUserState;
    }

    public void setUserPawnUserState(Integer userPawnUserState) {
        this.userPawnUserState = userPawnUserState;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getExperterInfo() {
        return experterInfo;
    }

    public void setExperterInfo(String experterInfo) {
        this.experterInfo = experterInfo;
    }

    public String getExperterName() {
        return experterName;
    }

    public void setExperterName(String experterName) {
        this.experterName = experterName;
    }

    public String getExpressData() {
        return expressData;
    }

    public void setExpressData(String expressData) {
        this.expressData = expressData;
    }

    public Integer getExpressType() {
        return expressType;
    }

    public void setExpressType(Integer expressType) {
        this.expressType = expressType;
    }

    public String getExpressData2() {
        return expressData2;
    }

    public void setExpressData2(String expressData2) {
        this.expressData2 = expressData2;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderStateEx() {
        return orderState;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setOrderStateEx(Integer orderStateEx) {
        this.orderStateEx = orderStateEx;
    }

    public Integer getCollectUserId() {
        return collectUserId;
    }

    public void setCollectUserId(Integer collectUserId) {
        this.collectUserId = collectUserId;
    }

    public Integer getCollectPraiseType() {
        return collectPraiseType;
    }

    public void setCollectPraiseType(Integer collectPraiseType) {
        this.collectPraiseType = collectPraiseType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getSellPawnCodeInfo() {
        return sellPawnCodeInfo;
    }

    public void setSellPawnCodeInfo(String sellPawnCodeInfo) {
        this.sellPawnCodeInfo = sellPawnCodeInfo;
    }

    public Date getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Date orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}