package com.mygarage.byhibernate.controller;

import com.mygarage.byhibernate.model.Car;
import com.mygarage.byhibernate.model.EngineType;
import com.mygarage.byhibernate.model.Role;
import com.mygarage.byhibernate.model.User;
import com.mygarage.byhibernate.service.BaseService;
import com.mygarage.byhibernate.service.impl.CarServiceImpl;
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
    private BaseService<Car> carService;


    public void init() {
        service = new UserServiceImpl();
        carService = new CarServiceImpl();
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
                case "/cabinet" -> userCabinet(req,resp);
                case "/addñar" -> addCarForm(req, resp);
                case "/addnewcar" -> addCar(req, resp);
                case "/listcar" -> listCars(req,resp);
                case "/info" -> informationForm(req, resp);
                default -> registrationForm(req, resp);
            }
        } catch (ServletException| IOException exc){
            throw new ServletException(exc);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
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

    private void listCars(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        List<Car> cars = carService.findAll();
        if (search != null) {
            cars = cars.stream().filter(car -> car.getBrand().contains(search)||car.getModel().contains(search)).collect(Collectors.toList());
        }
        req.setAttribute("listCars", cars);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-cars.jsp");
        dispatcher.forward(req, resp);
    }

    private void userCabinet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user=(User) session.getAttribute("user");
        List<Car> cars = user.getCars();
        req.setAttribute("userCabinet", cars);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/user-cabinet.jsp");
        dispatcher.forward(req, resp);
    }

    private void addCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String bodyType = req.getParameter("bodyType");
        String color = req.getParameter("color");
        String comment = req.getParameter("comment");
        double engineVolume = Double.parseDouble(req.getParameter("engineVolume"));
        int yearOfManufacture = Integer.parseInt(req.getParameter("yearOfManufacture"));
        int mark = Integer.parseInt(req.getParameter("mark"));
        String engineType = req.getParameter("engineType");
        EngineType engineType1 = null;
        switch (engineType){
            case "petrol"-> engineType1 = EngineType.PETROL;
            case "diesel"-> engineType1 = EngineType.DIESEl;
            case "gas"-> engineType1 = EngineType.GAS;
            case "hybrid"-> engineType1 = EngineType.HYBRID;
            case "electric"-> engineType1 = EngineType.ELECTRIC;
        }
        Car car = new Car(brand,model,bodyType, color, comment, engineVolume, yearOfManufacture, mark, engineType1);

        User user=(User) session.getAttribute("user");
        User userBd = service.findById(user.getId());
        List<Car> cars = userBd.getCars();
        cars.add(car);
        userBd.setCars(cars);
        service.update(userBd);
        session.setAttribute("user", userBd);
        resp.sendRedirect("cabinet");
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
        resp.sendRedirect("cabinet");
    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = service.findByLoginAndPassword(login, password);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("userRole", user.getRole().name());
            if (user.getRole().name().equals("ADMIN")){
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
        Role role = Role.USER;
        user.setRole(role);
        //List<Car> cars = user;
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

    private void addCarForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/addcar.jsp");
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
        List<Car> cars = existedUser.getCars();
        req.setAttribute("existedUser", existedUser);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/edit-user.jsp");
        dispatcher.forward(req, resp);
    }

    private void informationForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/info.jsp");
        dispatcher.forward(req, resp);
    }


}
