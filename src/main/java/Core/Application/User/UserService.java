package Core.Application.User;

import Core.Application.User.Models.UserLoginRequest;
import Core.Application.User.Models.UserRegisterRequest;
import Core.Application.User.Models.UserResponse;
import Core.Application.User.Models.UserUpdateRequest;
import Core.Domain.CustomException.UserNotFoundException;
import Core.Infrastructure.Infrastructure.User.IUserRepository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletableFuture<Void> logIn(UserLoginRequest userRequest) {
        // Example: Call userRepository method for login logic
        // Implement as needed
        return null;
    }

    @Override
    public CompletableFuture<Void> register(UserRegisterRequest userRequest) {
        // Example: Call userRepository method for registration logic
        // Implement as needed
        return null;
    }

    @Override
    public CompletableFuture<Void> updateUser(int userId, UserUpdateRequest userRequest) {
        // Example: Call userRepository method for update logic
        // Implement as needed
        return null;
    }

    @Override
    public CompletableFuture<UserResponse> getById(int userId) {
        return userRepository.findByIdAsync(userId)
                .thenApply(user -> {
                    if (user == null) {
                        throw new CompletionException(new UserNotFoundException("User with ID " + userId + " not found."));
                    }
                    return new UserResponse(user.getUsername());
                });
    }

}

