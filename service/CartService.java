package by.htp.ishop.service;


import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.User;
import by.htp.ishop.service.impl.ServiceException;

public interface CartService {
	void addNewCart(User user) throws ServiceException;

	Cart getCardByUserId(int userId) throws ServiceException;
}
