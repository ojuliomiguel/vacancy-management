package br.com.bluelobster.vacancy_management.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.bluelobster.vacancy_management.provides.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class SecurityCandidateFilter extends OncePerRequestFilter {

  @Autowired
  private JWTCandidateProvider jwtProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String header = request.getHeader("Authorization");

    if (request.getRequestURI().startsWith("/candidate")) {
      if (header != null) {
        var token = this.jwtProvider.validateToken(header);

        if (token == null) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }

        request.setAttribute("candidate_id", token.getSubject());
        var roles = token.getClaim("roles").asList(Object.class);

        var grants = roles
            .stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toString()))
            .toList();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            token.getSubject(),
            null,
            grants);

        SecurityContextHolder.getContext().setAuthentication(auth);

      }
    }

    filterChain.doFilter(request, response);
  }

}
