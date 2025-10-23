package com.blogapp.dao;
import com.blogapp.model.Post;
import com.blogapp.util.DBCOnnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
	 public void save(Post p) throws Exception {
	        try (Connection con = DBCOnnection.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                "INSERT INTO posts(user_id,title,content) VALUES(?,?,?)");
	            ps.setInt(1, p.getUserId());
	            ps.setString(2, p.getTitle());
	            ps.setString(3, p.getContent());
	            ps.executeUpdate();
	        }
	    }

	    // Read all posts
	    public List<Post> getAll() throws Exception {
	        List<Post> list = new ArrayList<>();
	        try (Connection con = DBCOnnection.getConnection()) {
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery("SELECT * FROM posts ORDER BY created_at DESC");
	            while(rs.next()){
	                Post p = new Post();
	                p.setPostId(rs.getInt("post_id"));
	                p.setUserId(rs.getInt("user_id"));
	                p.setTitle(rs.getString("title"));
	                p.setContent(rs.getString("content"));
	                p.setCreatedAt(rs.getTimestamp("created_at"));
	                list.add(p);
	            }
	        }
	        return list;
	    }

	    // Get post by ID
	    public Post getById(int id) throws Exception {
	        try (Connection con = DBCOnnection.getConnection()) {
	            PreparedStatement ps = con.prepareStatement("SELECT * FROM posts WHERE post_id=?");
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if(rs.next()){
	                Post p = new Post();
	                p.setPostId(rs.getInt("post_id"));
	                p.setUserId(rs.getInt("user_id"));
	                p.setTitle(rs.getString("title"));
	                p.setContent(rs.getString("content"));
	                p.setCreatedAt(rs.getTimestamp("created_at"));
	                return p;
	            }
	            return null;
	        }
	    }

	    // Update post
	    public void update(Post p) throws Exception {
	        try (Connection con = DBCOnnection.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                "UPDATE posts SET title=?, content=? WHERE post_id=?");
	            ps.setString(1, p.getTitle());
	            ps.setString(2, p.getContent());
	            ps.setInt(3, p.getPostId());
	            ps.executeUpdate();
	        }
	    }

	    // Delete post
	    public void delete(int id) throws Exception {
	        try (Connection con = DBCOnnection.getConnection()) {
	            PreparedStatement ps = con.prepareStatement("DELETE FROM posts WHERE post_id=?");
	            ps.setInt(1, id);
	            ps.executeUpdate();
	        }
	    }

}
