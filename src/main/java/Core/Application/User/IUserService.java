package Core.Application.User;


import Core.Application.User.Models.UserLoginRequest;
import Core.Application.User.Models.UserRegisterRequest;
import Core.Application.User.Models.UserResponse;
import Core.Application.User.Models.UserUpdateRequest;

import java.util.concurrent.CompletableFuture;

public interface IUserService {

    CompletableFuture<Void> logIn(UserLoginRequest userRequest);

    CompletableFuture<Void> register(UserRegisterRequest userRequest);

    CompletableFuture<Void> updateUser(int userId, UserUpdateRequest userRequest);

    CompletableFuture<UserResponse> getById(int userId);
}

