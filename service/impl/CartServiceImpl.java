package by.htp.ishop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.ishop.dao.CartDAO;
import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.User;
import by.htp.ishop.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDAO cartDAO;
	
	@Override
	@Transactional
	public void addNewCart(User user) throws ServiceException {
		try {
			cartDAO.addNewCart(user);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public Cart getCardByUserId(int userId) throws ServiceException {
		try {
			Cart cart = cartDAO.getCardByUserId(userId);
			return cart;
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

}
