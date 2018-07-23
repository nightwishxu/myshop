package com.item.service;

import java.util.List;

import com.item.daoEx.ShopCartMapperEx;
import com.item.daoEx.model.ShopCartEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.dao.ShopCartMapper;
import com.item.dao.model.ShopCart;
import com.item.dao.model.ShopCartExample;

@Service
public class ShopCartService {
	@Autowired
	private ShopCartMapper shopCartMapper;

	@Autowired
	private ShopCartMapperEx shopCartMapperEx;

	public int countByExample(ShopCartExample example) {
		return this.shopCartMapper.countByExample(example);
	}

	public ShopCart selectByPrimaryKey(Integer user_id) {
		return this.shopCartMapper.selectByPrimaryKey(user_id);
	}

	public List<ShopCart> selectByExample(ShopCartExample example) {
		return this.shopCartMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.shopCartMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ShopCart record) {
		return this.shopCartMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ShopCart record) {
		return this.shopCartMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(ShopCartExample example) {
		return this.shopCartMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(ShopCart record, ShopCartExample example) {
		return this.shopCartMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(ShopCart record, ShopCartExample example) {
		return this.shopCartMapper.updateByExample(record, example);
	}

	public int insert(ShopCart record) {
		return this.shopCartMapper.insert(record);
	}

	public int insertSelective(ShopCart record) {
		return this.shopCartMapper.insertSelective(record);
	}

	/**
	 * 购物车列表
	 * @param userId
	 * @return
	 */
	public List<ShopCartEx> findList(Integer userId){
		return shopCartMapperEx.findList(userId);
	}

}
