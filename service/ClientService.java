package by.htp.ishop.service;

import by.htp.ishop.entity.User;
import by.htp.ishop.service.impl.ServiceException;

public interface ClientService {
	boolean logIn(String login, String password) throws ServiceException;

	User getUserIdByLogin(String login) throws ServiceException;

	void registration(User user, String login, String password) throws ServiceException;
}
