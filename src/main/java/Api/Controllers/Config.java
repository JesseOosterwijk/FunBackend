package Api.Controllers;

import Api.Repository.UserRepository;
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
    public UserRepository userRepository(JdbcTemplate con) {
        return new UserRepository(con);
    }
}
