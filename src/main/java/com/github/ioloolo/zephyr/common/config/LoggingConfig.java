package com.github.ioloolo.zephyr.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class LoggingConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new LoggingInterceptor())
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/public/**", "/*.png");
	}

	public static class LoggingInterceptor implements HandlerInterceptor {

		@Override
		public boolean preHandle(
				@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler
		) {

			String steamId = request.getHeader("Authorization");
			if (steamId == null) {
				return true;
			}

			log.info("[{}] {} {}", steamId, request.getMethod(), request.getRequestURI());

			return true;
		}
	}
}
