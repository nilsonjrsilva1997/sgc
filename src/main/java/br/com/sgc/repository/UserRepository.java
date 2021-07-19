package br.com.sgc.repository;

import br.com.sgc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String username);
    User findFirstByEmail(String email);

    String aux = "(*) > 0";
    @Query("SELECT CASE WHEN COUNT "+ aux +" THEN true ELSE false END FROM User u WHERE u.email = :email")

    boolean existsByEmail(@Param("email") String email);
}
