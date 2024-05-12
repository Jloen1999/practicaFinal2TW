package es.unex.cum.tw.services;

import es.unex.cum.tw.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

/**
 * Clase que implementa la interfaz LoginService.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:49
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<User> authenticate(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        return Optional.ofNullable(user);
    }
}
