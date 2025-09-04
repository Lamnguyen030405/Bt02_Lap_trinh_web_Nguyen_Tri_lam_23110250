package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);
	
	UserModel findByUserName(String username);
	
	void update(UserModel user);
	
	boolean register(String email, String password, String repeatPassword, String username, String fullname, String phone);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);
	
	boolean isPasswordMatch(String password, String repeatPassword);
	
	UserModel findByEmail(String email);
}
