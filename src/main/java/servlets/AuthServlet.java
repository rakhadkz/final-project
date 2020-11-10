package servlets;

import config.Database;
import enums.Role;
import models.Admin;
import models.Student;
import services.AuthService;
import services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AuthServlet")
public class AuthServlet extends HttpServlet {
    HttpSession session;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        session = request.getSession();
        Connection connection = Database.getConnection();
        AuthService authService = new AuthService(connection);
        if (session.getAttribute("user") != null){
            session.invalidate();
            //TODO Add here Cookie removing functionality
            return;
        }

        if (role.equals(Role.student.toString())){
            try {
                authService.authenticate(email, password);
                StudentService studentService = new StudentService(connection);
                session.setAttribute("user", studentService.read(email));
                System.out.println(studentService.read(email).getFirstName());
                //TODO Add some cookies
            } catch (Exception e) {
                request.setAttribute("student_auth_error", e.getMessage());
            }

        }
        else if (role.equals(Role.admin.toString())){
            try {
                authService.admin_authenticate(email, password);
                session.setAttribute("user", new Admin(email));
                System.out.println("Successfully!");
                //TODO Add some cookies
            } catch (Exception e) {
                request.setAttribute("admin_auth_error", e.getMessage());
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null)
            return;
        request.getRequestDispatcher("./auth.jsp").forward(request, response);
    }
}
