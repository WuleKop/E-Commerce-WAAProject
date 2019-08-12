package edu.mum.cs.sellerService.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiEndPointsInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.mum.cs.sellerService"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiEndPointsInfo() {


        return new ApiInfo("Seller Service Api Documentation",
                "A micro service where seller can upload a product",
                "1.0",
                "urn:tos",
                new Contact("Wuletaw Adane", "", "wtefera@mum.edu"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());

    }
}