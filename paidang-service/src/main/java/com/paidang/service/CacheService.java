package com.paidang.service;

import com.alibaba.fastjson.JSONObject;
import com.item.dao.model.Code;
import com.item.dao.model.CodeExample;
import com.item.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @Auther: xuwenwei
 * @Date: 2018/8/6 08:29
 * @Description:
 */
@Service
public class CacheService {

    private final static Logger logger = LoggerFactory.getLogger(CacheService.class);

    public  static List<String> expressList;
    public  static Map<String, String> expressMap;

    @Autowired
    CodeService codeService;

    @PostConstruct
    public void load() {

        logger.info("[内存数据]-开始加载物流公司数据任务");
        try {
            loadData();
            logger.info("[内存数据]-物流公司加载完成");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void loadData() {
        expressList=new ArrayList<>();
        expressMap=new HashMap<>();
        CodeExample example = new CodeExample();
        example.createCriteria().andCodeEqualTo("express");
        List<Code> codes = codeService.selectByExample(example);
        Code code = codes.get(0);
        Map<String, String> map = (Map) JSONObject.parse(code.getValue());
        expressMap.putAll(map);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            expressList.add(entry.getKey());
        }

    }

}
