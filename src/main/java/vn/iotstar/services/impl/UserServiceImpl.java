package vn.iotstar.services.impl;

import vn.iotstar.models.UserModel;
import vn.iotstar.services.IUserService;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.dao.IUserDao;

public class UserServiceImpl implements IUserService {

	//lấy toàn bộ hàm trong tầng Dao của user
	IUserDao userDao = new UserDaoImpl();
	
	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findByUserName(username);
		
		if(user != null && password.equals(user.getPassword())) {
			user.setStatus(1);
			return user;
		}
		return null;
	}

	@Override
	public UserModel findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public boolean register(String email, String password, String repeatPassword,String username, String fullname, String phone) {
		if (!this.isPasswordMatch(password, repeatPassword)) {
			return false;
		}
		if (this.checkExistUsername(username)) {
			return false;
		}
		long millis=System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		UserModel user = new UserModel(); 
		user.setUsername(username); 
		user.setPassword(password);
		user.setEmail(email);
		user.setFullname(fullname);
		user.setPhone(phone);
		user.setCreateDate(date);
		user.setRoleid(5);
		userDao.insert(user);
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public boolean isPasswordMatch(String password, String repeatPassword) {
		return password != null && password.equals(repeatPassword);
	}

	@Override
	public UserModel findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public void update(UserModel user) {
		userDao.update(user);
	}

	
}
