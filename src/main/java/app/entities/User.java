package app.entities;

public class User {
    private long user_id;
    private String username;
    private String password;

    public User(long user_id, String password, String username) {
        this.password = password;
        this.username = username;
        this.user_id = user_id;
    }
}
