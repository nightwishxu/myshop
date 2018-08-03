package com.api.action;

import com.base.api.annotation.ApiMethod;
import com.item.dao.model.UserComment;
import com.paidang.service.PawnOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param
 * @Auther: xuwenwei
 * @Date: 2018/8/3 15:48
 * @Description:
 */
@RestController
@RequestMapping(value = "/api/pawnOrg", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "店铺详情")
public class ApiPawnOrgController {

    @Autowired
    private PawnOrgService pawnOrgService;

    @ApiOperation(value = "店铺详情", notes = "不用登陆")
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public Object get(@ApiParam(value = "机构id", required = true)Integer orgId){
      return pawnOrgService.selectByPrimaryKey(orgId);
    }
}
