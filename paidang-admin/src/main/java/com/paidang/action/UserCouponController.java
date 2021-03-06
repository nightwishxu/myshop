package com.paidang.action;

import java.util.List;
import com.base.dialect.PaginationSupport;
import com.base.dao.model.Ret;
import com.base.dao.model.Grid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang.StringUtils;
import com.base.util.JSONUtils;
import com.base.web.annotation.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import com.base.action.CoreController;
import com.paidang.dao.model.UserCoupon;
import com.paidang.dao.model.UserCouponExample;
import com.paidang.service.UserCouponService;

/**
@author sun
*/
@RequestMapping("userCoupon")
@Controller
@LoginFilter
public class UserCouponController extends CoreController{

    @Autowired
    private UserCouponService userCouponService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public String list(Integer page, Integer rows){
    	PaginationSupport.byPage(page, rows);
    	UserCouponExample example = new UserCouponExample();
    	
    	List<UserCoupon> list = userCouponService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public String save(UserCoupon userCoupon){
    	if (userCoupon.getId() == null){
    		userCouponService.insert(userCoupon);
    	}else{
    		userCouponService.updateByPrimaryKeySelective(userCoupon);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public String find(Integer id){
    	UserCoupon userCoupon = userCouponService.selectByPrimaryKey(id);
       	return ok(userCoupon);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public String del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		userCouponService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}