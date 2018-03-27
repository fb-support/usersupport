package com.facebank.usersupport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by pengyuming on 2017/9/8.
 */
@Component
public class BeanConfig {

//    @Bean
//    public SpringContextHolder createSpringContextHolder(){
//        return new SpringContextHolder();
//    }

    /*@Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }*/

    /**
     * Swagger文档
     */
//    @Value("${usersupport-mode.version}")
    private String usersupportModelVersion ;
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("cn.facebank.userprimary.service.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("笑脸金融用户相关接口")
//                .description("请于IP：端口/后面添加swagger-ui.html来访问对应环境的接口地址" +
//                        "<br>" +
//                        "<br>maven环境请添加如下配置来使用API中的model类" +
//                        "<br>&lt;dependency&gt;" +
//                        "<br>&nbsp;&nbsp;&nbsp;&nbsp;&lt;groupId&gt;cn.facebank&lt;/groupId&gt;" +
//                        "<br>&nbsp;&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;userprimary-model&lt;/artifactId&gt;" +
//                        "<br>&nbsp;&nbsp;&nbsp;&nbsp;&lt;version&gt;"+userPrimaryModelVersion+"&lt;/version&gt;" +
//                        "<br>&lt;/dependency&gt;")
//                .version("1.0")
//                .build();
//    }
}
