package Api.JdbcRepository;

import org.springframework.jdbc.core.JdbcTemplate;

public class StateRepository {
    private JdbcTemplate connection;

    public StateRepository(JdbcTemplate con) {
        this.connection = con;
    }

}
