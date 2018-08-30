package io.sanye.open.financial.swagger;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/30 下午3:49
 */
//@Configuration
@EnableConfigurationProperties(SwaggerInfo.class)
@EnableSwagger2
public class SwaggerConfiguration {

    private final SwaggerInfo swaggerInfo;


    public SwaggerConfiguration(SwaggerInfo swaggerInfo1) {
        this.swaggerInfo = swaggerInfo1;
    }

    @Bean
    public Docket controllerApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(swaggerInfo.getGroupName())
                .apiInfo(apiInfo());

        ApiSelectorBuilder builder = docket.select();
        if (!StringUtils.isEmpty(swaggerInfo.getBasePackage())) {
            builder = builder.apis(RequestHandlerSelectors.basePackage(swaggerInfo.getBasePackage()));
        }

        if (!StringUtils.isEmpty(swaggerInfo.getAntPath())) {
            builder = builder.paths(PathSelectors.ant(swaggerInfo.getAntPath()));
        }

        return builder.build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerInfo.getTitle())
                .description(swaggerInfo.getDescription())
                .termsOfServiceUrl("http://springfox.io")
                .contact("sanye")
                .license(swaggerInfo.getLicense())
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LINCENSE")
                .version("2.0")
                .build();
    }

}
