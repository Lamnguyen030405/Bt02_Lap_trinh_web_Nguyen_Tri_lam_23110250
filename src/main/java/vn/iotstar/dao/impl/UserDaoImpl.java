package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnection;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnection implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<UserModel> findAll() {
		
		String sql = "Select * from users";
		
		List<UserModel> list = new ArrayList<>(); //tạo một list để truyền dữ liệu
		
		try {
			conn = super.getConnection(); //kết nối database
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) /*Next từng dòng đến cuối*/ {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id")); 
				user.setUsername(rs.getString("username")); 
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setImages(rs.getString("images"));
				user.setFullname(rs.getString("fullname"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreateDate(rs.getDate("createDate"));
				user.setStatus(rs.getInt("status"));
				user.setCode(rs.getString("code"));
				list.add(user); //Add vào
			}
			return list;
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserModel findById(int id) {
		
		String sql = "Select * from users where users.id = ?";
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id")); 
				user.setUsername(rs.getString("username")); 
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setImages(rs.getString("images"));
				user.setFullname(rs.getString("fullname"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreateDate(rs.getDate("createDate"));
				user.setStatus(rs.getInt("status"));
	            return user;
	        }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserModel findByEmail(String email) {
		String sql = "Select * from users where users.email = ?";
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id")); 
				user.setUsername(rs.getString("username")); 
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setImages(rs.getString("images"));
				user.setFullname(rs.getString("fullname"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreateDate(rs.getDate("createDate"));
				user.setStatus(rs.getInt("status"));
	            return user;
	        }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserModel findByUserName(String username) {
		
		String sql = "Select * from users where users.username = ? ";
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id")); 
				user.setUsername(rs.getString("username")); 
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setImages(rs.getString("images"));
				user.setFullname(rs.getString("fullname"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreateDate(rs.getDate("createDate"));
				user.setStatus(rs.getInt("status"));
	            return user;
	        }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean checkExistEmail(String email) {

		String sql = "Select * from users where email = ?";
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			ps.close();
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkExistUsername(String username) {
		String sql = "Select * from users where username = ?";
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			ps.close();
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		String sql = "Select * from users where phone = ?";
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
			ps.close();
			conn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public void insert(UserModel user) {
		
		String sql = "Insert into users(username, password, email, images, fullname, phone, roleid, createDate, code, status) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);

	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getPassword());
	        ps.setString(3, user.getEmail());
	        ps.setString(4, user.getImages());
	        ps.setString(5, user.getFullname());
	        ps.setString(6, user.getPhone());
	        ps.setInt(7, user.getRoleid());  
	        ps.setDate(8, user.getCreateDate());
	        ps.setString(9, user.getCode());
	        ps.setInt(10, user.getStatus());
	        
			ps.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void update(UserModel user) {
		
		String sql = "UPDATE users SET password = ? WHERE email = ?";
		
		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getEmail());
			
			ps.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	
//	public static void main(String[] args) {
//		UserDaoImpl userDao = new UserDaoImpl();
//		
//		UserModel u = new UserModel(
//			    "abc",                    // username
//			    "123",                    // password
//			    "hcmute@123.com",         // email
//			    "",                       // images
//			    "abcw",                   // fullname
//			    "02353",                  // phone
//			    1,                        // roleid (int)
//			    new java.sql.Date(System.currentTimeMillis()),
//			    "",
//			    1);               // createDate (java.util.Date)
//		
//		userDao.insert(u);
//		
//		List<UserModel> lst = userDao.findAll();
//		
//		for(UserModel user : lst) {
//			System.out.println(user);
//		}
//		
//		System.out.println(userDao.findById(1));
//	}
		
}
