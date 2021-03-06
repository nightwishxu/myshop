package com.api.action;

import com.api.view.userComment.AppUserComment;
import com.base.action.CoreController;
import com.base.api.ApiBaseController;
import com.base.api.ApiException;
import com.base.api.MobileInfo;
import com.base.api.annotation.ApiMethod;
import com.base.service.SensitivWordsService;
import com.base.util.BeanUtils;
import com.item.dao.model.UserComment;
import com.item.dao.model.UserCommentExample;
import com.item.service.UserCommentService;
import com.item.service.UserService;
import com.paidang.dao.model.Goods;
import com.paidang.dao.model.OrderExample;
import com.paidang.service.GoodsService;
import com.paidang.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.paidang.dao.model.Order;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api/userComment", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "用户评价(用户端)")
public class ApiUserCommentController extends ApiBaseController {


    @Autowired
    private UserCommentService userCommentService;

    @Autowired
    private SensitivWordsService sensitivWordsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "新增用户评价", notes = "登陆")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Object add(MobileInfo mobileInfo,
                      @ApiParam(value = "评论", required = true)String info,
                      @ApiParam(value = "商品id", required = true)Integer goodsId,
                      @ApiParam(value = "订单id", required = true)Integer orderId,
                      @ApiParam(value = "图片", required = false)String img,
                      @ApiParam(value = "评分1-5", required = true)Integer score,
                      @ApiParam(value = "物流评分1-5", required = true)Integer expressScore,
                      @ApiParam(value = "服务评分1-5", required = true)Integer serviceScore
                      ){
        UserComment userComment=new UserComment();

        OrderExample example=new OrderExample();
        example.createCriteria().andIdEqualTo(orderId).andStateEqualTo(4).andCommentStateNotEqualTo(1);
        List<Order> orders=orderService.selectByExample(example);
        if (orders==null || orders.size()==0){
            throw new ApiException("该订单不是未评价订单");
        }
        Date date=new Date();
        userComment.setInfo(info);
        userComment.setStatus(1);
        userComment.setScore(score);
        userComment.setShowName(0);
        userComment.setUserId(mobileInfo.getUserid());
        userComment.setGoodsId(goodsId);
        userComment.setOrgId(orders.get(0).getOrgId());
        userComment.setOrderId(orderId);
        userComment.setImg(img);
        userComment.setExpressScore(expressScore);
        userComment.setServiceScore(serviceScore);
        userComment.setCreateTime(date);
        if (userComment.getShowName()==null){
            userComment.setShowName(0);
        }
        //敏感词汇过滤
        if (StringUtils.isNotBlank(userComment.getInfo())){
            userComment.setInfo(sensitivWordsService.relpSensitivWords(info));
        }
        Goods goods=goodsService.selectByPrimaryKey(orders.get(0).getGoodsId());

        userComment.setGoodsName(goods==null?"":goods.getName());
        userComment.setUserName(userService.selectByPrimaryKey(mobileInfo.getUserid()).getNickName());
        Integer result=userCommentService.insert(userComment);
        if (result>0){
            com.paidang.dao.model.Order order=new  com.paidang.dao.model.Order();
            order.setCommentState(1);
            orderService.updateByExampleSelective(order,example);
        }
        return result;
    }


    @ApiOperation(value = "更新用户评价", notes = "登陆")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Object update(@ApiParam(value = "评价id", required = true)Integer id,@ApiParam(value = "评论", required = true)String info){
        UserComment userComment=new UserComment();
        userComment.setId(id);
        userComment.setInfo(sensitivWordsService.relpSensitivWords(info));
        return userCommentService.updateByPrimaryKeySelective(userComment);
    }


    @ApiOperation(value = "商品评价列表", notes = "不需要登陆")
    @RequestMapping(value = "/goodsCommentList", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public Object goodsCommentList(@ApiParam(value = "商品id", required = true)Integer goodsId){
        UserComment comment=new UserComment();
        comment.setGoodsId(goodsId);
        comment.setExpressScore(1);
        return userCommentService.findList(comment);
    }

    @ApiOperation(value = "机构评价列表", notes = "不需要登陆")
    @RequestMapping(value = "/getGoodsComment", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public Object getGoodsComment(@ApiParam(value = "机构id", required = true)Integer orgId){
        UserComment comment=new UserComment();
        comment.setOrgId(orgId);
        comment.setExpressScore(1);
        return userCommentService.findList(comment);
    }

    @ApiOperation(value = "test", notes = "登陆")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public Object test(@ApiParam(value = "评论", required = true)String info){
        System.out.println(info);
        System.out.println("过滤="+sensitivWordsService.relpSensitivWords(info));
        return sensitivWordsService.relpSensitivWords(info);
    }

}
