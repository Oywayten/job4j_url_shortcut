package shortcut.urlshortcut.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * Oywayten 01.06.2023.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Url Shortcut System Api",
                description = "Url Shortcut System", version = "1.0.0",
                contact = @Contact(
                        name = "oywayten",
                        email = "oywayten@gmail.com",
                        url = "https://github.com/Oywayten"
                )
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {

}