package org.piva.fisd.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.piva.fisd.database.mapper.impl.LogRowMapper;
import org.piva.fisd.database.mapper.impl.UserRowMapper;
import org.piva.fisd.database.DataSource;
import org.piva.fisd.database.JdbcTemplate;
import org.piva.fisd.util.PasswordUtil;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String encryptPassword = PasswordUtil.encrypt(password);

        DataSource dataSource = new DataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        if (!jdbcTemplate.execute("select * from users where login = ? and password = ?", new UserRowMapper(), username, encryptPassword).isEmpty()) {
            jdbcTemplate.execute("insert into logs (date, login, issucceed) values (?, ?, ?)", new LogRowMapper(), sqlDate, username, true);
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            resp.sendRedirect("profile");
        }
        else {

            jdbcTemplate.execute("insert into logs (date, login, issucceed) values (?, ?, ?)", new LogRowMapper(), LocalDateTime.now(), username, false);
            resp.getWriter().println("<h3>Неверное имя или пароль, попробуйте ещё раз</h3>");
        }
    }
}
