package com.clone.fbclone.util;

import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.services.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Aminul Hoque
 * @since 2021-03-18
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtRequestFilter extends OncePerRequestFilter {
    private final MyUserDetailsService service;
    private final JwtUtil util;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader !=null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = util.extractUsername(jwt);
        }
        if (username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserEntity userEntity = this.service.loadUserByUsername(username);
            if (util.validateToken(jwt, userEntity)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userEntity, null, userEntity.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
