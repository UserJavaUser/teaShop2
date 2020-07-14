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
public class RegistrationController {

	private final static String USER_CART = "userCart";

	private static final Logger log = Logger.getLogger(RegistrationController.class);

	@Autowired
	private UserCart userCart;

	@Autowired
	private ClientService clientService;

	@Autowired
	private CartService cartService;

	@RequestMapping("/go-to-registration")
	public String showRegistrationPage() {
		return "registration";
	}

	@RequestMapping("/registration")
	public String login(@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName, 
			@RequestParam("email") String email, 
			@RequestParam("login") String login, @RequestParam("password") String password, Model model){
		User user = new User(firstName, lastName, email);

		try {
			clientService.registration(user, login, password);
			User savedUser = clientService.getUserIdByLogin(login);
			cartService.addNewCart(savedUser);
			Cart savedCart = cartService.getCardByUserId(user.getId());
			userCart.setUser(savedUser);
			userCart.setCart(savedCart);
			model.addAttribute(USER_CART, userCart);
			return "redirect:/items-list";
		} catch (ServiceException e) {
			log.error("Registration is failed", new ControllerException(e));
			return "error";
		}
		
	}

}
