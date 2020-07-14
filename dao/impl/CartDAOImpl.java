package by.htp.ishop.dao.impl;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.ishop.dao.CartDAO;
import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.User;

@Repository
public class CartDAOImpl implements CartDAO{

	private static final String ID = "id";
	private static final String USER_ID = "userId";
	private static final String GET_CART = "select c from Cart c where c.user.id = :userId";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addNewCart(User user) throws DAOException {
		Cart cart = new Cart();
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			cart.setUser(user);
			currentSession.save(cart);
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Cart getCardByUserId(int userId) throws DAOException {
		Cart cart = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query query = currentSession.createQuery(GET_CART);
			query.setParameter(USER_ID, userId);
			cart = (Cart)query.getSingleResult();
			return cart;
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}

}
