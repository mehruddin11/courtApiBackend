package com.nagarro.ProductSearchApi.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private String secret = "jwtTokenKey";
	
	@Autowired
	private UserRepository userRepository;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expiration date from JWT token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
//	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//	    claims.put("roles", userDetails.getAuthorities()); 
//	    claims.put("userId", userDetails.());
//	    return doGenerateToken(claims, userDetails.getUsername());
//	}
//	
	public String generateToken(UserDetails userDetails) {
	    Map<String, Object> claims = new HashMap<>();
	     claims.put("roles", userDetails.getAuthorities());

	    if (userDetails instanceof org.springframework.security.core.userdetails.User) {
	        User user = userRepository.findByUsername(userDetails.getUsername());
	        claims.put("userId", user.getId());
	    }

	    return doGenerateToken(claims, userDetails.getUsername());
	}

	public Boolean validateResetToken(String token, String email) {
	    final String userEmail = getUsernameFromToken(token);
	    return (userEmail.equals(email) && !isTokenExpired(token));
	}
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
