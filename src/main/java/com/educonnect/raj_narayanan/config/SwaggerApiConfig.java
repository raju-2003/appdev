package com.educonnect.raj_narayanan.config;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;
import static com.educonnect.raj_narayanan.utils.MyConstant.JWT_LOCALHOST_URL;
import static com.educonnect.raj_narayanan.utils.MyConstant.JWT_SECURITY_SCHEME_NAME;
import static com.educonnect.raj_narayanan.utils.MyConstant.SWAGGER_CONTACT_EMAIL;
import static com.educonnect.raj_narayanan.utils.MyConstant.SWAGGER_CONTACT_NAME;
import static com.educonnect.raj_narayanan.utils.MyConstant.SWAGGER_CONTACT_URL;
import static com.educonnect.raj_narayanan.utils.MyConstant.SWAGGER_DESCRIPTION;
import static com.educonnect.raj_narayanan.utils.MyConstant.SWAGGER_LICENSE;
import static com.educonnect.raj_narayanan.utils.MyConstant.SWAGGER_LICENSE_URL;
import static com.educonnect.raj_narayanan.utils.MyConstant.SWAGGER_TITLE;
import static com.educonnect.raj_narayanan.utils.MyConstant.SWAGGER_VERSION;
import static com.educonnect.raj_narayanan.utils.MyConstant.JWT_SCHEME;
import static com.educonnect.raj_narayanan.utils.MyConstant.JWT_DESCRIPTION;
import static com.educonnect.raj_narayanan.utils.MyConstant.JWT_BEARER_FORMAT;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                .title(SWAGGER_TITLE)
                .description(SWAGGER_DESCRIPTION)
                .version(SWAGGER_VERSION)
                .contact(new Contact()
                        .name(SWAGGER_CONTACT_NAME)
                        .url(SWAGGER_CONTACT_URL)
                        .email(SWAGGER_CONTACT_EMAIL))
                .license(new License()
                        .name(SWAGGER_LICENSE)
                        .url(SWAGGER_LICENSE_URL))
                )
                .servers(List.of(new Server().url(JWT_LOCALHOST_URL)))
                .addSecurityItem(new SecurityRequirement()
                        .addList(JWT_SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(
                                JWT_SECURITY_SCHEME_NAME, new SecurityScheme()
                                        .name(JWT_SECURITY_SCHEME_NAME)
                                        .type(HTTP)
                                        .scheme(JWT_SCHEME)
                                        .description(JWT_DESCRIPTION)
                                        .bearerFormat(JWT_BEARER_FORMAT)));
                                
    }

}
