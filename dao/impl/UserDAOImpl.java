package by.htp.ishop.dao.impl;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.ishop.dao.UserDAO;
import by.htp.ishop.entity.AuthData;
import by.htp.ishop.entity.User;

@Repository
public class UserDAOImpl implements UserDAO{

	private static final String LOG_IN = "from AuthData where login = :login";
	private static final String GET_USER = "select a.user from AuthData a where a.login = :login";

	private static final String ID = "id";
	private static final String LOGIN = "login";
	private static final String SALT = "salt";
	private static final String PASSWORD = "password";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String ADDRESS = "address";

	@Autowired
	private SessionFactory sessionFactory;

	public AuthData logIn(String login) throws DAOException {		
		
		AuthData authData = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query query = currentSession.createQuery(LOG_IN);
			query.setParameter(LOGIN, login);
			authData = (AuthData) query.getSingleResult();
		}catch(Exception e) {
			throw new DAOException(e);
		}
		return authData;
	}

	@Override
	public User getUserIdByLogin(String login) throws DAOException {
		User user = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query query = currentSession.createQuery(GET_USER);
			query.setParameter(LOGIN, login);
			user = (User)query.getSingleResult();
		}catch(Exception e) {
			throw new DAOException(e);
		}
		return user;
	}

	@Override
	public void registration(User user, AuthData authData) throws DAOException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			// save
			currentSession.save(user);
			currentSession.save(authData);
		}catch(Exception e) {
			throw new DAOException(e);
		}
	}

}
