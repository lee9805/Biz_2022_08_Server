package com.callor.todo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	public String list(@ModelAttribute("memo") TodoVO todo,Principal principal, String username) {
		todo = tService.findById(username);
		return "todo/insert";
	}
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String list(@ModelAttribute("memo") TodoVO todo,Model model, Principal principal) {
		tService.insert(todo);
		return "redirect:/";
	}
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update(String t_seq,Model model) {
		TodoVO todoVO = tService.findById(t_seq);
		model.addAttribute("TODO", todoVO);
		return "redirect:/todo/insert?seq=" + todoVO.getT_seq();
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(TodoVO todoVO,Model model) {
		tService.update(todoVO);
		return "redirect:/";
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(String t_seq) {
		
		tService.delete(t_seq);
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
