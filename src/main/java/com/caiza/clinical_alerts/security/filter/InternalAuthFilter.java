package com.caiza.clinical_alerts.security.filter;

import com.caiza.clinical_alerts.security.model.UserContext;
import com.caiza.clinical_alerts.security.model.UserContextHolder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.List;

@Component
public class InternalAuthFilter extends OncePerRequestFilter {

    @Value("${internal.jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            try {
                Claims claims = Jwts.parser()
                        .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                        .build()
                        .parseSignedClaims(token.substring(7))
                        .getPayload();

                // Cria o contexto do usuário
                UserContext context = new UserContext(
                        Long.valueOf(claims.getSubject()),
                        claims.get("roles", List.class),
                        claims.get("tenantId", String.class)
                );

                UserContextHolder.setContext(context);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token interno inválido");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}