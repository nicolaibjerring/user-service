package retros.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retros.userservice.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Intentionally left blank
    // This provides an interface to do CRUD operation to the table "users" in
    // the PostgreSQL database
}
