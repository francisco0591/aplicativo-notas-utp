package com.utp.microservicios.app.notas.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.utp.microservicios.app.notas.entity.Usuario;


@Component
public class JWTUtil {

	@Value("${springbootjjwt.jjwt.secret}")
	private String secret;

	@Value("${springbootjjwt.jjwt.expiration}")
	private String expirationTime;

	private Key key;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public String getUsernameFromToken(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	public Date getExpirationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Usuario user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", Arrays.asList(user.getRoles()) );
		System.out.println("tokken:"+doGenerateToken(claims, user.getUsername()));
		return doGenerateToken(claims, user.getUsername());
	}

	public String doGenerateToken(Map<String, Object> claims, String username) {
		try {
			Long expirationTimeLong = Long.parseLong(expirationTime); // in second

			final Date createdDate = new Date();
			final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
            System.out.println("token:"+Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(createdDate)
					.setExpiration(expirationDate).signWith(key).compact());
			return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(createdDate)
					.setExpiration(expirationDate).signWith(key).compact();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

}
