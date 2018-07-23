package com.api.action;


import com.api.MEnumError;
import com.api.view.home.UserInfo;
import com.base.action.CoreController;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.api.annotation.ApiMethod;
import com.base.dialect.PaginationSupport;
import com.base.util.JSONUtils;
import com.item.dao.model.ShopCart;
import com.item.dao.model.ShopCartExample;
import com.item.dao.model.User;
import com.item.service.ShopCartService;
import com.redis.JedisTemplate;
import com.redis.RedisKeyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/userShopCart", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "用户购物车(用户端)")
public class ApiUserShopCartController extends CoreController {

    @Autowired
    private JedisTemplate jedisTemplate;

    @Autowired
    private ShopCartService shopCartService;

    @RequestMapping("/list")
    @ResponseBody
    public String list(Integer page, Integer rows){
        PaginationSupport.byPage(page, rows);
        ShopCartExample example = new ShopCartExample();

        List<ShopCart> list = shopCartService.selectByExample(example);
        return page(list);
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(ShopCart shopCart){
        if (shopCart.getUserId() == null){
            shopCartService.insert(shopCart);
        }else{
            shopCartService.updateByPrimaryKeySelective(shopCart);
        }
        return ok();
    }

    @RequestMapping("/findById")
    @ResponseBody
    public String find(Integer id){
        ShopCart shopCart = shopCartService.selectByPrimaryKey(id);
        return ok(shopCart);
    }

    @RequestMapping("/del")
    @ResponseBody
    public String del(String id){
        String[] ids = id.split(",");
        for (String str : ids){
            shopCartService.deleteByPrimaryKey(Integer.parseInt(str));
        }
        return ok();
    }


    @ApiOperation(value = "修改购物车商品数量", notes = "登陆")
    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public Object updateCart(MobileInfo mobileInfo,String goodsId,Integer num) {
//        String json=jedisTemplate.get(RedisKeyUtils.SHOP_CART+mobileInfo.getUserid());
//        JSONUtils.deserialize();
      return "test";
    }

}
