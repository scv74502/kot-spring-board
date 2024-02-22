package org.example.assignment.common.swagger

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Value("\${swagger.project.base-package}")
    private val basePackage: String? = null

    //    @Value("${swagger.project.external-host}")
    //    private String externalHost;
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(this.basePackage))
                .paths(PathSelectors.ant("/**"))
                .build() //                .host(this.externalHost)
                .apiInfo(this.apiInfo())
                .useDefaultResponseMessages(false)
    }

    /**
     * API Documents Information
     * @return
     */
    private fun apiInfo(): ApiInfo {
        val title = "Swagger API Documents" // 스웨거 UI 타이틀
        val version = "1.0.0"
        val license = "cc All rights reserved"

        return ApiInfoBuilder()
                .title(title)
                .version(version)
                .description("test kotlin spring api, supports auth with jwt")
                .license(license)
                .build()
    }
}