package fr.imt.tp.tpimt2.repositories;

import fr.imt.tp.tpimt2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameContains(String query);
}
