package by.htp.ishop.service;

import java.util.List;

import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.CategoryName;
import by.htp.ishop.entity.Item;
import by.htp.ishop.service.impl.ServiceException;

public interface ItemService {
	//void addNewItem(Item item);

	Item getItemById(int itemId) throws ServiceException;

	//void updateItem(int itemId, String field);

	//void deleteItem(int itemId);

	//List<ItemView> findItemByParameter(String parameter, List<String> value) throws ServiceException;
	
	List<Item> getAllItems() throws ServiceException;

	List<CategoryName> getCategories() throws ServiceException;
	
}
