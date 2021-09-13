package retros.userservice.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import retros.userservice.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Component
public class TokenHandler extends OncePerRequestFilter {

    private static final String AUTH = "Authorization";

    private static final String BEARER = "Bearer ";

    private static final String ISSUER = "retros";

    private final static String KEY = "lslASDaC:dk2139snna@@das_-€!2sllasdnKSFasdJFsadjnad_lFD(2123EE!?.dhHDsixretos___SWLsPlqMbJd?2s3490.seWrt";

    public String generateToken( final User user ) {
        return Jwts.builder().setSubject(user.getUsername())
                             .claim("id", user.getUserId())
                             .setIssuer(ISSUER)
                             .setIssuedAt(new Date())
                             // Token is valid for 2 weeks
                             .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                             .signWith(Keys.hmacShaKeyFor(KEY.getBytes()))
                             .compact();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTH);

        if (Objects.isNull(authHeader) || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.replace(BEARER, "");
            Jws<Claims> claims = Jwts.parserBuilder()
                                     .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes()))
                                     .build().parseClaimsJws(token);

            Claims body = claims.getBody();
            String username = body.getSubject();
            var id = new SimpleGrantedAuthority( body.get("id").toString() );
            List<SimpleGrantedAuthority> authorities = Collections.singletonList(id);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);

        } catch (JwtException e) {
            throw new RuntimeException("Token cannot be trusted");
        }
    }
}
