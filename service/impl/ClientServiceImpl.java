package by.htp.ishop.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.ishop.dao.ItemDAO;
import by.htp.ishop.dao.UserDAO;
import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.AuthData;
import by.htp.ishop.entity.Item;
import by.htp.ishop.entity.User;
import by.htp.ishop.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public boolean logIn(String login, String password) throws ServiceException {

		try {
			AuthData authData = userDAO.logIn(login);
			if(authData == null) {
				return false;
			}
			byte[] salt = authData.getSalt();
			byte[] hashedPassword = EncodingUtil.getHash(password, salt);
			if(authData.getHash().length != hashedPassword.length) {
				return false;
			}
			if(Arrays.equals(hashedPassword, authData.getHash())) {
				return true;
			}

		} catch (DAOException e) {
			throw new ServiceException("User isn't found!");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Error while hashing");
		}
		return false; 
	}

	@Override
	@Transactional
	public User getUserIdByLogin(String login) throws ServiceException {
		User user = null;
		try {
			user = userDAO.getUserIdByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	@Transactional
	public void registration(User user, String login, String password) throws ServiceException {
		try {
			byte[]salt = EncodingUtil.getSalt();
			byte[]hash = EncodingUtil.getHash(password, salt);
			AuthData authData = new AuthData(login, salt, hash);
			authData.setUser(user);
			userDAO.registration(user, authData);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ServiceException("Error while hashing");
		}
		
	}

}
