package Api.Controllers.Config;

import Api.Entity.User;
import Api.Repository.*;
import Api.Service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class Config {
    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUsername("dbi398785_fundb");
        dataSource.setPassword("123");
        dataSource.setUrl("jdbc:sqlserver://mssql.fhict.local;databaseName=dbi398785_fundb");
        return dataSource;
    }

    @Bean
    public CategoryRepository categoryRepository(JdbcTemplate con) { return new CategoryRepository(con); }

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) { return new CategoryService(categoryRepository); }

    @Bean
    public MeetingRepository meetingRepository(JdbcTemplate con) { return new MeetingRepository(con); }

    @Bean
    public MeetingService meetingService(MeetingRepository meetingRepository) { return new MeetingService(meetingRepository); }

    @Bean
    public ProjectRepository projectRepository(JdbcTemplate con) { return new ProjectRepository(con); }

    @Bean
    public ProjectService projectService(ProjectRepository projectRepository) { return new ProjectService(projectRepository); }

    @Bean
    public RoleRepository roleRepository(JdbcTemplate con) { return new RoleRepository(con); }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) { return new RoleService(roleRepository); }

    @Bean
    public StateRepository stateRepository(JdbcTemplate con) { return new StateRepository(con); }

    @Bean
    public StateService stateService(StateRepository stateRepository) { return new StateService(stateRepository); }

    @Bean
    public TaskRepository taskRepository(JdbcTemplate con) { return new TaskRepository(con); }

    @Bean
    public TaskService taskService(TaskRepository taskRepository) { return new TaskService(taskRepository); }

    @Bean
    public UserRepository userRepository(JdbcTemplate con) {
        return new UserRepository(con);
    }

    @Bean
    public UserService userService(UserRepository userRepository) { return new UserService(userRepository); }

}
