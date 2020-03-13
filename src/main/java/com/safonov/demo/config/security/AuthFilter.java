package com.safonov.demo.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (Objects.nonNull(httpRequest.getUserPrincipal())) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            String userName = httpRequest.getUserPrincipal().getName();

            Authentication auth = new UsernamePasswordAuthenticationToken(userName, null, null);
            securityContext.setAuthentication(auth);
            httpRequest.getSession(true).setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
        }
        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
    }
}
