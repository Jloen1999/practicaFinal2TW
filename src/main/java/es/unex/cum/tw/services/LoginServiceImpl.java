package es.unex.cum.tw.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Properties;

public class LoginServiceImpl implements LoginService {
    Properties props = new Properties();
    @Override
    public boolean authenticate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try{
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            String username = session.getAttribute("username") != null ? (String) session.getAttribute("username") : "";
            String password = session.getAttribute("password") != null ? (String) session.getAttribute("password") : "";
            return username.equals(props.getProperty("USERNAME")) && password.equals(props.getProperty("PASSWORD"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
