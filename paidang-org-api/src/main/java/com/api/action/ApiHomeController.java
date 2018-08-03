package com.api.action;

import com.api.view.orgHome.OrgInfo;
import com.paidang.daoEx.model.PawnOrgEx;
import com.paidang.service.PawnOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.MEnumError;
import com.api.util.PageLimit;
import com.api.view.home.UserInfo;
import com.api.view.home.UserIsReadCountInfo;
import com.api.view.home.UserNotifyInfo;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.api.annotation.ApiMethod;
import com.base.dao.model.Ret;
import com.base.dialect.PaginationSupport;
import com.base.util.DateUtil;
import com.base.util.StringUtil;
import com.item.dao.model.User;
import com.item.dao.model.UserExample;
import com.item.dao.model.UserNotify;
import com.item.dao.model.UserNotifyExample;
import com.item.service.UserNotifyService;
import com.item.service.UserService;

@RestController
@RequestMapping(value = "/api/home", produces = { "application/json;charset=UTF-8" })
//@Api(tags = "机构端个人中心")
public class ApiHomeController extends ApiBaseController{

	@Autowired
	private UserNotifyService userNotifyService;


	/**
	 *test
	 */
	@ApiOperation(value = "test",notes="登陆")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ApiMethod(isLogin = false)
	public Object test(String info){
		return "Hello,"+info;
	}


	/**
	 * 修改个人资料
	 * */
/*	@ApiOperation(value = "修改个人资料*",notes="登陆")
	@RequestMapping(value="/editUser", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public UserInfo editUser(MobileInfo mobileInfo,UserInfo userInfo){
		boolean update = false;
		User temp = new User();
		temp.setId(mobileInfo.getUserid());
		userInfo.setId(mobileInfo.getUserid());
		if (StringUtil.isNotBlank(userInfo.getHeadImg())){
			update = true;
			temp.setHeadImg(userInfo.getHeadImg());
		}
		if (StringUtil.isNotBlank(userInfo.getNickName())){
			update = true;
			// 校验登录
			UserExample example = new UserExample();
			example.createCriteria().andNickNameEqualTo(userInfo.getNickName());
			int i = userService.countByExample(example);
			if (i > 0) {
				throw new ApiException(MEnumError.NICK_EXISTS_ERROR);
			}
			temp.setNickName(userInfo.getNickName());
		}
		if (userInfo.getSex() != null){
			update = true;
			temp.setSex(userInfo.getSex());
		}
		if (update){
			//更新
			userService.updateByPrimaryKeySelective(temp);
		}
		temp = userService.selectByPrimaryKey(mobileInfo.getUserid());
		if (temp != null)
			userInfo.setAccount(temp.getAccount());
		return userInfo;
	}*/
	
	/**
	 * 获取用户未读消息数量
	 */
	@ApiOperation(value = "获取用户未读消息数量",notes="登陆")
	@RequestMapping(value = "/getUserIsReadCount", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public UserIsReadCountInfo getUserIsReadCount(MobileInfo mobileInfo){
		int count = 0;
		UserNotifyExample example = new UserNotifyExample();
		example.createCriteria().andUserIdEqualTo(mobileInfo.getUserid()).andIsReadEqualTo(0);
	    count = userNotifyService.countByExample(example);
	    UserIsReadCountInfo userIsReadCountInfo = new UserIsReadCountInfo();
	    userIsReadCountInfo.setCount(count);
		return userIsReadCountInfo;
	}
	
	/**
	 * 获取用户消息列表
	 */
	@ApiOperation(value = "获取用户消息列表",notes="登陆,分页")
	@RequestMapping(value = "/getUserMsgList", method = RequestMethod.POST)
	@ApiMethod(isLogin = true,isPage = true)
	public List<UserNotifyInfo> getUserMsgList(MobileInfo mobileInfo,
											   PageLimit pageLimit){
		UserNotifyExample example = new UserNotifyExample();
		example.setOrderByClause("create_time desc");
		example.createCriteria().andUserIdEqualTo(mobileInfo.getUserid());
		PaginationSupport.byPage(pageLimit.getPage(), pageLimit.getLimit(), false);
		List<UserNotify> list = userNotifyService.selectByExample(example);		
		return getAppInfo(list);
	}
	
	/**
	 * 封装返回用户列表数据
	 * 
	 * @param list
	 * @return
	 */
	private List<UserNotifyInfo> getAppInfo(List<UserNotify> list) {
		List<UserNotifyInfo> ret = new ArrayList<>(list.size());
		for (UserNotify ex : list) {
			ret.add(getAppInfo(ex));
		}
		return ret;
	}

	/**
	 * 封装返回UserNotifyInfo
	 * 
	 * @param ex
	 * @return
	 */
	private UserNotifyInfo getAppInfo(UserNotify ex) {
		UserNotifyInfo mini = new UserNotifyInfo();
		mini.setId(ex.getId());
		mini.setTitle(ex.getTitle());
		mini.setContent(ex.getContent());
		mini.setCreateTime(DateUtil.dateToStr(ex.getCreateTime()));
		mini.setType(ex.getType());
		mini.setRedirectType(ex.getRedirectType());
		mini.setRedirectContent(ex.getRedirectContent());
		return mini;
	}
	
	/**
	 * 用户点击详情
	 */
	@ApiOperation(value = "用户点击详情",notes="可以不登陆")
	@RequestMapping(value = "/enterUserNotifyDetail", method = RequestMethod.POST)
	@ApiMethod
	public UserNotifyInfo enterUserNotifyDetail(MobileInfo mobileInfo,
			@ApiParam(value = "消息id", required = true)Integer id){
		if (id == null) {
			throw new ApiException("id");
		}
		UserNotify ex = userNotifyService.selectByPrimaryKey(id);
		if(ex == null){
			throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
		}
		UserNotifyInfo record = new UserNotifyInfo();
		record.setTitle(ex.getTitle());
		record.setId(ex.getId());
		record.setType(ex.getType());
		record.setContent(ex.getContent());
		record.setRedirectType(ex.getRedirectType());
		record.setIsRead(ex.getIsRead());
		if(mobileInfo.getUserid()!=null){			
			
			ex.setIsRead(1);
			userNotifyService.updateByPrimaryKey(ex);
		}
		return record;
	}
	
	/**
	 * 删除用户消息列表
	 */
	@ApiOperation(value = "删除用户消息列表",notes="登陆")
	@RequestMapping(value = "/delNotifyDetail", method = RequestMethod.POST)
	@ApiMethod(isLogin = true)
	public Ret delNotifyDetail(MobileInfo mobileInfo, 
			@ApiParam(value = "消息id", required = true)Integer id){
		if (id == null) {
			throw new ApiException("id");
		}
		UserNotify record = userNotifyService.selectByPrimaryKey(id);
		if(record == null){
			throw new ApiException(MEnumError.CONTENT_NOEXIST_ERROR);
		}
		userNotifyService.deleteByPrimaryKey(id);
		Ret ret = new Ret();
		ret.setCode(0);
		return ret;
	}
	
}
