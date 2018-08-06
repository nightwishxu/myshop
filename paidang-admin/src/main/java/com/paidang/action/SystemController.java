package com.paidang.action;

import com.base.dialect.PaginationSupport;
import com.base.web.annotation.LoginFilter;
import com.paidang.service.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @param
 * @Auther: xuwenwei
 * @Date: 2018/8/6 14:53
 * @Description:
 */
@RequestMapping("system")
@Controller
@LoginFilter
public class SystemController {

    @RequestMapping("/expressList")
    @ResponseBody
    public Object list(){
        return CacheService.expressList;
    }
}
