package Core.Domain.Entities;

import Core.Application.PasswordEncryptor;

import java.security.NoSuchAlgorithmException;

public class User {
    private int id;
    private String username;
    private String pictureUrl;
    private String passwordHash;

    public User() {
    }

    public User(int id, String username, String pictureUrl, String password) throws NoSuchAlgorithmException {
        this.id = id;
        this.username = username;
        this.pictureUrl = pictureUrl;
        setPassword(password);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.passwordHash = PasswordEncryptor.Encrypt(password);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}

