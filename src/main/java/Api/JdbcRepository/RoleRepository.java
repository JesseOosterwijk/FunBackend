package Api.JdbcRepository;

import org.springframework.jdbc.core.JdbcTemplate;

public class RoleRepository {
    private JdbcTemplate connection;

    public RoleRepository(JdbcTemplate con) {
        this.connection = con;
    }
}
