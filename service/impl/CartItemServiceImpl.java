package by.htp.ishop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.CartItem;
import by.htp.ishop.entity.CartItemView;
import by.htp.ishop.entity.Item;
import by.htp.ishop.dao.CartItemDAO;
import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	private CartItemDAO cartItemDAO;

	@Override
	@Transactional
	public void addNewCartItem(Item item, Cart cart) throws ServiceException {

		int quantity = 0;
		try {
			CartItem cartItem = cartItemDAO.readCartItem(item, cart);
			if(cartItem == null) {
				quantity = 1;
				cartItemDAO.addNewCartItem(item, quantity, cart);
			}
			else {
				quantity = cartItem.getQuantity() + 1;
				cartItem.setQuantity(quantity);
				cartItemDAO.updateCartItem(cartItem);
			}
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public List<CartItemView> getCartItems(Cart cart) throws ServiceException{
		List<CartItemView> cartItems = new ArrayList<CartItemView>();
		try {
			cartItems = cartItemDAO.getCartItems(cart);
			if(!cartItems.isEmpty()) {
				for(CartItemView cartItemView : cartItems) {
					if(cartItemView.getQuantity() > 1) {
						double price = cartItemView.getQuantity() * cartItemView.getPrice();
						cartItemView.setPrice(price);
					}
				}
			}
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return cartItems;
	}

	@Override
	@Transactional
	public void deleteCartItem(int cartItemId) throws ServiceException {
		try {
			cartItemDAO.deleteCartItem(cartItemId);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public double getCartSum(List<CartItemView> items) {
		double sum = 0;
		for(CartItemView cartItem : items) {
			sum += cartItem.getPrice();
		}
		return sum;
	}

}
