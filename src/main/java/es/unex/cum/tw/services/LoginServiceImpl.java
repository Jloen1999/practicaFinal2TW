package es.unex.cum.tw.services;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.UserBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {
    @Override
    public Optional<User> authenticate(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        return Optional.ofNullable(user);
    }
}
