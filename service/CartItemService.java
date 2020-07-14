package by.htp.ishop.service;

import java.util.List;

import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.CartItem;
import by.htp.ishop.entity.CartItemView;
import by.htp.ishop.entity.Item;
import by.htp.ishop.service.impl.ServiceException;

public interface CartItemService {

	void addNewCartItem(Item item, Cart cart) throws ServiceException;

	void deleteCartItem(int cartItemId) throws ServiceException;

	List<CartItemView> getCartItems(Cart cart) throws ServiceException;

	double getCartSum(List<CartItemView> items);

}
