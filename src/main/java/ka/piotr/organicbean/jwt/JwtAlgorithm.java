package ka.piotr.organicbean.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtAlgorithm {

    private final String secret;
    private final Algorithm algorithm;

    public JwtAlgorithm(@Value("${jwt.secret}")String secret) {
        this.secret = secret;
        this.algorithm = Algorithm.HMAC512(this.secret);
    }
    public Algorithm getAlgorithm() {
        return algorithm;
    }
}
