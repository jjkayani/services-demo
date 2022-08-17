package com.service.demo.config;

import com.service.demo.common.Constants;
import com.service.demo.filter.AuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security configuration for <tt>Demo Service</tt>.
 * It recommends accessing APIs using JWT. However support for legacy application secret is still provided.
 *
 */
@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityConfigReader securityConfigReader;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(Constants.API_SECURED_PATH_PREFIX).authenticated()
                    .anyRequest().denyAll()
                .and()
                    .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(Constants.API_OPEN_PATH_PREFIX, "/", "/actuator/health", "/actuator/info", "/csrf", "/error", "/v2/api-docs", "/configuration/ui",
                "/swagger-resources/**", "/configuration/security", "/swagger-ui/**", "/webjars/**");
    }

    protected AuthenticationProcessingFilter authenticationTokenFilter() {
        AuthenticationProcessingFilter filter = new AuthenticationProcessingFilter(new AntPathRequestMatcher(Constants.API_SECURED_PATH_PREFIX), securityConfigReader);
        return filter;
    }

}
