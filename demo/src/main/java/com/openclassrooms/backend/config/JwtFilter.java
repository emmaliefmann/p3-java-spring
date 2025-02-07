package com.openclassrooms.backend.config;

import com.openclassrooms.backend.services.CustomUserDetailsService;
import com.openclassrooms.backend.services.JWTService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  JWTService jwtService;

  @Autowired
  ApplicationContext context;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
    String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;
    String role = null;

    if(authHeader !=null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
      username = jwtService.extractUsername(token);
      role = jwtService.extractRole(token);
    }

    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
      // directly importing UserDetails would create circular dependency
      UserDetails userDetails = context.getBean(CustomUserDetailsService.class).loadUserByUsername(username);
      if(jwtService.validateToken(token, userDetails)){
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(role);
        UsernamePasswordAuthenticationToken authToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
