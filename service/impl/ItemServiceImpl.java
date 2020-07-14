package by.htp.ishop.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.ishop.dao.ItemDAO;
import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.CategoryName;
import by.htp.ishop.entity.Item;
import by.htp.ishop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemDAO itemDAO;

	@Override
	@Transactional
	public List<Item> getAllItems() throws ServiceException{
		List<Item> items = null;
		try{
			items = itemDAO.getAllItems();
		}catch (DAOException e){
			throw new ServiceException(e);
		}
		return items;
	}

	@Override
	@Transactional
	public Item getItemById(int itemId) throws ServiceException {
		Item item = new Item();
		try{
			item = itemDAO.getItemById(itemId);
		}catch (DAOException e){
			throw new ServiceException(e);
		}
		return item;
	}

	@Override
	public List<CategoryName> getCategories() throws ServiceException {
		List<CategoryName> enumValues = Arrays.asList(CategoryName.values());
		return enumValues;
	}

}
