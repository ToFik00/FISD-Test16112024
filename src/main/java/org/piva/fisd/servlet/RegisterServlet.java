package org.piva.fisd.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.piva.fisd.database.mapper.impl.UserRowMapper;
import org.piva.fisd.database.DataSource;
import org.piva.fisd.database.JdbcTemplate;
import org.piva.fisd.util.PasswordUtil;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.html");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String encryptPassword = PasswordUtil.encrypt(password);
        DataSource dataSource = new DataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        if (jdbcTemplate.execute("select * from users where login = ?", new UserRowMapper(), username).isEmpty()) {
            jdbcTemplate.execute("insert into users (login, password) values (?, ?)", new UserRowMapper(), username, encryptPassword);
            resp.sendRedirect("profile");
        }
    }
}
