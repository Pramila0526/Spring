package com.bridgelabz.utility;



import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Tokenutility {
	
	private final String SECRET_KEY="secret";
	
	public String createToken(String id)
	{
		
		return Jwts.builder().setSubject(id).setIssuedAt(new Date(System.currentTimeMillis())).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public String getUserToken(String token)
	{
		    System.out.println("token :-"+token);
		    Claims claim = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	      
		  
		    return claim.getSubject();
		
	}

}
