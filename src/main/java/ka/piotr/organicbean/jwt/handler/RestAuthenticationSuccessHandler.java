package ka.piotr.organicbean.jwt.handler;

import com.auth0.jwt.JWT;
import ka.piotr.organicbean.jwt.JwtAlgorithm;
import ka.piotr.organicbean.jwt.SecurityConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static ka.piotr.organicbean.jwt.SecurityConstant.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtAlgorithm algorithm;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        String token = JWT.create()
                .withSubject(principal.getUsername())
//                .withClaim("roles", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(TOKEN_EXPIRATION_TIME_DAYS)))
                .sign(algorithm.getAlgorithm());

        String refreshToken = JWT.create()
                .withSubject(principal.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(java.sql.Date.valueOf(LocalDate.now().plusDays(REFRESH_TOKEN_EXPIRATION_TIME_DAYS)))
                .sign(algorithm.getAlgorithm());

        response.setHeader(ACCESS_TOKEN_HEADER, PREFIX.concat(token));
        response.setHeader(REFRESH_TOKEN_HEADER, PREFIX.concat(refreshToken));
    }
}
