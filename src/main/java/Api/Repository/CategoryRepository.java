package Api.Repository;

import org.springframework.jdbc.core.JdbcTemplate;

public class CategoryRepository {
    private JdbcTemplate connection;

    public CategoryRepository(JdbcTemplate con) {
        this.connection = con;
    }
}
