package web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	@RequestMapping( method = RequestMethod.GET)
	public String showMenu(Model model) {
		model.addAttribute("today", new Date());
		return "index";
	}
	
	

	

}
