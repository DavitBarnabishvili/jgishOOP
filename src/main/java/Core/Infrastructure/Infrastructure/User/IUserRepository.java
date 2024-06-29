package Core.Infrastructure.Infrastructure.User;

import Core.Domain.Entities.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IUserRepository {
    CompletableFuture<User> findByIdAsync(int id);
    CompletableFuture<Void> createAsync(User user);
    CompletableFuture<Void> updateAsync(User user);
    CompletableFuture<Void> deleteByIdAsync(int id);
    CompletableFuture<List<User>> findAllAsync(); // Optional: If needed
}
