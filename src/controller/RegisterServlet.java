package controller;

import model.User;
import model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rick on 30-8-2016.
 */
@WebServlet("/controller.RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("register post");

        String username = request.getParameter("gebruikersnaam");

        User user = UserModel.getInstance().findUser(username);
        if (user == null) {
            String password = request.getParameter("wachtwoord");
            String type = request.getParameter("type");

            if (type.equals("owner")) {
                UserModel.getInstance().addUser(new User(username, password, true));
            } else {
                UserModel.getInstance().addUser(new User(username, password, false));
            }

        } else {
            //fuckoff
        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("register get");
        System.out.println(request.getParameter("gebruikersnaam"));
        System.out.println(request.getParameter("wachtwoord"));
        System.out.println(request.getParameter("type"));
    }
}
