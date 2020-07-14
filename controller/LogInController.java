package by.htp.ishop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.htp.ishop.bean.UserCart;

import by.htp.ishop.entity.Cart;

import by.htp.ishop.entity.User;
import by.htp.ishop.service.CartService;
import by.htp.ishop.service.ClientService;
import by.htp.ishop.service.impl.ServiceException;

@Controller
@Scope("request")

public class LogInController{

	private final static String USER_CART = "userCart";

	private static final Logger log = Logger.getLogger(LogInController.class);

	@Autowired
	private UserCart userCart;

	@Autowired
	private ClientService clientService;

	@Autowired
	private CartService cartService;

	@RequestMapping("/go-to-login")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping("/login")
	public String login(@RequestParam("login") String login, @RequestParam("password") String password, Model model){
		try {
			boolean isLoggedIn = clientService.logIn(login, password);
			if(isLoggedIn) {
				User user = clientService.getUserIdByLogin(login);
				Cart cart = cartService.getCardByUserId(user.getId());
				userCart.setUser(user);
				userCart.setCart(cart);
				model.addAttribute(USER_CART, userCart);
			}
			return "redirect:/items-list";
		}catch(ServiceException e) {
			log.error("Exception when login", new ControllerException(e));
			return "error";
		}
	}

}


