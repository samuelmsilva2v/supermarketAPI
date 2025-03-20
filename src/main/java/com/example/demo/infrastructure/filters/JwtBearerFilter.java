package com.example.demo.infrastructure.filters;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtBearerFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) servletResponse;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		var secretKey = "468041be-f345-4188-8136-4bdeef74b376";

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			filterChain.doFilter(request, response);
			return;
		}
		
		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado.");

			return;
		}
		
		try {
			final String token = authHeader.substring(7);
			Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			request.setAttribute("claims", claims);
			filterChain.doFilter(request, response);
		} 
		catch (Exception e) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado.");
		}
	}
}
