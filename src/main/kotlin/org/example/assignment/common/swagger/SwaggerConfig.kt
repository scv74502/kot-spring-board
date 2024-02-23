package org.example.assignment.common.swagger

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    @Bean
    fun swagger(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo()) //apiInfo 삽입
            .select() //ApiSelectorBuilder를 생성
            .apis(RequestHandlerSelectors.basePackage("org.example.assignment")) //API 범위 지정 (해당 패키지 내부에 있는 모든 Request 출력)
            .paths(PathSelectors.any()) //스웨거 html 페이지에 모든 URL 제공
            //.paths(PathSelectors.ant("/test/**")) 스웨거 html 페이지에 특정 api만 보여주고 싶다면 해당 부분 설정
            .build()
    }

    fun apiInfo(): ApiInfo {  //ApiInfo 설정
        return ApiInfoBuilder()
            .title(API_NAME)
            .version(API_VERSION)
            .description(API_DESCRIPTION)
            .build()
    }

    companion object {
        private const val API_NAME = "TestProject API"
        private const val API_VERSION = "0.0.1"
        private const val API_DESCRIPTION = "TestProject API 명세서"
    }
}
