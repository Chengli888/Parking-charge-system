package com.aiseminar.Bean;

/**
 * Created by 18852 on 2017/3/23.
 */

public class User {

        public String username;
        public String password;
        public String location;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
