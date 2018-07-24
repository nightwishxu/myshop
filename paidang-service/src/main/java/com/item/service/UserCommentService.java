package com.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.dao.UserCommentMapper;
import com.item.dao.model.UserComment;
import com.item.dao.model.UserCommentExample;

@Service
public class UserCommentService {
	@Autowired
	private UserCommentMapper userCommentMapper;

	public int countByExample(UserCommentExample example) {
		return this.userCommentMapper.countByExample(example);
	}

	public UserComment selectByPrimaryKey(Integer id) {
		return this.userCommentMapper.selectByPrimaryKey(id);
	}

	public List<UserComment> selectByExample(UserCommentExample example) {
		return this.userCommentMapper.selectByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return this.userCommentMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserComment record) {
		return this.userCommentMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(UserComment record) {
		return this.userCommentMapper.updateByPrimaryKey(record);
	}

	public int deleteByExample(UserCommentExample example) {
		return this.userCommentMapper.deleteByExample(example);
	}

	public int updateByExampleSelective(UserComment record, UserCommentExample example) {
		return this.userCommentMapper.updateByExampleSelective(record, example);
	}

	public int updateByExample(UserComment record, UserCommentExample example) {
		return this.userCommentMapper.updateByExample(record, example);
	}

	public int insert(UserComment record) {
		return this.userCommentMapper.insert(record);
	}

	public int insertSelective(UserComment record) {
		return this.userCommentMapper.insertSelective(record);
	}

}
