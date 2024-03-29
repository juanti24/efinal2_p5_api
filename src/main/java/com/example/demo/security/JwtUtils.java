package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {
	
	private static final Logger Log = LoggerFactory.getLogger(JwtUtils.class);

	public boolean validateJwtToken(String auhToken) {
		
		try {
		Jwts.parser().setSigningKey("semilla1").parseClaimsJws(auhToken);
		
		return true;
		
		}catch(Exception e){
			Log.error("",e);
		}
		return false;
	}
	
	public String getuseNameFromjwtToken (String token) {
		
		return Jwts.parser().setSigningKey("semilla1").parseClaimsJws(token).getBody().getSubject();
		
	
	}
	
	
	
}
