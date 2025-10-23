package com.blogapp.model;
import java.sql.Timestamp;

public class Post {
	
	 private int postId;
	    private int userId;
	    private String title;
	    private String content;
	    private Timestamp createdAt;

	    public int getPostId() { return postId; }
	    public void setPostId(int postId) { this.postId = postId; }
	    public int getUserId() { return userId; }
	    public void setUserId(int userId) { this.userId = userId; }
	    public String getTitle() { return title; }
	    public void setTitle(String title) { this.title = title; }
	    public String getContent() { return content; }
	    public void setContent(String content) { this.content = content; }
	    public Timestamp getCreatedAt() { return createdAt; }
	    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
	
}


