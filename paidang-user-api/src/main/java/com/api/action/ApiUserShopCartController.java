package com.api.action;


import com.api.MEnumError;
import com.api.view.home.UserInfo;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.api.annotation.ApiMethod;
import com.base.util.JSONUtils;
import com.item.dao.model.User;
import com.redis.JedisTemplate;
import com.redis.RedisKeyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/userShopCart", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "用户购物车(用户端)")
public class ApiUserShopCartController extends ApiBaseController {

    @Autowired
    private JedisTemplate jedisTemplate;

    @ApiOperation(value = "修改购物车商品数量", notes = "登陆")
    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public Object updateCart(MobileInfo mobileInfo,String goodsId,Integer num) {
//        String json=jedisTemplate.get(RedisKeyUtils.SHOP_CART+mobileInfo.getUserid());
//        JSONUtils.deserialize();
      return "test";
    }

}
