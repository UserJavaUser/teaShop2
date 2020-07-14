package by.htp.ishop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.ishop.dao.CartItemDAO;
import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.CartItem;
import by.htp.ishop.entity.CartItemView;
import by.htp.ishop.entity.Item;

@Repository
public class CartItemDAOImpl implements CartItemDAO{

	private static final String SELECT_CART_ITEM = "select ci from CartItem ci where ci.item.id = :itemId AND ci.cart.id = :cartId";
	private static final String SELECT_CART_ITEM_FROM_CART = "select c.items from Cart c where c.id = :cartId";
	private static final String SELECT_ITEM = "select ci.item from CartItem ci where ci.item.id = :itemId";
	private static final String DELETE_CART_ITEM = "delete CartItem where id = :cartItemId";
	private static final String CART_ID = "cartId";
	private static final String ITEM_ID = "itemId";
	private static final String CART_ITEM_ID ="cartItemId";
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addNewCartItem(Item item, int quantity, Cart cart) throws DAOException {

		try {
			Session currentSession = sessionFactory.getCurrentSession();
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setQuantity(quantity);
			cartItem.setItem(item);
			// save
			currentSession.save(cartItem);
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public CartItem readCartItem(Item item, Cart cart) throws DAOException {
		CartItem cartItem = new CartItem();
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<CartItem> query = currentSession.createQuery(SELECT_CART_ITEM,
					CartItem.class); 
			query.setParameter(ITEM_ID, item.getId());
			query.setParameter(CART_ID, cart.getId());
			List<CartItem> customers = query.getResultList();
			if(!customers.isEmpty()) {
				cartItem = customers.get(0);
			}
			else {
				cartItem = null;
			}
		}catch(Exception e) {
			throw new DAOException(e);
		}
		return cartItem;
	}

	@Override
	public List<CartItemView> getCartItems(Cart cart) throws DAOException {
		List<CartItemView> cartItemViews = new ArrayList<>();
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<CartItem> query = currentSession.createQuery(SELECT_CART_ITEM_FROM_CART); 
			query.setParameter(CART_ID, cart.getId());
			List<CartItem> cartItems = query.getResultList();

			for(CartItem cartItem : cartItems) {
				String name = cartItem.getItem().getName();
				double price = cartItem.getItem().getPrice();
				CartItemView itemView = new CartItemView(cartItem, name, price);
				cartItemViews.add(itemView);
			}
			return cartItemViews;
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void updateCartItem(CartItem cartItem) throws DAOException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.update(cartItem);
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteCartItem(int cartItemId) throws DAOException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();			
			Query theQuery = currentSession.createQuery(DELETE_CART_ITEM);
			theQuery.setParameter(CART_ITEM_ID, cartItemId);
			theQuery.executeUpdate();		
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}

}
