package com.project.redpontis.security;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(2)
public class RateLimitingFilter extends OncePerRequestFilter {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private static final int SC_TOO_MANY_REQUESTS = 429;

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket resolveBucket(String ip) {
        return buckets.computeIfAbsent(ip, k -> {
            Refill refill = Refill.intervally(5, Duration.ofMinutes(1)); // 5 solicitudes por minuto
            Bandwidth limit = Bandwidth.classic(5, refill);
            return Bucket.builder().addLimit(limit).build();
        });
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().contains("/auth/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String ip = request.getRemoteAddr();
        Bucket bucket = resolveBucket(ip);

        if (bucket.tryConsume(1)) {
            log.info("Acceso permitido para IP {} a {}", ip, request.getRequestURI());
            filterChain.doFilter(request, response);
        } else {
            log.warn("Acceso bloqueado por rate limit para IP {} a {}", ip, request.getRequestURI());
            response.setStatus(SC_TOO_MANY_REQUESTS);
            response.getWriter().write("Demasiadas solicitudes: intentelo de nuevo mas tarde");
        }
    }
}