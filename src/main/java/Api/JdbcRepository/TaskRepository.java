package Api.JdbcRepository;

import org.springframework.jdbc.core.JdbcTemplate;

public class TaskRepository {
    private JdbcTemplate connection;

    public TaskRepository(JdbcTemplate con) {
        this.connection = con;
    }

}
