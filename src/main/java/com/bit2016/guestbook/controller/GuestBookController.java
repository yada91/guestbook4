package com.bit2016.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.guestbook.DAO.GuestBookDAO;
import com.bit2016.guestbook.VO.GuestBook;

@Controller
public class GuestBookController {
	@Autowired
	private GuestBookDAO guestBookDAO;

	@RequestMapping("")
	public String list(Model model) {
		List<GuestBook> list = guestBookDAO.selectAll();
		model.addAttribute("list", list);
		model.addAttribute("br", "<br>");
		model.addAttribute("nl", "\r\n");
		return "/WEB-INF/views/list.jsp";
	}

	@RequestMapping("/add")
	public String add(@ModelAttribute GuestBook vo) {
		guestBookDAO.insert(vo);

		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		guestBookDAO.delete(id);
		return "redirect:/";
	}

	@RequestMapping("/deleteform/{id}")
	public String deleteForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("id", id);
		return "/WEB-INF/views/deleteform.jsp";
	}
}
