package ka.piotr.organicbean.jwt.handler;

import com.auth0.jwt.JWT;
import ka.piotr.organicbean.jwt.JwtClassParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtClassParams jwtParams;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtParams.getTokenExpirationTimeDays())))
                .sign(jwtParams.getAlgorithm());

        String refreshToken = JWT.create()
                .withSubject(principal.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtParams.getTokenExpirationTimeDays())))
                .sign(jwtParams.getAlgorithm());

        response.setHeader(jwtParams.getAccessTokenHeader(), jwtParams.getPrefix().concat(token));
        response.setHeader(jwtParams.getRefreshTokenHeader(), jwtParams.getPrefix().concat(refreshToken));
    }
}
