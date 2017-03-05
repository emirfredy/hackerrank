package co.com.rappi.cubes.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration class
 * 
 * @author <a href="emirfredy@gmail.com">Emir Cortes Trujillo</a>
 */
@EnableSwagger2
@Configuration
public class DocumentationConfig {

	/**
	 * Defines the paths to be documented
	 */
	private static final String PATH_MAPPING = "/";

	/**
	 * The root of the api
	 */
	private static final String API_ROOT = "/api.*";

	/**
	 * Defines the version of the api
	 */
	@Value("${application.version}")
	private String applicationVersion;

	/**
	 * Api title
	 */
	@Value("${application.title}")
	private String title;

	/**
	 * Api description shown in swagger
	 */
	@Value("${application.description}")
	private String description;

	/**
	 * Termos of service url for the api
	 */
	@Value("${application.termsOfServiveUrl}")
	private String termsOfServiveUrl;

	/**
	 * Establishes the license that applies to the api
	 */
	@Value("${application.license}")
	private String license;

	/**
	 * License url
	 */
	@Value("${application.licenseUrl}")
	private String licenseUrl;

	/**
	 * Produces a bean with the swagger configuration
	 * 
	 * @return a {@link Docket} containing the configuration related to the API
	 */
	@Bean
	public Docket onlineAccountApi() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(paths()).build().pathMapping(PATH_MAPPING);

	}

	/**
	 * Returns custom information related to the API
	 * 
	 * @return Information related to the API
	 */
	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title(title).description(description).termsOfServiceUrl(termsOfServiveUrl)
				.license(license).licenseUrl(licenseUrl).version(applicationVersion).build();
	}

	/**
	 * Produces a predicate with the paths that should be exposed in the
	 * documentation
	 * 
	 * @return a predicate with the paths that should be exposed in the
	 *         documentation
	 */
	private Predicate<String> paths() {
		return regex(API_ROOT);
	}
}
