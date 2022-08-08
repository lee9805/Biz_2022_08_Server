package com.callor.todo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.todo.model.TodoVO;
import com.callor.todo.service.TodoService;

@Controller
@RequestMapping(value="/todo")
public class TodoController {
	
	@Autowired
	private TodoService tService;

	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String list(@ModelAttribute("memo") TodoVO todo,
						HttpSession httpSession,Model model) {
		String username = (String) httpSession.getAttribute("USERNAME");
		tService.selectAll();
		todo.setT_author(username);
		model.addAttribute(tService);
		return "todo/insert";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String list(@ModelAttribute("memo") TodoVO todo,Model model,
						HttpSession httpSession) {
		String username = (String) httpSession.getAttribute("USERNAME");
		
		todo.setT_author(username);
		tService.insert(todo);
		
		return "redirect:/";
	}
	
	@ModelAttribute("todo")
	private TodoVO TodoVO() {
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat toDay = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat toTime = new SimpleDateFormat("HH:mm:SS");
		
		TodoVO todo = TodoVO.builder()
						.t_sdate(toDay.format(date))
						.t_stime(toTime.format(date))
						.build();
		
		return todo;
	}
	
}
