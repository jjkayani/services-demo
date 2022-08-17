package com.service.demo.config;

import com.fasterxml.classmate.TypeResolver;
import com.service.demo.common.Constants;
import com.service.demo.controller.DemoController;
import com.service.demo.payload.error.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    private static final String PARAM_TYPE_HEADER = "header";

    /**
     * Controls the initialization of swagger plugin instance. Intended to toggle Swagger endpoint per environment.
     * Defaults to {@code true}.
     */
    @Value("${swagger.enable:true}")
    private boolean enableSwagger;

    @Bean
    public Docket apiDocket() {
        List<Response> commonResponses = Collections.singletonList(errorResponseModel());
        Tag[] tags = tags();

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                .apiInfo(apiInfo())
                .tags(tags[0], Arrays.copyOfRange(tags, 1, tags.length))
                .select()
                .apis(RequestHandlerSelectors.basePackage(DemoController.class.getPackage().getName()))
                .paths(PathSelectors.any()).paths(PathSelectors.regex("/error.*").negate()).build()

                .securitySchemes(Arrays.asList(apiAccessKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, commonResponses)
                .globalResponses(HttpMethod.POST, commonResponses)

                .additionalModels(new TypeResolver().resolve(ErrorResponseModel.class))
                .ignoredParameterTypes(File.class, Resource.class, InputStream.class);
    }

    private Tag[] tags() {
        return new Tag[]{
                new Tag("DemoController", "This is the only controller in this service"),
        };
    }


    private static Response errorResponseModel() {
        return new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
    }

    private static List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = {new AuthorizationScope("global", "accessEverything")};
        return Arrays.asList(new SecurityReference(Constants.APPLICATION_SECRET, authorizationScopes));
    }

    private static SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .operationSelector(oc -> true)
                .build();
    }

    private SecurityScheme apiAccessKey() {
        return new ApiKey(Constants.APPLICATION_SECRET, Constants.APPLICATION_SECRET, PARAM_TYPE_HEADER);
    }

    private static ApiInfo apiInfo() {
        return new ApiInfo("Demo Service", "This is a Demo service", "1.0",
                "No T&C", author(), "Open License", null, Collections.emptyList());
    }

    private static Contact author() {
        return new Contact("Jamal Javed", "https://www.linkedin.com/in/jamaljaved/", "jamaljavedkayani@gmail.com");
    }
}
