package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;

public class CategoryServiceImpl implements ICategoryService{

	ICategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
	public void insert(CategoryModel category) {
		categoryDao.insert(category);
	}

	@Override
	public void update(CategoryModel category) {
		categoryDao.update(category);	
	}

	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public CategoryModel findById(int id) {
		return categoryDao.findById(id);
	}

	@Override
	public CategoryModel findByName(String name) {
		return categoryDao.findByName(name);
	}

	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public List<CategoryModel> search(String keyword) {
		return categoryDao.search(keyword);
	}

	
}
