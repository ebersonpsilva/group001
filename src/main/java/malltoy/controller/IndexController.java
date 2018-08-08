package malltoy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String usindex() {
		return "redirect:/public/home/index";
	}
	
	@RequestMapping("/admin")
	public String adIndex() {
		return "redirect:/public/admin/index";
	}
}
