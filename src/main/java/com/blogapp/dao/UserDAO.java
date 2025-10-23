package com.blogapp.dao;

import com.blogapp.model.User;
import com.blogapp.util.DBCOnnection;

import java.sql.*;

public class UserDAO {
	  public User findByEmail(String email) throws Exception {
	        try (Connection con = DBCOnnection.getConnection()) {
	            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=?");
	            ps.setString(1, email);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                User u = new User();
	                u.setUserId(rs.getInt("user_id"));
	                u.setName(rs.getString("name"));
	                u.setEmail(rs.getString("email"));
	                u.setPassword(rs.getString("password"));
	                u.setRole(rs.getString("role"));
	                return u;
	            }
	            return null;
	        }
	    }

	    public void save(User u) throws Exception {
	        try (Connection con = DBCOnnection.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)");
	            ps.setString(1, u.getName());
	            ps.setString(2, u.getEmail());
	            ps.setString(3, u.getPassword());
	            ps.setString(4, u.getRole());
	            ps.executeUpdate();
	        }
	    }
	}

