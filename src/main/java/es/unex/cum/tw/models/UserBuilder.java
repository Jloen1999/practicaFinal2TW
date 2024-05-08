package es.unex.cum.tw.models;

public class UserBuilder {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String username;
    private String password;

    public UserBuilder() {
        this.id = 0;
        this.nombre = "";
        this.apellidos = "";
        this.email = "";
        this.username = "";
        this.password = "";
    }

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UserBuilder setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        return new User(id,nombre, apellidos, email, username, password);
    }
}
