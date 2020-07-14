package by.htp.ishop.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.htp.ishop.service.impl.ServiceException;
@Controller
public class LogOutController {

	private static final Logger log = Logger.getLogger(LogOutController.class);

	@RequestMapping("/go-to-logout")
	public String showLoginPage(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession(true);
			session.invalidate();
			return "redirect:/items-list";
		}catch(Exception e) {
			log.error("Exception when log out", new ControllerException(e));
			return "error";
		}
	}

}
