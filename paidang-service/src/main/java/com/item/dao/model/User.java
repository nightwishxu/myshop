package com.item.dao.model;



/**
 *
 */
public class User {

	/**
	 *用户id
	 */
	private Integer id;

	/**
	 *用户账号(手机号)
	 */
	private String account;

	/**
	 *密码
	 */
	private String password;

	/**
	 *用户昵称
	 */
	private String nickName;

	/**
	 *头像
	 */
	private String headImg;

	/**
	 *性别 0:女 1:男 2:未知
	 */
	private Integer sex;

	/**
	 *姓名
	 */
	private String name;

	/**
	 *手机号
	 */
	private String phone;

	/**
	 *身份证
	 */
	private String idCard;

	/**
	 *身份证正面
	 */
	private String idCardImg;

	/**
	 *身份证反面
	 */
	private String idCardReverse;

	/**
	 *身份证手持
	 */
	private String idCardHand;

	/**
	 *摇一摇
	 */
	private String headShake;

	/**
	 *点点头
	 */
	private String headNod;

	/**
	 *眨眼睛
	 */
	private String headEye;

	/**
	 *身份证人脸是否绑定(0:未绑定 1绑定)
	 */
	private Integer isBind;

	/**
	 *0普通用户1机构员工账号
	 */
	private Integer type;

	/**
	 *机构id
	 */
	private Integer orgId;

	/**
	 *
	 */
	private java.util.Date createTime;

	/**
	 *
	 */
	private java.util.Date modifyTime;

	/**
	 *完善资料1是0否
	 */
	private Integer isComplete;

	/**
	 *
	 */
	private java.math.BigDecimal balance;

	/**
	 *
	 */
	private Integer state;

	/**
	 *
	 */
	private Integer credit;

	/**
	 *
	 */
	private java.util.Date loginTime;

	/**
	 *智齿token
	 */
	private String sobotToken;

	/**
	 *
	 */
	private java.util.Date lastSobotTokenTime;

	/**
	 *融云token
	 */
	private String imToken;

	public void setId(Integer id) {
		this.id=id;
	}

	public Integer getId() {
		return id;
	}

	public void setAccount(String account) {
		this.account=account == null ? account : account.trim();
	}

	public String getAccount() {
		return account;
	}

	public void setPassword(String password) {
		this.password=password == null ? password : password.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setNickName(String nickName) {
		this.nickName=nickName == null ? nickName : nickName.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setHeadImg(String headImg) {
		this.headImg=headImg == null ? headImg : headImg.trim();
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setSex(Integer sex) {
		this.sex=sex;
	}

	public Integer getSex() {
		return sex;
	}

	public void setName(String name) {
		this.name=name == null ? name : name.trim();
	}

	public String getName() {
		return name;
	}

	public void setPhone(String phone) {
		this.phone=phone == null ? phone : phone.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setIdCard(String idCard) {
		this.idCard=idCard == null ? idCard : idCard.trim();
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCardImg(String idCardImg) {
		this.idCardImg=idCardImg == null ? idCardImg : idCardImg.trim();
	}

	public String getIdCardImg() {
		return idCardImg;
	}

	public void setIdCardReverse(String idCardReverse) {
		this.idCardReverse=idCardReverse == null ? idCardReverse : idCardReverse.trim();
	}

	public String getIdCardReverse() {
		return idCardReverse;
	}

	public void setIdCardHand(String idCardHand) {
		this.idCardHand=idCardHand == null ? idCardHand : idCardHand.trim();
	}

	public String getIdCardHand() {
		return idCardHand;
	}

	public void setHeadShake(String headShake) {
		this.headShake=headShake == null ? headShake : headShake.trim();
	}

	public String getHeadShake() {
		return headShake;
	}

	public void setHeadNod(String headNod) {
		this.headNod=headNod == null ? headNod : headNod.trim();
	}

	public String getHeadNod() {
		return headNod;
	}

	public void setHeadEye(String headEye) {
		this.headEye=headEye == null ? headEye : headEye.trim();
	}

	public String getHeadEye() {
		return headEye;
	}

	public void setIsBind(Integer isBind) {
		this.isBind=isBind;
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setType(Integer type) {
		this.type=type;
	}

	public Integer getType() {
		return type;
	}

	public void setOrgId(Integer orgId) {
		this.orgId=orgId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime=modifyTime;
	}

	public java.util.Date getModifyTime() {
		return modifyTime;
	}

	public void setIsComplete(Integer isComplete) {
		this.isComplete=isComplete;
	}

	public Integer getIsComplete() {
		return isComplete;
	}

	public void setBalance(java.math.BigDecimal balance) {
		this.balance=balance;
	}

	public java.math.BigDecimal getBalance() {
		return balance;
	}

	public void setState(Integer state) {
		this.state=state;
	}

	public Integer getState() {
		return state;
	}

	public void setCredit(Integer credit) {
		this.credit=credit;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime=loginTime;
	}

	public java.util.Date getLoginTime() {
		return loginTime;
	}

	public void setSobotToken(String sobotToken) {
		this.sobotToken=sobotToken == null ? sobotToken : sobotToken.trim();
	}

	public String getSobotToken() {
		return sobotToken;
	}

	public void setLastSobotTokenTime(java.util.Date lastSobotTokenTime) {
		this.lastSobotTokenTime=lastSobotTokenTime;
	}

	public java.util.Date getLastSobotTokenTime() {
		return lastSobotTokenTime;
	}

	public void setImToken(String imToken) {
		this.imToken=imToken == null ? imToken : imToken.trim();
	}

	public String getImToken() {
		return imToken;
	}

}
