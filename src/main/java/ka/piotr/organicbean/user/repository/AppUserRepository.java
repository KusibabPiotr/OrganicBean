package ka.piotr.organicbean.user.repository;

import ka.piotr.organicbean.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String email);
    boolean existsByUsername(String email);
}
