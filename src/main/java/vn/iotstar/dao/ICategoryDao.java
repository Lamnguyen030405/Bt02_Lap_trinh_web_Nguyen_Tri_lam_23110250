package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.CategoryModel;

public interface ICategoryDao {

	void insert(CategoryModel category);
	void update(CategoryModel category);
	void delete(int id);
	CategoryModel findById(int id);
	CategoryModel findByName(String name);
	List<CategoryModel> findAll();
	List<CategoryModel> search(String keyword);
}
