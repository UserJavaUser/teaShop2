package by.htp.ishop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.ishop.dao.ItemDAO;
import by.htp.ishop.entity.Item;

@Repository
public class ItemDAOImpl implements ItemDAO{
	
	private static final String GET_ALL_ITEMS = "from Item";

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Item> getAllItems() throws DAOException {
		List<Item> items = null;
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Item> theQuery = currentSession.createQuery(GET_ALL_ITEMS, Item.class);
			items = theQuery.getResultList();
		}catch(Exception e) {
			throw new DAOException(e);
		}
		return items;
	}

	@Override
	public Item getItemById(int itemId) throws DAOException {
		Item item = new Item();
		try{
			Session currentSession = sessionFactory.getCurrentSession();
			item = currentSession.get(Item.class, itemId);
		}catch(Exception e) {
			throw new DAOException(e);
		}
		return item;
	}

}
