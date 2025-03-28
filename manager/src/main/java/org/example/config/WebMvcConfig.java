package org.example.config;

import org.example.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @Configuration 用于声明一个配置类
 * springboot项目中没有springmvc.xml文件  所以有些配置就需要通过配置类来完成
 * springboot提供了一个接口WebMvcConfigurer  我们可以编写配置类来实现该接口
 *   接口中的方法：
 *       addCorsMappings  配置跨域
 *       addResourceHandlers  配置静态资源的访问
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${alloworigins}")
    String[] allowOrigins;

    /**
     * 相当于前面SSM项目中写的跨域过滤器的作用
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        System.out.println("允许跨域的路径：");
        System.out.println(Arrays.toString(allowOrigins));
        /*
         * addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
         * allowCredentials：是否开启Cookie
         * allowedMethods：允许的请求方式，如：POST、GET、PUT、DELETE等。
         * allowedOrigins：允许访问的url，可以固定单条或者多条内容
         * allowedHeaders：允许的请求header，可以自定义设置任意请求头信息。
         * maxAge：配置预检请求的有效时间
         */
        registry.addMapping("/**")
                //String... origins  参数是不定项参数  其实就是数组  String[]
                .allowedOrigins(allowOrigins)//前端地址
                .allowCredentials(true)
                .allowedMethods("GET", "POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .maxAge(36000);
    }

    @Value("${uploadlocation}")
    String uploadlocation;

    /**
     * 相当于 springmvc.xml中配置的
     * <mvc:resources mapping="/img/**" location="file:D://upload1118/"/>
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("上传路径："+uploadlocation);
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:///"+uploadlocation);
    }

    /**
     * 配置拦截器
     * 可以配置多个
     *   拦截器对象
     *   拦截的地址
     *   不拦截的地址
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/log/in","/verifyCode/**");
    }
}