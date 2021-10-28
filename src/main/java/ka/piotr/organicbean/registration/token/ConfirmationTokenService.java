package ka.piotr.organicbean.registration.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepo;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepo.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepo.findByToken(token);
    }

    @Transactional
    public void setConfirmedAt(String token) {
        confirmationTokenRepo.findByToken(token)
                .orElseThrow()
                .setConfirmedAt(LocalDateTime.now());
    }
}
