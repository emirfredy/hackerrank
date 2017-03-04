package co.com.rappi.cubes.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/home")
	public String home(ModelMap modal) {
        modal.addAttribute("title","CRUD Example");
		return "index";
	}

}