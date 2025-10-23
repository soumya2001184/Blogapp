<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.blogapp.model.User, com.blogapp.model.Post, com.blogapp.model.Comment" %>
<%@ include file="navbar.jsp" %>

<%
    User u = (User) session.getAttribute("user");
    if(u == null){ 
        response.sendRedirect("jsp/login.jsp"); 
        return; 
    }
%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<div class="container mt-4">
    <h2 class="mb-4 text-primary">Welcome, <%= u.getName() %>!</h2>

    <!-- Create Post Form -->
    <div class="card mb-4 shadow-sm">
        <div class="card-body">
            <h5 class="card-title">Create New Post</h5>
            <form method="post" action="post">
                <input type="hidden" name="action" value="create">
                <div class="mb-3">
                    <input type="text" name="title" class="form-control" placeholder="Post Title" required>
                </div>
                <div class="mb-3">
                    <textarea name="content" class="form-control" rows="4" placeholder="Write something..." required></textarea>
                </div>
                <button type="submit" class="btn btn-success">Post</button>
            </form>
        </div>
    </div>

    <!-- All Posts -->
    <div class="row">
    <%
        try {
            java.util.List<Post> posts = new com.blogapp.dao.PostDAO().getAll();
            for(Post p : posts){
    %>
        <div class="col-md-6 mb-4">
            <div class="card shadow-sm">
                <div class="card-header bg-light">
                    <h5 class="card-title"><%= p.getTitle() %></h5>
                    <small>by User <%= p.getUserId() %> on <%= p.getCreatedAt() %></small>
                </div>
                <div class="card-body">
                    <p class="card-text"><%= p.getContent() %></p>

                    <!-- Edit / Delete Buttons -->
                    <form method="post" action="post" style="display:inline;">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="postId" value="<%= p.getPostId() %>">
                        <button class="btn btn-sm btn-warning">Edit</button>
                    </form>
                    <form method="post" action="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="postId" value="<%= p.getPostId() %>">
                        <button class="btn btn-sm btn-danger">Delete</button>
                    </form>
                </div>

                <!-- Comments Section -->
                <div class="card-footer">
                    <h6>Comments:</h6>
                    <%
                        java.util.List<Comment> comments = new com.blogapp.dao.CommentDAO().getByPost(p.getPostId());
                        for(Comment c : comments){
                    %>
                        <p><b>User <%= c.getUserId() %>:</b> <%= c.getComment() %></p>
                    <%
                        }
                    %>

                    <!-- Add Comment Form -->
                    <form method="post" action="post" class="mt-2">
                        <input type="hidden" name="action" value="comment">
                        <input type="hidden" name="postId" value="<%= p.getPostId() %>">
                        <div class="input-group">
                            <input type="text" name="comment" class="form-control" placeholder="Write a comment..." required>
                            <button class="btn btn-primary" type="submit">Comment</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    <%
            }
        } catch(Exception e) {
            out.println("<div class='alert alert-danger'>"+e+"</div>");
        }
    %>
    </div>
</div>
