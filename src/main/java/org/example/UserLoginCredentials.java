package org.example;

public class UserLoginCredentials {

    private String email;
    private String password;

    public UserLoginCredentials(){

    }

    public UserLoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public static UserLoginCredentials from(User user){
        return new UserLoginCredentials(user.getEmail(), user.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
