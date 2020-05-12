import Api.Entity.Project;
import Api.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HibernatePersistencyTests {
    Configuration cfg = new Configuration()
            .addResource("hibernate.cfg.xml");
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
    SessionFactory sessions = cfg.buildSessionFactory(serviceRegistry);
    Session session = sessions.openSession();

    @Test
    public void ManyToManyRelationShip() {
        new Configuration();

        Set<User> users = new HashSet<>();
        Set<Project> projects = new HashSet<>();

        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setName("user1");
        user1.setEmail("lol@user1.com");
        user1.setPassword("123");
        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setName("user2");
        user2.setEmail("lol@user2.com");
        user2.setPassword("321");
        users.add(user2);

        Project project1 = new Project();
        project1.setId(1);
        project1.setDescription("test1");
        project1.setName("project1");
        projects.add(project1);
        Project project2 = new Project();
        project2.setId(2);
        project2.setDescription("test2");
        project2.setName("project2");
        projects.add(project2);

        for (User user : users) {
            assertEquals(0, user.getProjects().size());
            user.setProjects(projects);
            session.persist(user);

            assertNotNull(user);
        }
    }
}
