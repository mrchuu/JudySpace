package com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.configuration;

import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller.Auth.CustomAccessDeniedHandler;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.controller.Auth.CustomSuccessOauth2AuthHandler;
import com.JudySpaceLoginRegistrationDemo.JudySpaceLoginRegistrationDemo.securityConfiguration.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomSuccessOauth2AuthHandler successHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider, CustomSuccessOauth2AuthHandler successHandler, CustomAccessDeniedHandler accessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
        this.successHandler = successHandler;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(
                                        "/api/auth/**",
                                        "/api/users/resetPassword",
                                        "/api/comment/getRootComments/{blogId}",
                                        "/api/comment/getChildComments/{commentId}",
                                        "/api/blog/getBlogsPaginated",
                                        "/api/blog/getBlogContent/{blogId}",
                                        "/api/blogUpvote/getUpvotedUserListOfBlog/{blogId}",
                                        "/api/blog/getBlogDetail/{blogId}",
                                        "/api/blogTag/getAll",
                                        "/api/blog/updateBlog",
                                        "/api/movieCategoryController/getAll",
                                        "/api/moviePlaylists/getAll",
                                        "/api/music/playlist/getAll",
                                        "/api/music/playList/getSongs/{playListId}"
                                ).permitAll()
                                .requestMatchers(swaggerWhiteList).permitAll()
                                .requestMatchers(
                                        "/api/blog/getAll",
                                        "/api/blog/addBlog"
//                                "/api/blog/updateBlog"
                                ).hasAnyAuthority("Judy")
                                .anyRequest().authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login").permitAll()
                )
                .oauth2Login(customizer -> customizer
                        .successHandler(successHandler)
                        .loginPage("/login").permitAll()
                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handler -> handler
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                );
        return http.build();
    }



    private static final String[] swaggerWhiteList = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
}
