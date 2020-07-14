package by.htp.ishop.dao;

import java.util.List;

import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.CartItem;
import by.htp.ishop.entity.CartItemView;
import by.htp.ishop.entity.Item;

public interface CartItemDAO {

	void addNewCartItem(Item item, int quantity, Cart cart) throws DAOException;

	CartItem readCartItem(Item item, Cart cart) throws DAOException;

	void updateCartItem(CartItem cartItem) throws DAOException;

	void deleteCartItem(int cartItemId) throws DAOException;

	List<CartItemView> getCartItems(Cart cart)throws DAOException;

}
