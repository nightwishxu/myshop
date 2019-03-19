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
import com.paidang.dao.model.OutOrder;
import com.paidang.dao.model.OutOrderExample;
import com.paidang.service.OutOrderService;

/**
@author sun
*/
@RequestMapping("outOrder")
@Controller
@LoginFilter
public class OutOrderController extends CoreController{

    @Autowired
    private OutOrderService outOrderService;
    
    @RequestMapping("/list")
	@ResponseBody 
    public String list(Integer page, Integer rows){
    	PaginationSupport.byPage(page, rows);
    	OutOrderExample example = new OutOrderExample();
    	
    	List<OutOrder> list = outOrderService.selectByExample(example);
      	return page(list);
    }
    
    @RequestMapping("/save")
	@ResponseBody 
    public String save(OutOrder outOrder){
    	if (outOrder.getId() == null){
    		outOrderService.insert(outOrder);
    	}else{
    		outOrderService.updateByPrimaryKeySelective(outOrder);
    	}
       	return ok();
    }
    
    @RequestMapping("/findById")
	@ResponseBody 
    public String find(Integer id){
    	OutOrder outOrder = outOrderService.selectByPrimaryKey(id);
       	return ok(outOrder);
    }
    
    @RequestMapping("/del")
	@ResponseBody 
    public String del(String id){
    	String[] ids = id.split(",");
    	for (String str : ids){
    		outOrderService.deleteByPrimaryKey(Integer.parseInt(str));
    	}
       	return ok();
    }


}