package com.callor.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.callor.todo.model.TodoVO;
import com.callor.todo.persistance.TodoDao;
import com.callor.todo.service.TodoService;

@Service
public class TodoServiceImplV1 implements TodoService{

	@Autowired
	private TodoDao todoDao;
	
	@Override
	public List<TodoVO> selectAll() {
		// TODO Auto-generated method stub
		return todoDao.selectAll();
	}

	@Override
	public TodoVO findById(String t_author) {
		// TODO Auto-generated method stub
		return todoDao.findById(t_author);
	}

	@Override
	public int insert(TodoVO vo) {
		// TODO Auto-generated method stub
		return todoDao.insert(vo);
	}

	@Override
	public int update(TodoVO vo) {
		// TODO Auto-generated method stub
		return todoDao.update(vo);
	}

	@Override
	public int delete(String t_seq) {
		// TODO Auto-generated method stub
		return todoDao.delete(t_seq);
	}

	@Bean
	@Override
	public void create_todo_table() {
		todoDao.create_todo_table();
		
	}

	@Override
	public void completeUpdate(String t_seq) {
		
		TodoVO todoVO = todoDao.findById(t_seq);
		if(todoVO.isT_complete() == false) {
			todoVO.setT_complete(true);
		} else {
			todoVO.setT_complete(false);
		}
		todoDao.update(todoVO);
	}

}
