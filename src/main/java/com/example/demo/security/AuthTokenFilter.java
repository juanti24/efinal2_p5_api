package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	private static final Logger Log = LoggerFactory.getLogger(JwtUtils.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String jwt = this.parseJwt(request);
			if (jwt != null && this.jwtUtils.validateJwtToken(jwt)) {
				// Vamos a generar una autenticación
				// Necesitamos el nombre

				String userName = this.jwtUtils.getuseNameFromjwtToken(jwt);

				// Generarmos la autenticacón

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,
						null, new ArrayList<>());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
		} catch (Exception e) {
			Log.error("", e);
		}
		
		filterChain.doFilter(request, response);

	}

	private String parseJwt(HttpServletRequest request) {
		String hearderAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(hearderAuth) && hearderAuth.startsWith("Bearer ")) {
			return hearderAuth.substring(7, hearderAuth.length());
		}
		return null;
	}

}
