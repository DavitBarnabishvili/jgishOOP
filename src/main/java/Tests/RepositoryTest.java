package Tests;

import Core.Domain.Entities.User;
import Core.Infrastructure.Infrastructure.UserRepository;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RepositoryTest extends TestCase {
    private UserRepository userRepository;
    private User testUser;

    @Before
    public void setUp() throws SQLException, NoSuchAlgorithmException {
        userRepository = new UserRepository();
        testUser = new User();
        testUser.setId(1);
        testUser.setPassword("guramiko777");
        testUser.setUsername("johndoe");
        testUser.setPictureUrl("Pictures/default_profile_picture.jpg");
    }

    @Test
    public void testFindByIdAsync() throws InterruptedException, ExecutionException {
        // Call the method being tested
        Future<User> futureUser = userRepository.findByIdAsync(1);

        // Get the result (blocking for the sake of this example)
        User foundUser = futureUser.get();

        // Assert that the user is not null
        assertNotNull(foundUser);

        // Assert that the user retrieved matches the testUser
        assertEquals(testUser.getId(), foundUser.getId());
        assertEquals(testUser.getUsername(), foundUser.getUsername());
        assertEquals(testUser.getPictureUrl(), foundUser.getPictureUrl());
    }
}