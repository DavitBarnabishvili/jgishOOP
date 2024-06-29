package Core.Application.User.Models;

import java.io.File;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegisterRequest {
    private String username;
    private String password;
    private String selectedAvatar;

    // Constructor
    public UserRegisterRequest(String username, String password, String selectedAvatar) {
        this.username = username;
        setPassword(password);
        this.selectedAvatar = selectedAvatar;
    }

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
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be at least 8 characters long, " +
                    "contain at least one uppercase letter, and at least one digit.");
        }
    }

    public String getSelectedAvatar() {
        return selectedAvatar;
    }

    public void setSelectedAvatar(String selectedAvatar) {
        this.selectedAvatar = selectedAvatar;
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}


