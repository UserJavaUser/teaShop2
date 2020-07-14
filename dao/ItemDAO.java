package by.htp.ishop.dao;

import java.util.List;

import by.htp.ishop.dao.impl.DAOException;
import by.htp.ishop.entity.CategoryName;
import by.htp.ishop.entity.Item;

public interface ItemDAO {

	Item getItemById(int itemId) throws DAOException;

	//List<ItemView> findItemByParameter(String parameter, List<String> values) throws DAOException;*/

	List<Item> getAllItems()throws DAOException;

}
