package com.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.ConstantsCode;
import com.base.cache.serviceCache.CacheAdd;
import com.base.cache.serviceCache.CacheRemove;
import com.item.dao.CodeMapper;
import com.item.dao.model.Code;
import com.item.dao.model.CodeExample;

@Service
public class CodeService {
    @Autowired
    private CodeMapper codeMapper;

    public int countByExample(CodeExample example) {
        return this.codeMapper.countByExample(example);
    }

    @CacheAdd(cache=ConstantsCode.SERVICE_CACHE,group="'CodeService'",key="#code")
    public Code selectByPrimaryKey(String code) {
        return this.codeMapper.selectByPrimaryKey(code);
    }

    public List<Code> selectByExample(CodeExample example) {
        return this.codeMapper.selectByExample(example);
    }

    @CacheRemove(cache=ConstantsCode.SERVICE_CACHE,group="'CodeService'",key="#code")
    public int deleteByPrimaryKey(String code) {
        return codeMapper.deleteByPrimaryKey(code);
    }

    @CacheRemove(cache=ConstantsCode.SERVICE_CACHE,group="'CodeService'",key="#record.code")
    public int updateByPrimaryKeySelective(Code record) {
        return codeMapper.updateByPrimaryKeySelective(record);
    }
    
    @CacheRemove(cache=ConstantsCode.SERVICE_CACHE,group="'CodeService'",key="#record.code")
    public int updateByPrimaryKey(Code record) {
        return codeMapper.updateByPrimaryKey(record);
    }

    public int deleteByExample(CodeExample example) {
        return codeMapper.deleteByExample(example);
    }

    public int updateByExampleSelective(Code record, CodeExample example) {
        return codeMapper.updateByExampleSelective(record, example);
    }

    public int updateByExample(Code record, CodeExample example) {
        return codeMapper.updateByExample(record, example);
    }

    public int insert(Code record) {
        return codeMapper.insert(record);
    }

    public int insertSelective(Code record) {
        return codeMapper.insertSelective(record);
    }
    
    public String getCode(String code){
    	Code c = selectByPrimaryKey(code);
    	return c.getValue();
    }
    
    @CacheAdd(cache=ConstantsCode.SERVICE_CACHE,group="'CodeService'",key="#code")
    public Code getByCode(String code){
    	return selectByPrimaryKey(code);
    }
}