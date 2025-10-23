<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.blogapp.model.User, com.blogapp.model.Post" %>
<%@ include file="navbar.jsp" %>

<%
    User u = (User) session.getAttribute("user");
    if(u == null){ response.sendRedirect("jsp/login.jsp"); return; }

    int postId = Integer.parseInt(request.getParameter("postId"));
    Post p = new com.blogapp.dao.PostDAO().getById(postId);
    if(p == null || p.getUserId() != u.getUserId()){
        response.sendRedirect("dashboard.jsp"); 
        return; 
    }
%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<div class="container mt-4">
    <h2 class="mb-4 text-primary">Edit Post</h2>

    <div class="card shadow-sm">
        <div class="card-body">
            <form method="post" action="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="postId" value="<%= p.getPostId() %>">
                <div class="mb-3">
                    <input type="text" name="title" class="form-control" value="<%= p.getTitle() %>" required>
                </div>
                <div class="mb-3">
                    <textarea name="content" class="form-control" rows="4" required><%= p.getContent() %></textarea>
                </div>
                <button type="submit" class="btn btn-success">Update Post</button>
                <a href="dashboard.jsp" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </div>
</div>
