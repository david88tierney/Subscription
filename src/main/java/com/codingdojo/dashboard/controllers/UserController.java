package com.codingdojo.dashboard.controllers;



import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.dashboard.models.User;
import com.codingdojo.dashboard.services.SubscriptionService;
import com.codingdojo.dashboard.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	private UserService uS;
	private SubscriptionService sS;
	
	public UserController(UserService uS, SubscriptionService sS ){
		this.uS= uS;
		this.sS = sS;
	}
	

	@GetMapping("")
	public String showRegister(@ModelAttribute("user") User user) {
		return "register";
	}
	
	@PostMapping("")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult res, Model model) {
		if(res.hasErrors()) {
			return "register";
		}else {
			if(!user.getPassword().equals(user.getConfirm())) {
				model.addAttribute("userError", "Password's must match!");
				return "register";
			} else {
				
				String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				user.setPassword(pw);
				User exists= uS.findByEmail(user.getEmail());
				
				if(exists != null) {
					model.addAttribute("userError", "A user with this email already exists");
					return "register";
				} else {
					int userCount = uS.findAll().size();
					
					if(userCount <1 ) {
						user.setAdmin(true);
					} else {
						user.setAdmin(false);
					}
					
					uS.create(user);
					return "redirect:/users/dashboard";
				}
			}
		}
	}
	
	@PostMapping("/login")
	public String login( @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(email.length() < 1) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "register";
		}
		if(password.length() < 8) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "register";
		}
		
		User u = uS.findByEmail(email);
		
		if(u == null) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "register";
		} else {
			boolean matches = BCrypt.checkpw(password, u.getPassword());
			
			if(matches) {
				session.setAttribute("user", u.getId());
				
				if(u.isAdmin()) {
					return "redirect:/users/admin";
				} else {
					return "redirect:/users/dashboard";
				} 
			}else {
					model.addAttribute("loginError", "Invalid Credentials");
					model.addAttribute("user", new User());
					return "register";
				}
			}
		}
	
	
	@GetMapping("/admin")
	public String admin(HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("user");
		
		
		if(id == null) {
			return "redirect:/users";
		} else {
			User user = uS.findById(id);
			
			if(user.isAdmin()) {
				model.addAttribute("users", uS.findAll());
				model.addAttribute("subscriptions", sS.findAll());
				return "admin";
			} else {
				return "redirect:/users/dashboard";
			}	
		}	
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		
		if(id == null) {
			return "redirect:/users";
		} else {
			User u = uS.findById(id);
			model.addAttribute("user", u);
			model.addAttribute("subscriptions", sS.findAll());
			return "dashboard";
		}
	}
	


}






























