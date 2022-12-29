package md.hadj4r.yawp.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User API",
                version = "${api.version}",
                description = "${api.description}"
        ),
        servers = @Server(
                url = "${api.server.url}",
                description = "Demo"
        )
)
public class OpenAPI30Configuration {
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement().addList(securitySchemeName)
                )
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .description(
                                                        """
                                                                Provide the JWT token.
                                                                JWT token can be obtained from the Login API.
                                                                For testing, use the credentials <strong>john/password</strong>
                                                                or token <strong>eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjo5MjIzMzcyMDM2ODU0Nzc1LCJpYXQiOjE2NzIzMjM2NDksInVzZXJJZCI6ImY5NTEyZjI4LWFkMmMtNGIwYS1hOTA5LTY4OTdkOWJiNGI2NiJ9.DhdpF-ZZkGfU4Bp7DSuWJHPBTFAb8nL0sMGgkLhYHlM</strong>
                                                                """
                                                )
                                                .bearerFormat("JWT")
                                )
                );
    }
}