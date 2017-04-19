package com.codetutr.controller;

import com.codetutr.form.Frequency;
import com.codetutr.form.SubscriberSimple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {
	
	@ModelAttribute("frequencies")
	public Frequency[] frequencies() {
		/*When a method is annotated with @ModelAttribute,
		Spring runs it before each handler method and adds the return value to the model.
		We specified in the annotation to add the Frequency values to the model as “frequencies”.*/
		return Frequency.values();
	}

	@RequestMapping(value="form", method=RequestMethod.GET)
	public String loadFormPage(Model model) {

		SubscriberSimple subscriber = new SubscriberSimple();
		subscriber.setReceiveNewsletter(true);
		subscriber.setNewsletterFrequency(Frequency.HOURLY);
		model.addAttribute("subscriber", subscriber);

		// все поля будут пустые если
		//model.addAttribute("subscriber", new SubscriberSimple());

		return "formPage";
	}
	
	@RequestMapping(value="form", method=RequestMethod.POST)
	public String submitForm(SubscriberSimple subscriber, Model model) {

		model.addAttribute("message", "Successfully saved person: " + subscriber.toString());

		return "formPage";
	}

/*		Use	<c:url var="addUrl" value="/contacts/submitForm"/>
<form:form method="post" action="${addUrl}" commandName="contact"
	id="contact" onsubmit="return validateContact(this)">
	In general, it is recomended to use c:url in every application internal
	instead of direct use of the url in a <a> tag*/

}
