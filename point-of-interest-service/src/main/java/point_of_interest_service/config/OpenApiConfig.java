package point_of_interest_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pointOfInterestOpenApi() {

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local server");

        return new OpenAPI()
                .servers(List.of(localServer))
                .info(new Info()
                        .title("Point Of Interest API")
                        .description("REST API for Point Of Interest management")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Theofanis Tasoulas")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));
    }
}