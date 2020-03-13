package com.safonov.demo.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class BasicAuthEntryPoint extends BasicAuthenticationEntryPoint {

    @Value("${odata.authentication.header.name}")
    private String odataHeaderName;
    @Value("${odata.authentication.header.value}")
    private String odataHeaderValue;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.addHeader(odataHeaderName, odataHeaderValue);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("message.error.authorization.401" + authException.getMessage());
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("REALM");
        super.afterPropertiesSet();
    }
}
