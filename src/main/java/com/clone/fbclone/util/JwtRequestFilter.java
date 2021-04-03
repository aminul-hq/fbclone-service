package com.clone.fbclone.util;

import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.services.MyUserDetailsService;
import com.clone.fbclone.services.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        String email = null;
        String jwt = null;

        if (authHeader !=null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            email = util.extractEmail(jwt);
        }
        if (email !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserEntity user = this.service.loadUserByUsername(email);
            UserDTO dto = new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getAuthorities(), Arrays.asList(new UserDTO.Images("12334")));
            if (util.validateToken(jwt, dto)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, dto.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
