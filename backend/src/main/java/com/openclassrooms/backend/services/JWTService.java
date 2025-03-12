package com.openclassrooms.backend.services;

import com.openclassrooms.backend.dto.LoginRequestDTO;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

  private final String secretKey;
  private final int tokenDurationMinutes = 30;

  public JWTService(@Value("${jwt.secret}") String key) {
    // This method regenerates a secret key each time the application starts, rather than storing a secret key
    // If a longer validity is required, the secret key should be stored externally and not be regenerated each time
    try {
      KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
      SecretKey sk = keyGen.generateKey();
      this.secretKey = Base64.getEncoder().encodeToString(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256").getEncoded());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public String generateToken(String email) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", "ROLE_USER");
    return Jwts.builder()
      .claims()
      .add(claims)
      .subject(email)
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + tokenDurationMinutes * 60 * 1000))
      .and()
      .signWith(getKey())
      .compact();
  }

  private SecretKey getKey() {
    if (secretKey == null || secretKey.isEmpty()) {
      throw new IllegalArgumentException("Secret key must not be null or empty");
    }
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
      .verifyWith(getKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  public String extractEmail(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    final String userName = extractEmail(token);
    return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public String extractRole(String token) {
    Claims claims = extractAllClaims(token);
    return claims.get("role", String.class);
  }
}
