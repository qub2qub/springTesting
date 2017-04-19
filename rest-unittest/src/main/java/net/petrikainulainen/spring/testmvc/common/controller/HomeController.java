package net.petrikainulainen.spring.testmvc.common.controller;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Petri Kainulainen
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    protected static final String HOME_VIEW = "index";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHomePage() {
        LOGGER.debug("Rendering home page.");
        return HOME_VIEW;
    }

    @RequestMapping(value="/session", method = RequestMethod.GET)
    public String showSessionPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        LOGGER.debug("UserId: {}", user);
        model.addAttribute("modelUser", user);
        return "session";
    }
}
