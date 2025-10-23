package com.blogapp.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import com.blogapp.dao.PostDAO;
import com.blogapp.dao.CommentDAO;
import com.blogapp.model.Post;
import com.blogapp.model.Comment;
import com.blogapp.model.User;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null){
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            PostDAO dao = new PostDAO();

            if("create".equals(action)){
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                Post p = new Post();
                p.setUserId(user.getUserId());
                p.setTitle(title);
                p.setContent(content);
                dao.save(p);
                resp.sendRedirect("dashboard.jsp");

            } else if("update".equals(action)){
                int id = Integer.parseInt(req.getParameter("postId"));
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                Post p = new Post();
                p.setPostId(id);
                p.setTitle(title);
                p.setContent(content);
                dao.update(p);
                resp.sendRedirect("dashboard.jsp");

            } else if("delete".equals(action)){
                int id = Integer.parseInt(req.getParameter("postId"));
                dao.delete(id);
                resp.sendRedirect("dashboard.jsp");

            // ✅ Edit post
            } else if("edit".equals(action)){
                int id = Integer.parseInt(req.getParameter("postId"));
                Post p = dao.getById(id);
                req.setAttribute("post", p);
                req.getRequestDispatcher("editpost.jsp").forward(req, resp);

            // ✅ Add comment
            } else if("comment".equals(action)){
                int postId = Integer.parseInt(req.getParameter("postId"));
                String commentText = req.getParameter("comment");
                Comment c = new Comment();
                c.setPostId(postId);
                c.setUserId(user.getUserId());
                c.setComment(commentText);
                new CommentDAO().save(c);
                resp.sendRedirect("dashboard.jsp");
            }

        } catch(Exception e){
            throw new ServletException(e);
        }
    }
}

