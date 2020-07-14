package by.htp.ishop.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.htp.ishop.bean.UserCart;
import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.CartItemView;
import by.htp.ishop.entity.CategoryName;
import by.htp.ishop.entity.Item;
import by.htp.ishop.service.CartItemService;
import by.htp.ishop.service.ItemService;
import by.htp.ishop.service.impl.ServiceException;

@Controller
@Scope("request")

public class ItemsController {

	private final static String GOODS = "goods";
	private final static String USER_CART = "userCart";
	private final static String CATEGORIES = "categories";
	private final static String CART_ITEMS = "cart_items";
	private final static String SUM = "sum";

	private static final Logger log = Logger.getLogger(ItemsController.class);

	@Autowired
	private UserCart userCart;

	@Autowired
	private ItemService itemService;

	@Autowired
	private CartItemService cartItemService;

	@RequestMapping("/")
	public String showMain(Model theModel) {
		return "redirect:/items-list";
	}

	@RequestMapping("/items-list")
	public String listItems(Model theModel) {
		try {
			List<Item> items = itemService.getAllItems();
			theModel.addAttribute(GOODS, items);
			theModel.addAttribute(USER_CART, userCart);
			List<CategoryName> enumValues = itemService.getCategories();
			theModel.addAttribute(CATEGORIES, enumValues);
			return "main";
		} catch (ServiceException e) {
			log.error("Exception while displaying item list", new ControllerException(e));
			return "error";
		}
	}

	@RequestMapping("/add-to-cart")
	public String addToCart(@RequestParam("itemId") String itemId, Model theModel) {
		try {
			Cart cart = userCart.getCart();
			Item item = itemService.getItemById(Integer.parseInt(itemId));
			cartItemService.addNewCartItem(item, cart);
			return "redirect:/items-list";
		} catch (NumberFormatException | ServiceException e) {
			log.error("Exception while adding to the Cart", new ControllerException(e));
			return "error";
		}
	}

	@RequestMapping("/open-cart")
	public String openCart(Model theModel) {
		try {
			Cart cart = userCart.getCart();

			List<Item> items = itemService.getAllItems();
			List<CartItemView> cartItemViews = cartItemService.getCartItems(cart);
			double sum = cartItemService.getCartSum(cartItemViews);

			theModel.addAttribute(GOODS, items);
			theModel.addAttribute(USER_CART, userCart);
			theModel.addAttribute(CART_ITEMS, cartItemViews);
			theModel.addAttribute(SUM, sum);
			return "cart";
		} catch (ServiceException e) {
			log.error("Exception when open the Cart", new ControllerException(e));
			return "error";
		}
	}

	@RequestMapping("/delete-cart-item")
	public String deleteCartItems(@RequestParam("cartItemId") String cartItemId, Model theModel) {
		try {
			cartItemService.deleteCartItem(Integer.parseInt(cartItemId));
			return "redirect:/open-cart";
		} catch (NumberFormatException | ServiceException e) {
			log.error("Exception when delete the item", new ControllerException(e));
			return "error";
		}
	}

}
