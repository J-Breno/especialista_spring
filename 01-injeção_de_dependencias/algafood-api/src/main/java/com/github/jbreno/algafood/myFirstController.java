package com.github.jbreno.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class myFirstController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello!";
	}
	
	
}
