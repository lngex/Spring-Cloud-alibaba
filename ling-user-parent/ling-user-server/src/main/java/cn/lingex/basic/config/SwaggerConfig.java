package cn.lingex.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {



    @Bean
    public Docket createRestApi() {
        List<SecurityScheme> parameters = new ArrayList<>();
        parameters.add(new ApiKey("Authorization", "Authorization", "header"));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 对外暴露服务的包,以controller的方式暴露,所以就是controller的包.
                .apis(RequestHandlerSelectors.basePackage("cn.lingex.controller"))
                .paths(PathSelectors.any())
                .build().securityContexts(securityContexts())
                .securitySchemes(parameters);
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContextList = new ArrayList<>();
        List<SecurityReference> securityReferenceList = new ArrayList<>();
        securityReferenceList.add(new SecurityReference(HttpHeaders.AUTHORIZATION, scopes()));
        securityContextList.add(SecurityContext
                .builder()
                .securityReferences(securityReferenceList)
                .forPaths(PathSelectors.any())
                .build()
        );
        return securityContextList;
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{new AuthorizationScope("global", "accessAnything")};
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户服务")
                .description("用户服务服务接口文档说明")
                .contact(new Contact("廖某", "www.baidu.com", "1902156923@qq.com"))
                .version("1.1")
                .build();
    }
}
