package ka.piotr.organicbean.jwt.filter;

import com.auth0.jwt.JWT;
import ka.piotr.organicbean.jwt.JwtClassParams;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtClassParams jwtParams;
    private final UserDetailsService userDetailsService;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtClassParams jwtParams,
                                  UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtParams = jwtParams;
        this.userDetailsService = userDetailsService;
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
        String token = request.getHeader(jwtParams.getAccessTokenHeader());

        if (token != null && token.startsWith(jwtParams.getPrefix())){
            String username = JWT.require(jwtParams.getAlgorithm())
                    .build()
                    .verify(token.replace(jwtParams.getPrefix(), ""))
                    .getSubject();

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),null,userDetails.getAuthorities());
        }
        return null;
    }
}
