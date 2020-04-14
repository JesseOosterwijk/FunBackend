package Api.JdbcRepository;

import org.springframework.jdbc.core.JdbcTemplate;

public class MeetingRepository {
    private JdbcTemplate connection;

    public MeetingRepository(JdbcTemplate con) {
        this.connection = con;
    }
}
