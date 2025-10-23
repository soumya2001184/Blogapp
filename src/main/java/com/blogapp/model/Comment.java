package com.blogapp.model;

import java.sql.Timestamp;

public class Comment {
	    private int commentId;
	    private int postId;
	    private int userId;
	    private String comment;
	    private Timestamp createdAt;

	    public int getCommentId() { return commentId; }
	    public void setCommentId(int commentId) { this.commentId = commentId; }
	    public int getPostId() { return postId; }
	    public void setPostId(int postId) { this.postId = postId; }
	    public int getUserId() { return userId; }
	    public void setUserId(int userId) { this.userId = userId; }
	    public String getComment() { return comment; }
	    public void setComment(String comment) { this.comment = comment; }
	    public Timestamp getCreatedAt() { return createdAt; }
	    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }


}
