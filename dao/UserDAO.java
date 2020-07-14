package by.htp.ishop.dao;

import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.AuthData;
import by.htp.ishop.entity.User;

public interface UserDAO {
	AuthData logIn(String login) throws DAOException;

	void registration(User user, AuthData authData) throws DAOException;

	User getUserIdByLogin(String login) throws DAOException;

}
