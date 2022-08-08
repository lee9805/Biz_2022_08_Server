package com.callor.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.callor.todo.model.AuthorityVO;
import com.callor.todo.model.UserVO;
import com.callor.todo.persistance.UserDao;
import com.callor.todo.service.UserService;

@Service("userServiceV1")
public class UserServiceImplV1 implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Bean
	public void create_table() {
		userDao.create_user_table();
		userDao.create_auth_table();
	}
	
	@Override
	public void create_user_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_auth_table() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AuthorityVO> select_auths(String username) {
		return userDao.select_auths(username);
	}

	@Override
	public List<UserVO> selectAll() {
		return userDao.selectAll();
	}

	@Override
	public UserVO findById(String username) {
		return userDao.findById(username);
	}

	@Override
	public int insert(UserVO vo) {
		
		return userDao.insert(vo);
		
	}

	@Override
	public int update(UserVO vo) {
		// TODO Auto-generated method stub
		return userDao.update(vo);
	}

	@Override
	public int delete(String username) {
		// TODO Auto-generated method stub
		return userDao.delete(username);
	}

	@Override
	public int roleInsert(List<AuthorityVO> auths) {
		// TODO Auto-generated method stub
		return userDao.roleInsert(auths);
	}

}
