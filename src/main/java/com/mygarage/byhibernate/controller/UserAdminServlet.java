package com.mygarage.byhibernate.controller;

import com.mygarage.byhibernate.model.*;
import com.mygarage.byhibernate.service.BaseService;
import com.mygarage.byhibernate.service.BaseUserService;
import com.mygarage.byhibernate.service.impl.CarServiceImpl;
import com.mygarage.byhibernate.service.impl.ExpensesServiceImpl;
import com.mygarage.byhibernate.service.impl.UserServiceImpl;
import com.mysql.cj.Query;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet("/")
public class UserAdminServlet extends HttpServlet {
    private BaseUserService<User> service;
    private BaseService<Car> carService;
    private BaseService<Expenses> expensesService;

    public void init() {
        service = new UserServiceImpl();
        carService = new CarServiceImpl();
        expensesService = new ExpensesServiceImpl();
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
                case "/updatecar" -> updateCar(req, resp);
                case "/delete" -> deleteUser(req, resp);
                case "/deletecar" -> deleteCar(req, resp);
                case "/edit" -> showEditForm(req, resp);
                case "/editcar" -> showCarEditForm(req, resp);
                case "/list" -> listUser(req, resp);
                case "/cabinet" -> userCabinet(req,resp);
                case "/addñar" -> addCarForm(req, resp);
                case "/addnewcar" -> addCar(req, resp);
                case "/addexpense" -> addExpenseForm(req, resp);
                case "/addnewexpense" -> addExpense(req, resp);
                case "/listcar" -> listCars(req,resp);
                case "/info" -> informationForm(req, resp);
                case "/userexpenses" -> listUserExpenses(req,resp);
                case "/carexpenses" -> listCarExpenses(req,resp);
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
            cars = cars.stream().filter(car -> car.getBrand().contains(search)&&car.getModel().contains(search)).collect(Collectors.toList());
        }
        req.setAttribute("listCars", cars);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-cars.jsp");
        dispatcher.forward(req, resp);
    }

    private void listCarExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Car car = carService.findById(id);
        req.setAttribute("listCarExpenses", car.getExpenses());
        req.setAttribute("id", id);
        req.setAttribute("car", car);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-carexpenses.jsp");
        dispatcher.forward(req, resp);
    }

    private void listUserExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user=(User) session.getAttribute("user");
        List<Car> cars = user.getCars();
        List<Expenses> expenses = new ArrayList<Expenses>();
        for (Car car: cars){
            expenses.addAll( car.getExpenses());
        }
        String sortByDateAsc = req.getParameter("sortByDateAsc");
//        if (sortByDateAsc==){
//            expenses = expenses.s
       // }
        //Query query = session.createQuery(hql);
       // List<Expenses> expenses = cars.stream().filter(car -> car.getExpenses()).collect.toList();
//        if (search != null) {
//            cars = cars.stream().filter(car -> car.getBrand().contains(search)||car.getModel().contains(search)).collect(Collectors.toList());
//        }
        req.setAttribute("listUserExpenses", expenses);
        req.setAttribute("cars", cars);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-userexpenses.jsp");
        dispatcher.forward(req, resp);
    }

    private void userCabinet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user=(User) session.getAttribute("user");
        long userId = user.getId();
        req.setAttribute("cars", service.findById(userId).getCars());
//        req.setAttribute("cars", user.getCars());
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
        EngineType engineType1 = EngineType.valueOf(engineType.toUpperCase(Locale.ROOT));
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

    private void addExpense(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        LocalDate date = LocalDate.parse(req.getParameter("date")) ;
        String typeOfExpense = req.getParameter("typeOfExpense");
        int price = Integer.parseInt(req.getParameter("price"));
        String commentExp = req.getParameter("commentexp");
        Expenses expense = new Expenses(date,typeOfExpense,price,commentExp);
        //expensesService.create(expense);
        Car car = carService.findById(id);
        List<Expenses> expenses = car.getExpenses();
        expenses.add(expense);
        car.setExpenses(expenses);
        carService.update(car);
        //req.setAttribute("expenses", expenses);
        resp.sendRedirect("carexpenses?id="+id);
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

    private void updateCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String comment = req.getParameter("comment");
        int mark = Integer.parseInt(req.getParameter("mark"));
        Car car = carService.findById(id);
        car.setMark(mark);
        car.setComment(comment);
        carService.update(car);
        resp.sendRedirect("cabinet");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteById(id);
        resp.sendRedirect("list");
    }

    private void deleteCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        carService.deleteById(id);
        HttpSession session = req.getSession();
        User user=(User) session.getAttribute("user");
        User userBd = service.findById(user.getId());
        List<Car> cars = userBd.getCars();
        service.update(userBd);
        session.setAttribute("user", userBd);
        resp.sendRedirect("cabinet");
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

    private void addExpenseForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/addexpense.jsp");
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
        // List<Car> cars = existedUser.getCars();
        req.setAttribute("existedUser", existedUser);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/edit-user.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCarEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Car existedCar = carService.findById(id);
        req.setAttribute("existedCar", existedCar);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/edit-car.jsp");
        dispatcher.forward(req, resp);
    }

    private void informationForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/info.jsp");
        dispatcher.forward(req, resp);
    }


}
