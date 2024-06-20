package org.example;

public class UserCredentials {
    public UserCredentials(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public static UserCredentials from(User user){
        return new UserCredentials(user.getName(), user.getEmail());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String name;
    private String email;
}
