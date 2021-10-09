package ka.piotr.organicbean.security.repository;

import ka.piotr.organicbean.security.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
