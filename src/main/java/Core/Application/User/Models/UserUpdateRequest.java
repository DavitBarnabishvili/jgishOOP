package Core.Application.User.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUpdateRequest {
    private String username;
    private String avatar; // Assuming this will store the image URL or filename
    private String password;

    // Constructors
    public UserUpdateRequest() {
        // Default constructor
    }

    public UserUpdateRequest(String username, String avatar, String password) {
        this.username = username;
        this.avatar = avatar;
        setPassword(password);
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter, and at least one digit.");
        }
    }

    private boolean isValidPassword(String password) {
        // Minimum eight characters, at least one uppercase letter and one digit
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

