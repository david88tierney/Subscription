package com.codingdojo.dashboard.controllers;

import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.dashboard.models.Subscription;
import com.codingdojo.dashboard.models.User;
import com.codingdojo.dashboard.services.SubscriptionService;
import com.codingdojo.dashboard.services.UserService;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {
	private SubscriptionService sS;
	private UserService uS;
	
	public SubscriptionController(SubscriptionService sS, UserService uS) {
		this.uS = uS;
		this.sS= sS;
	}
	
	@PostMapping("")
	public String create(@RequestParam("name") String name, @RequestParam("cost") double cost) {
		Subscription sub = new Subscription();
		sub.setAvailable(true);
		sub.setName(name);
		sub.setCost(cost);
		
		sS.create(sub);
		
		return "redirect:/users/admin";
	}
	
	@PostMapping ("{id}/toggle")
	public String toggle( @PathVariable("id") Long id, HttpSession session) {
		
		Long uid = (Long) session.getAttribute("user");
		User user = uS.findById(uid);
		if(user == null) {
			return "redirect:/users";
		}
		
		if(!user.isAdmin()) {
			return "redirect:/users/dashboard";
		}
		
		
		Subscription sub = sS.findById(id);
		sub.setAvailable(!sub.isAvailable());
		
		sS.update(sub);
		
		return "redirect:/users/admin";
	}
	
	@GetMapping("{id}/edit")
	public String showEdit(  @PathVariable("id") Long id, HttpSession session, Model model) {
		
		Long uid = (Long) session.getAttribute("user");
		User user = uS.findById(uid);
		if(user == null) {
			return "redirect:/users";
		}
		
		if(!user.isAdmin()) {
			return "redirect:/users/dashboard";
		}
		
		Subscription sub = sS.findById(id);
		
		if(sub == null) {
			return "redirect:/users/admin";
		}
		
		model.addAttribute("subscription", sub);
		return "edit";
	}
	
	
	@PostMapping("{id}/update")
	public String edit( @PathVariable("id") Long id, @RequestParam("cost") double cost ) {
		Subscription sub =  sS.findById(id);
		sub.setCost(cost);
		sS.update(sub);
		return "redirect:/users/admin";
	}
	
	@PostMapping("{id}/destroy")
	public String destroy(@PathVariable("id") Long id) {
		sS.destroy(id);
		return "redirect:/users/admin";
	}
	
	@PostMapping("/change")
	public String change(HttpSession session, @RequestParam("subscription") Long subId) {
		Long uid = (Long)session.getAttribute("user");
		User user = uS.findById(uid);
		Subscription sub = sS.findById(subId);
		user.setSubscription(sub);
		
		
		
		Date today = new Date();
		long ltime = today.getTime()+31*24*60*60*1000;
		Date newDate= new Date(ltime);
		
		user.setDueDate(newDate);
		uS.update(user);
		
		return "redirect:/users/dashboard";
	}
	
	
	
}
