package ka.piotr.organicbean.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import ka.piotr.organicbean.jwt.JwtAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ka.piotr.organicbean.jwt.SecurityConstant.*;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtAlgorithm algorithm;

    public JwtAuthorizationFilter(UserDetailsService userDetailsService,
                                  JwtAlgorithm algorithm) {
        this.userDetailsService = userDetailsService;
        this.algorithm = algorithm;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        if (authentication == null){
            filterChain.doFilter(request,response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader(ACCESS_TOKEN_HEADER);

        if (token != null){
            String username = JWT.require(algorithm.getAlgorithm())
                    .build()
                    .verify(token)
                    .getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            log.error("Username: " + userDetails.getUsername());
            log.error("Pass: " + userDetails.getPassword());
            userDetails.getAuthorities().forEach(e -> log.warn(e.getAuthority()));
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());
        }
        return null;
    }
}
