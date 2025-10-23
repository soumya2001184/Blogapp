package com.blogapp.dao;

import com.blogapp.model.Comment;
import com.blogapp.util.DBCOnnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
	  public void save(Comment c) throws Exception {
	        try(Connection con = DBCOnnection.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                "INSERT INTO comments(post_id,user_id,comment) VALUES(?,?,?)");
	            ps.setInt(1, c.getPostId());
	            ps.setInt(2, c.getUserId());
	            ps.setString(3, c.getComment());
	            ps.executeUpdate();
	        }
	    }

	    // Get comments by post_id
	    public List<Comment> getByPost(int postId) throws Exception {
	        List<Comment> list = new ArrayList<>();
	        try(Connection con = DBCOnnection.getConnection()) {
	            PreparedStatement ps = con.prepareStatement(
	                "SELECT * FROM comments WHERE post_id=? ORDER BY created_at ASC");
	            ps.setInt(1, postId);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()){
	                Comment c = new Comment();
	                c.setCommentId(rs.getInt("comment_id"));
	                c.setPostId(rs.getInt("post_id"));
	                c.setUserId(rs.getInt("user_id"));
	                c.setComment(rs.getString("comment"));
	                c.setCreatedAt(rs.getTimestamp("created_at"));
	                list.add(c);
	            }
	        }
	        return list;
	    }

}
