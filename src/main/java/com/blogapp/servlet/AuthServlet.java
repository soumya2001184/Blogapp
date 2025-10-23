package com.blogapp.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import com.blogapp.dao.UserDAO;
import com.blogapp.model.User;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            if ("register".equals(action)) {
                String name  = req.getParameter("name");
                String email = req.getParameter("email");
                String pass  = req.getParameter("password");

                User u = new User();
                u.setName(name);
                u.setEmail(email);
                u.setPassword(pass);
                u.setRole("user");

                new UserDAO().save(u);
                resp.sendRedirect("login.jsp?msg=registered");

            } else if ("login".equals(action)) {
                String email = req.getParameter("email");
                String pass  = req.getParameter("password");

                User u = new UserDAO().findByEmail(email);
                if (u != null && pass.equals(u.getPassword())) {
                    HttpSession s = req.getSession();
                    s.setAttribute("user", u);
                    resp.sendRedirect("dashboard.jsp");
                } else {
                    resp.sendRedirect("login.jsp?error=1");
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

