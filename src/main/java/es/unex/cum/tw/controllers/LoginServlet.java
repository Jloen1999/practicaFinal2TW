package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.Carta;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet(
        name = "LoginServlet",
        value = "/login"
)
public class LoginServlet extends HttpServlet {
    private Properties props;
    private String USERNAME;
    private String PASSWORD;
    private static String user;

    @Override
    public void init() throws ServletException {
        super.init();
        USERNAME = "";
        PASSWORD = "";
        props = new Properties();
        user = "";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter print = null;
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            USERNAME = props.getProperty("USERNAME");
            PASSWORD = props.getProperty("PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        user = request.getParameter("username");
        String password = request.getParameter("password");

        UserService service = new UserServiceJDBCImpl((Connection) request.getAttribute("con"));
        Optional<User> userOptional = service.login(user, password);

        if (userOptional.isPresent()) {
            request.setAttribute("username", user);
            request.setAttribute("password", password);
            if (user.equals(USERNAME) && password.equals(PASSWORD)) {
                // Obtenemos la lista de usuarios
                List<User> users = service.findAll();
                try {
                    print = response.getWriter();
                    print.println("<div class=\"col-md-3 d-flex justify-content-end ms-6\">");
                    print.println("<img class=\"\" src=\"img/profile.svg\" alt=\"imagen de perfil del admin\" wi>");
                    // Mostrar nombre username
                    print.println("<p class=\"text-success\">" + user + "</p>");
                    print.println("</div>");

                    mostrarUsuarios(print, users);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {

                try {
                    print = response.getWriter();
                    print.println("<div class=\"col-md-3 d-flex justify-content-end ms-6\">");
                    print.println("<img class=\"\" src=\"img/profile.svg\" alt=\"imagen de perfil del admin\" wi>");
                    // Mostrar nombre username
                    print.println("<p class=\"text-success\">" + user + "</p>");
                    print.println("</div>");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("/Introducir.html");
                try {
                    dispatcher.include(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AutenticarErrorCredenciales.html");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void mostrarUsuarios(PrintWriter print, List<User> users) {
        print.println("<!doctype html>");
        print.println("<html lang=\"es\">");
        print.println("<head>");
        print.println("<title>Sesion 7</title>");
        print.println("<meta charset=\"utf-8\">");
        print.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        print.println("<meta name=\"description\" content=\"\">");
        print.println("<meta name=\"author\" content=\"Jose Luis Obiang Ela Nanguang\">");
        print.println("<link rel=\"stylesheet\" href=\"./css/styles.css\">");
        print.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
        print.println("<link href=\"https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap\" rel=\"stylesheet\">");
        print.println("</head>");
        print.println("<body class=\"bg-dark\">");
        print.println("<h1 class=\"col-md-9 text-warning text-center display-1\">Reyes Magos</h1>");
        print.println("<div class=\"row flex-nowrap justify-content-between align-items-center\">");
        print.println("<header class=\"col-5\">");
        print.println("<nav id=\"navbar-example3\" class=\"h-100 flex-column align-items-stretch pe-4 border-end\">");
        print.println("<nav class=\"nav nav-pills flex-column\">");
        print.println("<a class=\"nav-link\" href=\"/webapp-sesion7/logout\" data-bs-toggle=\"tooltip\" data-bs-title=\"Cerrar sesión de usuario\" data-bs-placement=\"left\">");
        print.println("Cerrar sesión");
        print.println("</a>");
        print.println("</nav>");
        print.println("</nav>");
        print.println("</header>");
        print.println("<main class=\"col-7\">");
        print.println("<div class=\"row p-4 p-md-5 mb-4 text-white rounded bg-dark\">");
        print.println("<div class=\"col-md-10 px-0\">");
        print.println("<h1 class=\"display-4 fst-italic\">Bienvenido al menu de administración</h1>");
        print.println("<p class=\"lead my-3\">Aquí podrás ver los usuarios, añadir nuevos usuarios, ver las cartas y los regalos de cada usuario</p>");
        print.println("</div>");
        print.println("<div class=\"col-md-10 d-flex justify-content-center align-items-center img-thumbnail\">");
        print.println("<img src=\"img/navidad-reyes-magos.webp\" alt=\"imagen de los tres reyes magos\" id=\"img-reyes\">");
        print.println("</div>");
        print.println("<div class=\"col-md-12 px-0\">");
        print.println("<table class=\"table table-dark table-striped\">");
        print.println("<thead>");
        print.println("<tr>");
        print.println("<th scope=\"col\">Id</th>");
        print.println("<th scope=\"col\">Nombre</th>");

        print.println("<th scope=\"col\">Apellidos</th>");
        print.println("<th scope=\"col\">Email</th>");
        print.println("<th scope=\"col\">Username</th>");
        print.println("<th scope=\"col\">Password</th>");
        print.println("</tr>");
        print.println("</thead>");
        print.println("<tbody>");
        print.println("<tr>");
        for (User user : users) {
            print.println("<td>" + user.getId() + "</td>");
            print.println("<td>" + user.getNombre() + "</td>");
            print.println("<td>" + user.getApellidos() + "</td>");
            print.println("<td>" + user.getEmail() + "</td>");
            print.println("<td>" + user.getUsername() + "</td>");
            print.println("<td>" + user.getPassword() + "</td>");
            print.println("</tr>");
        }
        print.println("</tbody>");
        print.println("</table>");
        print.println("</div>");
        print.println("</main>");
        print.println("</div>");
        print.println("<footer class=\"row blog-footer d-flex justify-content-center align-content-center\">");
        print.println("<figure class=\"col-md-6 text-white text-center\">");
        print.println("<blockquote class=\"blockquote\">");
        print.println("<p>Sesión 7.</p>");
        print.println("</blockquote>");
        print.println("<figcaption class=\"blockquote-footer\">");
        print.println("Página <cite title=\"Source Title\">de Inicio</cite>");
        print.println("</figcaption>");
        print.println("</figure>");
        print.println("</footer>");
        print.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script>");
        print.println("<script src=\"index.js\"></script>");
        print.println("</body>");
        print.println("</html>");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService service = new UserServiceJDBCImpl((Connection) request.getAttribute("con"));
        Optional<User> userOptional = service.login((String) request.getAttribute("username"), (String) request.getAttribute("password"));

        try (PrintWriter print = response.getWriter()) {
            if (userOptional.isPresent()) {
                print.println("<p style='color: green'>Usuario autenticado</p>");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/action");
                try {
                    dispatcher.include(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/AutenticarErrorUsername.html");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }


            }
            print.println("</body>");
            print.println("</html>");
        }

    }
}
