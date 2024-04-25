package es.unex.cum.tw.services;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface LoginService {
    public boolean authenticate(HttpServletRequest request);
}
