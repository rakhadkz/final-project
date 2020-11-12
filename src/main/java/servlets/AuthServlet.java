package servlets;

import models.Admin;
import models.Student;
import services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthServlet")
public class AuthServlet extends HttpServlet {
    HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        session = request.getSession();
        AuthService authService = new AuthService();
        if (session.getAttribute("user") != null){
            session.invalidate();
            //TODO Add here Cookie removing functionality
            return;
        }

        if (role.equals("student")){
            try {
                Student student = authService.authenticate(email, password);
                session.setAttribute("user", student);
                System.out.println("Successfully logged");
                //TODO Add some cookies
            } catch (Exception e) {
                request.setAttribute("student_auth_error", e.getMessage());
            }

        }
        else if (role.equals("admin")){
            try {
                Admin admin = authService.admin_authenticate(email, password);
                session.setAttribute("user", admin);
                System.out.println("Successfully!");
                //TODO Add some cookies
            } catch (Exception e) {
                request.setAttribute("admin_auth_error", e.getMessage());
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            // If user exists, you can redirect to main page (ex. response.redirect('main');)
            return;
        }
        request.getRequestDispatcher("./auth.jsp").forward(request, response);
    }
}
