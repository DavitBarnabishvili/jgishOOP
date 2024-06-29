package Core.Infrastructure;

import Core.Infrastructure.Infrastructure.User.IUserRepository;
import Core.Infrastructure.Infrastructure.User.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        IUserRepository userRepository = UserRepository.getInstance();
        sce.getServletContext().setAttribute("UserRepository", userRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

