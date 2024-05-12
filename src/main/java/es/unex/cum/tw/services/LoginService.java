package es.unex.cum.tw.services;

import es.unex.cum.tw.models.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

/**
 * Clase que define los métodos de autenticación de un usuario.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:48
 */
public interface LoginService {
    public Optional<User> authenticate(HttpServletRequest request);
}
