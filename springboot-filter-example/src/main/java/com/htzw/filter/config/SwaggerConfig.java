package com.htzw.filter.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author J.L.G
 * @version 1.0
 * @date 2020/9/8 9:22
 */
@Configuration
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean swaggerEnable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo())
                //.groupName("swagger-example-service") // 项目组名
                // 选择那些路径和api会生成document
                .select()
                // 对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                //错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                //actuator路径跳过
                .paths(Predicates.not(PathSelectors.regex("/actuator.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("swagger-demo-service")
                // 文档描述
                .description("This is a swagger project.")
                // 文档版本
                .version("1.0.0")
                .build();
    }
}
