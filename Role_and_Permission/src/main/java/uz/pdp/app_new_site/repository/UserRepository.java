package uz.pdp.app_new_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.app_new_site.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

      boolean existsByUsername(String username);

      Optional<User>findByUsername(String username);

}
