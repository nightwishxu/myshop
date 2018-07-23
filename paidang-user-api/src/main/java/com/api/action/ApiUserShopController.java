package com.api.action;


import com.api.MEnumError;
import com.api.view.home.UserInfo;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.api.annotation.ApiMethod;
import com.item.dao.model.User;
import com.redis.JedisTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/userShop", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "用户购物车(用户端)")
public class ApiUserShopController {

    @Autowired
    private JedisTemplate jedisTemplate;

    @ApiOperation(value = "添加商品进购物车*", notes = "登陆")
    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public UserInfo getUserInfo(MobileInfo mobileInfo,String goodsId,Integer num) {

        return null;
    }

}
