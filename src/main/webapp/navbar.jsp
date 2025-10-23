<%@ page import="com.blogapp.model.User" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
  <div class="container">
    <a class="navbar-brand fw-bold" href="dashboard.jsp">MyBlogApp</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <%
            User sessionUser = (User) session.getAttribute("user");
            if(sessionUser != null){ 
        %>
            <li class="nav-item">
                <span class="nav-link text-white">Hello, <%= sessionUser.getName() %>!</span>
            </li>
            <li class="nav-item">
                <a class="btn btn-outline-light btn-sm ms-2" href="logout">Logout</a>
            </li>
        <% } else { %>
            <li class="nav-item"><a class="nav-link text-white" href="jsp/register.jsp">Register</a></li>
            <li class="nav-item"><a class="nav-link text-white" href="jsp/login.jsp">Login</a></li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>

