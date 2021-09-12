package com.service.product.filter;

import com.service.product.config.SecurityConfigReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * The authentication processing filter, which only let a user go in provided that certain headers are available
 * in upcoming request. Current implementation does no further validation over the required header values.
 * Their presence merely does the job.
 *
 * @author Jamal
 */
public class AuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String APPLICATION_SECRET = "x-application-secret";

    protected final SecurityConfigReader configReader;

    @Autowired
    public AuthenticationProcessingFilter(RequestMatcher requestMatcher, SecurityConfigReader configReader) {
        super(requestMatcher);
        this.configReader = configReader;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String applicationSecret = request.getHeader(APPLICATION_SECRET);
        if (!configReader.getAppSecret().equals(applicationSecret)) {
            throw new BadCredentialsException("Request doesn't have required security headers");
        }
        // We don't need any authentication provider/manager.
        return new AuthenticationToken();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException
    {
        // Setup the security context
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        chain.doFilter(request, response);
    }


    @lombok.Getter
    public static class AuthenticationToken extends AbstractAuthenticationToken {

        public AuthenticationToken() {
            super(Collections.emptyList());
            super.setAuthenticated(true);
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }
    }
}

