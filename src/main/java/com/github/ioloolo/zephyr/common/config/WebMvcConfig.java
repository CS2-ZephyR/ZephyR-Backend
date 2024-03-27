package com.github.ioloolo.zephyr.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins("*").allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		// registry.addViewController("/{spring:\\w+}").setViewName("forward:/");
		// registry.addViewController("/**/{spring:\\w+}").setViewName("forward:/");
		// registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}").setViewName("forward:/");
	}
}
