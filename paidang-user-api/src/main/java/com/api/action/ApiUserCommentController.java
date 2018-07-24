package com.api.action;

import com.api.view.userComment.AppUserComment;
import com.base.action.CoreController;
import com.base.api.annotation.ApiMethod;
import com.base.service.SensitivWordsService;
import com.base.util.BeanUtils;
import com.item.dao.model.UserComment;
import com.item.dao.model.UserCommentExample;
import com.item.service.UserCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/userComment", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
@Api(tags = "用户评价(用户端)")
public class ApiUserCommentController extends CoreController {


    @Autowired
    private UserCommentService userCommentService;

    @Autowired
    private SensitivWordsService sensitivWordsService;

    @ApiOperation(value = "新增用户评价", notes = "登陆")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiMethod(isLogin = true)
    public Object add(AppUserComment appUserComment){
        UserComment userComment=new UserComment();
        try {
            BeanUtils.copyProperties(appUserComment,userComment);
        } catch (Exception e) {
            e.printStackTrace();
            return msg(-1,"评价失败，请重新评价");
        }
        if (userComment.getShowName()==null){
            userComment.setShowName(0);
        }
        userComment.setStatus(1);
        //敏感词汇过滤
        userComment.setInfo(sensitivWordsService.relpSensitivWords(userComment.getInfo()));
        return userCommentService.insert(userComment);
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
        UserCommentExample example=new UserCommentExample();
        UserCommentExample.Criteria criteria=example.createCriteria();
        example.setOrderByClause("create_time desc");
        criteria.andGoodsIdEqualTo(goodsId);
        criteria.andStatusEqualTo(1);
        return userCommentService.selectByExample(example);
    }

    @ApiOperation(value = "机构评价列表", notes = "不需要登陆")
    @RequestMapping(value = "/getGoodsComment", method = RequestMethod.POST)
    @ApiMethod(isLogin = false)
    public Object getGoodsComment(@ApiParam(value = "机构id", required = true)Integer orgId){
        UserCommentExample example=new UserCommentExample();
        UserCommentExample.Criteria criteria=example.createCriteria();
        example.setOrderByClause("create_time desc");
        criteria.andOrgIdEqualTo(orgId);
        criteria.andStatusEqualTo(1);
        return userCommentService.selectByExample(example);
    }

}
