package ka.piotr.organicbean.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtClassParams {

    private final Long tokenExpirationTimeDays;
    private final Long refreshTokenExpirationTimeDays;
    private final String secret;
    private final String prefix;
    private final String accessTokenHeader;
    private final String refreshTokenHeader;
    private final Algorithm algorithm;

    public JwtClassParams(@Value("${jwt.tokenExpirationTimeDays}") Long tokenExpirationTimeDays,
                          @Value("${jwt.refreshTokenExpirationTimeDays}") Long refreshTokenExpirationTimeDays,
                          @Value("${jwt.secret}") String secret,
                          @Value("${jwt.tokenPrefix}") String prefix,
                          @Value("${jwt.accessTokenHeader}") String accessTokenHeader,
                          @Value("${jwt.refreshTokenHeader}")String refreshTokenHeader) {
        this.tokenExpirationTimeDays = tokenExpirationTimeDays;
        this.refreshTokenExpirationTimeDays = refreshTokenExpirationTimeDays;
        this.secret = secret;
        this.prefix = prefix;
        this.accessTokenHeader = accessTokenHeader;
        this.refreshTokenHeader = refreshTokenHeader;
        this.algorithm = Algorithm.HMAC256(secret);
    }

}
