package com.openclassrooms.backend.config;

import com.openclassrooms.backend.services.CustomUserDetailsService;
import com.openclassrooms.backend.services.JWTService;
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

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  JWTService jwtService;

  @Autowired
  ApplicationContext context;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    System.out.println("do filter internal method");
    String authHeader = request.getHeader("Authorization");
    String token = null;
    String email = null;
    String role = null;
      System.out.println("Token received" + authHeader);

    if(authHeader !=null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
      email = jwtService.extractEmail(token);
      System.out.println("email extracted in filter " + email);
      role = jwtService.extractRole(token);
    }

    if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      System.out.println("not authenticated");
      // directly importing UserDetails would create circular dependency
      UserDetails userDetails = context.getBean(CustomUserDetailsService.class).loadUserByUsername(email);
      if (jwtService.validateToken(token, userDetails)) {
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
