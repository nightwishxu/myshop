package com.paidang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paidang.dao.model.UserGoods;
import com.paidang.dao.model.UserGoodsExample;

public interface UserGoodsMapper {
	int countByExample(UserGoodsExample example);

	int deleteByExample(UserGoodsExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(UserGoods record);

	int insertSelective(UserGoods record);

	List<UserGoods> selectByExample(UserGoodsExample example);

	List<UserGoods> selectByExampleWithBLOBs(UserGoodsExample example);

	UserGoods selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") UserGoods record,@Param("example") UserGoodsExample example);

	int updateByExampleWithBLOBs(@Param("record") UserGoods record, @Param("example") UserGoodsExample example);

	int updateByExample(@Param("record") UserGoods record,@Param("example") UserGoodsExample example);

	int updateByPrimaryKeySelective(UserGoods record);

	int updateByPrimaryKeyWithBLOBs(UserGoods record);

	int updateByPrimaryKey(UserGoods record);

}
