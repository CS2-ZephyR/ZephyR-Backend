package com.github.ioloolo.zephyr.common.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.ioloolo.zephyr.domain.user.data.User;
import com.github.ioloolo.zephyr.domain.user.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class LoggingConfig implements WebMvcConfigurer {

	private final LoggingInterceptor loggingInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(loggingInterceptor)
				.order(1)
				.addPathPatterns("/**")
				.excludePathPatterns("/public/**");
	}

	@Component
	@RequiredArgsConstructor
	public static class LoggingInterceptor implements HandlerInterceptor {

		private final UserRepository userRepository;

		@Override
		public boolean preHandle(
				@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler
		) {

			String steamId = request.getHeader("Authorization");
			if (steamId == null) {
				return true;
			}

			Optional<User> userOptional = userRepository.findBySteamId(Long.parseLong(steamId));

			log.info("[{}] {} {}", userOptional.isPresent() ? userOptional.get().getName() : steamId, request.getMethod(), request.getRequestURI());

			return true;
		}
	}
}
