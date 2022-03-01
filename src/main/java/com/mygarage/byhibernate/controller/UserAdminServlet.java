package com.mygarage.byhibernate.controller;

import com.mygarage.byhibernate.model.*;
import com.mygarage.byhibernate.service.BaseService;
import com.mygarage.byhibernate.service.UserService;
import com.mygarage.byhibernate.service.ExpensesService;
import com.mygarage.byhibernate.service.impl.CarServiceImpl;
import com.mygarage.byhibernate.service.impl.ExpensesServiceImpl;
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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/")
public class UserAdminServlet extends HttpServlet {
    private UserService userService;
    private BaseService<Car> carService;
    private ExpensesService expensesService;

    public void init() {
        userService = new UserServiceImpl();
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
                case "/addcar" -> addCarForm(req, resp);
                case "/addnewcar" -> addCar(req, resp);
                case "/addexpense" -> addExpenseForm(req, resp);
                case "/addnewexpense" -> addExpense(req, resp);
                case "/deleteexpense" -> deleteExpense(req, resp);
                case "/sumExpense" -> sumExpense(req, resp);
                case "/listcar" -> listCars(req,resp);
                case "/info" -> informationForm(req, resp);
                case "/userexpenses" -> listUserExpenses(req,resp);
                case "/carexpenses" -> listCarExpenses(req,resp);
                case "/ratingcalc" -> ratingCalc(req, resp);
                default ->  registrationForm(req, resp);

            }
        } catch (ServletException| IOException exc){
            throw new ServletException(exc);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        doGet(req, resp);
    }

    private void ratingCalc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brandSearch = req.getParameter("search");
        Set<Car> cars = carService.findAll();
        Set<Car> carsByModel = cars.stream().filter(car ->car.getBrand().equals(brandSearch)).collect(Collectors.toSet());
        int counter=0;
        double sumMark =0;
        for (Car car: carsByModel){
            sumMark += car.getMark();
            counter++;
        }
        double rating = sumMark/counter;
        resp.sendRedirect("listcar?search="+brandSearch+"&rating="+rating);

    }


    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        Set<User> users = userService.findAll();
        if (search != null) {
            users = users.stream().filter(user -> user.getName().contains(search) ||
                    user.getLastName().contains(search)).collect(Collectors.toSet());
        }
        req.setAttribute("listUser", users);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-user.jsp");
        dispatcher.forward(req, resp);
    }

    private void listCars(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        Set<Car> cars = carService.findAll();
        if (search != null) {
            cars = cars.stream().filter(car -> car.getBrand().contains(search)).collect(Collectors.toSet());
            req.setAttribute("search", search);
        }
        req.setAttribute("listCars", cars);
        String rating = req.getParameter("rating");
        req.setAttribute("rating", rating);
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
        String sumExpOut= req.getParameter("sumExpOut");
        req.setAttribute("sumExpOut",sumExpOut);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-carexpenses.jsp");
        dispatcher.forward(req, resp);
    }

    private void sumExpense (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            LocalDate fromDate = LocalDate.parse(req.getParameter("fromDate"));
            LocalDate toDate = LocalDate.parse(req.getParameter("toDate"));
            String typeOfExpense = req.getParameter("typeOfExpense");
            String sumExpOut = expensesService.sumExpense(fromDate, toDate, String.valueOf(id), typeOfExpense);
            req.setAttribute("id", id);
            if (sumExpOut.equals("null")) resp.sendRedirect("carexpenses?id=" + id + "&sumExpOut=check the entered parameters");
            else resp.sendRedirect("carexpenses?id="+id+"&sumExpOut="+sumExpOut);
        }catch (DateTimeParseException e){
            resp.sendRedirect("carexpenses?id=" + id + "&sumExpOut=select date");
        }
    }

    private void listUserExpenses(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user=(User) session.getAttribute("user");
        Set<Car> cars = user.getCars();
        Set<Expenses> expenses = new LinkedHashSet<Expenses>();
        for (Car car: cars){
            expenses.addAll( car.getExpenses());
        }
        req.setAttribute("listUserExpenses", expenses);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-userexpenses.jsp");
        dispatcher.forward(req, resp);
    }

    private void userCabinet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user=(User) session.getAttribute("user");
        long userId = user.getId();
        req.setAttribute("cars", userService.findById(userId).getCars());
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
        EngineType engineType1 = EngineType.fromString(engineType);
        Car car = new Car(brand,model,bodyType, color, comment);
        car.setEngineVolume(engineVolume);
        car.setYearOfManufacture(yearOfManufacture);
        car.setMark(mark);
        car.setEngineType(engineType1);
        User user=(User) session.getAttribute("user");
        User userBd = userService.findById(user.getId());
        Set<Car> cars = userBd.getCars();
        cars.add(car);
        userBd.setCars(cars);
        userService.update(userBd);
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
        Car car = carService.findById(id);
        Set<Expenses> expenses = car.getExpenses();
        expenses.add(expense);
        car.setExpenses(expenses);
        carService.update(car);
        resp.sendRedirect("carexpenses?id="+id);
    }


    private void deleteExpense(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String carId = req.getParameter("carid");
        expensesService.deleteById(id);
        resp.sendRedirect("carexpenses?id="+carId);
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String city = req.getParameter("city");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password1 = req.getParameter("password1");
        int age = Integer.parseInt(req.getParameter("age"));
        Role role = Role.USER;
        User user = new User(name, lastName, city, age);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        if (userService.isExistByLogin(login)){
            req.setAttribute("isExistLogin", true);
            ServletContext servletContext = getServletContext();
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/registration.jsp");
            dispatcher.forward(req, resp);
        } else {
            boolean isAdded = userService.create(user);
            if (isAdded) {
                session.setAttribute("user", user);
                session.setAttribute("userRole", user.getRole().name());
            }
            resp.sendRedirect("cabinet");
        }
    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = userService.findByLoginAndPassword(login, password);
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
        userService.update(user);
        resp.sendRedirect("list");
    }

    private void updateCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String comment = req.getParameter("comment");
        Car car = carService.findById(id);
        try {
            int mark = Integer.parseInt(req.getParameter("mark"));
            car.setMark(mark);
            car.setComment(comment);
            carService.update(car);
            resp.sendRedirect("cabinet");
        }catch (NumberFormatException e){
            int lastMark = car.getMark();
            car.setMark(lastMark);
            car.setComment(comment);
            carService.update(car);
            resp.sendRedirect("cabinet");
        }

    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteById(id);
        resp.sendRedirect("list");
    }

    private void deleteCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        carService.deleteById(id);
        HttpSession session = req.getSession();
        User user=(User) session.getAttribute("user");
        User userBd = userService.findById(user.getId());
        Set<Car> cars = userBd.getCars();
        userService.update(userBd);
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
        User existedUser = userService.findById(id);
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
