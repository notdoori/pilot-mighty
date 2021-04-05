package com.pilot.mighty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	/**
	 * 스웨거 API 문서 생성
	 * @author djchoi
	 * @return Docket
	 */
	@Bean
	public Docket swaggerAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(this.swaggerInfo()) // 스웨거 정보 등록
				.select()
				.apis(RequestHandlerSelectors.any()) // 모든 controller들이 있는 패키지를 탐색해서 API를 만든다.
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(true); // 기본으로 세팅되는 200, 401, 403, 404 메시지 표시
	}
	
	/**
	 * 스웨거 정보
	 * @author djchoi
	 * @return ApiInfo
	 */
	private ApiInfo swaggerInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot API Ducumentation")
				.description("Spring Boot 프로젝트 기본 구조를 잡는 프로젝트 입니다.")
				.version("1.0.0")
				.build();
	}

}
