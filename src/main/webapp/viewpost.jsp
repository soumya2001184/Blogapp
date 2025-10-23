<%@ page import="com.blogapp.dao.*,com.blogapp.model.*,java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    User u = (User) session.getAttribute("user");
    if (u == null) {
        response.sendRedirect("jsp/login.jsp");
        return;
    }

    int postId = Integer.parseInt(request.getParameter("id"));
    PostDAO postDao = new PostDAO();
    CommentDAO commentDao = new CommentDAO();
    Post post = postDao.getById(postId);
    List<Comment> comments = commentDao.getByPost(postId);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= post.getTitle() %> | Blog</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <div class="container mt-4">
        <div class="card p-4 shadow-sm">
            <h2><%= post.getTitle() %></h2>
            <p class="text-muted">Posted on <%= post.getCreatedAt() %></p>
            <p><%= post.getContent() %></p>
        </div>

        <div class="mt-4">
            <h4>💬 Comments</h4>
            <% if (comments.isEmpty()) { %>
                <p>No comments yet.</p>
            <% } else { %>
                <% for (Comment c : comments) { %>
                    <div class="border rounded p-2 mb-2">
                        <strong>User <%= c.getUserId() %>:</strong> <%= c.getComment() %>
                        <div class="text-muted small"><%= c.getCreatedAt() %></div>
                    </div>
                <% } %>
            <% } %>

            <form action="CommentServlet" method="post" class="mt-3">
                <input type="hidden" name="postId" value="<%= postId %>">
                <textarea name="comment" class="form-control" placeholder="Write a comment..." required></textarea>
                <button class="btn btn-primary mt-2">Submit</button>
            </form>
        </div>
    </div>
</body>
</html>
