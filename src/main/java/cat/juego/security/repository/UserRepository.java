package cat.juego.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.juego.security.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
