package com.jpa.controller.web;

import com.jpa.dao.UserDB;
import com.jpa.dao.impl.UserDBImpl;
import com.jpa.entity.UserEntity;
import com.jpa.utils.HibernateUtil;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultMessage = "";
        String resultPost = "danger";;
        try {
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            if (!userService.emailExists(email)) {
                Long id = userService.insert(new UserEntity(firstName,lastName,email));
                resultMessage = new StringBuilder("Insert ").append(id).append(" successfull!").toString();
                resultPost = "success";
            }
            else resultMessage = "email already exists!";

        }
        catch (Exception e) {
            resultMessage = e.getMessage();
        }
        request.setAttribute("resultMessage", resultMessage);
        request.setAttribute("resultPost", resultPost);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(request, response);
    }
}
