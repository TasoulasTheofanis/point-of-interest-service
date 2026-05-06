package point_of_interest_service.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostGisInitializer {

    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initializePostGis() {
        jdbcTemplate.execute("CREATE EXTENSION IF NOT EXISTS postgis");
    }
}