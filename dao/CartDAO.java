package by.htp.ishop.dao;

import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.Cart;
import by.htp.ishop.entity.User;

public interface CartDAO {

	void addNewCart(User user) throws DAOException;

	Cart getCardByUserId(int userId) throws DAOException;

}
