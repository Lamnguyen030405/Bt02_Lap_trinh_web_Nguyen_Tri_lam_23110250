package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;

public interface IUserDao {
	
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	UserModel findByEmail(String email);
	
	void insert(UserModel user);
	
	void update(UserModel user);
	
	UserModel findByUserName(String username);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);
	
	
}
