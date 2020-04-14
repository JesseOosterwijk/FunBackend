package Api.Repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class ProjectRepository {
    private JdbcTemplate connection;

    public ProjectRepository(JdbcTemplate con) {
        this.connection = con;
    }
}
