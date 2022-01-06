package com.mygarage.byhibernate.controller;

import com.mygarage.byhibernate.model.Role;
import com.mygarage.byhibernate.model.User;
import com.mygarage.byhibernate.service.BaseService;
import com.mygarage.byhibernate.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class UserAdminServlet extends HttpServlet {
    private BaseService<User> service;


    public void init() {
        service = new UserServiceImpl();
    }
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        String action = req.getServletPath();

        try {
            switch (action){
                case "/registration" -> registrationForm(req,resp);
                case "/login" -> loginForm(req, resp);
                case "/loginAction" -> loginUser(req, resp);
                case "/logoutAction" -> logoutUser(req, resp);
                case "/add" -> addUser(req, resp);
                case "/update" -> updateUser(req, resp);
                case "/delete" -> deleteUser(req, resp);
                case "/edit" -> showEditForm(req, resp);
                case "/list" -> listUser(req, resp);
                //case "/cabinet" ->
                default -> registrationForm(req, resp);
            }
        } catch (ServletException| IOException exc){
            throw new ServletException(exc);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        List<User> users = service.findAll();
        if (search != null) {
            users = users.stream().filter(user -> user.getName().contains(search) ||
                    user.getLastName().contains(search) ||
                    user.getCity().contains(search)).collect(Collectors.toList());
        }
        req.setAttribute("listUser", users);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-user.jsp");
        dispatcher.forward(req, resp);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String city = req.getParameter("city");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        Role role = Role.USER;
        User user = new User(name, lastName, city, age);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        boolean isAdded = service.create(user);
        if (isAdded) {
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole().name());
        }
        resp.sendRedirect("list");
    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = service.findByLoginAndPassword(login, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole().name());
            if (user.getRole().name()== "ADMIN"){
                resp.sendRedirect("list");
            } else {
                resp.sendRedirect("cabinet");
            }
        } else {
            req.setAttribute("invalidLoginOrPassword", true);
            ServletContext servletContext = getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/login.jsp");
            dispatcher.forward(req, resp);

        }

    }

    private void logoutUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("login");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String city = req.getParameter("city");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        User user = new User(name, lastName, city, age);
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        service.update(user);
        resp.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteById(id);
        resp.sendRedirect("list");
    }

    private void registrationForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/registration.jsp");
        dispatcher.forward(req, resp);
    }

    private void loginForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/login.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existedUser = service.findById(id);
        req.setAttribute("existedUser", existedUser);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/edit-user.jsp");
        dispatcher.forward(req, resp);
    }


}
