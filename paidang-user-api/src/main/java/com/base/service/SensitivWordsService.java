package com.base.service;

import com.alibaba.fastjson.JSONObject;
import com.item.dao.model.Code;
import com.item.dao.model.CodeExample;
import com.item.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 敏感词库 初始化缓存
 *
 */
@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class SensitivWordsService {
    private final static Logger logger = LoggerFactory.getLogger(SensitivWordsService.class);
    
    private static final String ENCODING = "UTF-8"; // 字符编码
    
    private static final String PATH = "/static/templates/SensitivWords.txt"; // 词库路径
    
    private static Set<String> keySet = new HashSet<String>();

    public  static List<String> expressList;
    public  static Map<String, String> expressMap;

    @Autowired
    CodeService codeService;
    
    /**
     * 加载敏感词
     *
     */
    @PostConstruct
    public void load() {
        
        logger.info("[内存数据]-开始加载敏感词数据任务");
        try {
            readSensitiveWordFile();
            loadData();
            logger.info("[内存数据]-敏感词数据任务加载完成");
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
    
    private void readSensitiveWordFile() throws Exception {
        Set<String> set = new HashSet<String>();;
        InputStreamReader read = null;
        try {
            if (PATH != null && !"".equals(PATH)) { // 文件流是否存在
                InputStream in = SensitivWordsService.class.getResourceAsStream(PATH);
                read = new InputStreamReader(in, ENCODING);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String txt = null;
                while ((txt = bufferedReader.readLine()) != null) { // 读取文件，将文件内容放入到set中
                    set.add(txt);
                }
                keySet.clear();
                keySet = set;
            } else { // 不存在抛出异常信息
                logger.error("敏感词库文件不存在");
            }
        } catch (Exception e) {
            logger.error("词库加载缓存失败");
        } finally {
            read.close(); // 关闭文件流
        }
    }
    
    /**
     * 过滤敏感词
     * @param info
     * @return
     */
    public String relpSensitivWords(String info) {
        SensitiveWordDfaFilter.initKeyWords(keySet);
        SensitiveWordDfaFilter filter = new SensitiveWordDfaFilter();
        info = filter.replaceSensitiveWord(info, 2, "*");
        return info;
    }

}
