package com.codetutr.controller;

import com.codetutr.form.SubscriberWithValidator;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codetutr.form.SubscriberSimple;

@Controller
@RequestMapping("/validator")
public class FormControllerValidator {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String loadFormPage(Model m) {
		m.addAttribute("subscriber", new SubscriberWithValidator());
		return "formPageValidator";
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String submitForm(@Valid SubscriberWithValidator subscriber, BindingResult result, Model m) {
		if(result.hasErrors()) {
			return "formPageValidator";
		}
		
		m.addAttribute("message", "Successfully saved person: " + subscriber.toString());
		return "formPageValidator";
	}
}
