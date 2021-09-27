package fr.imt.repositories;

import fr.imt.models.Sauce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SauceRepository extends JpaRepository<Sauce, String> {
    List<Sauce> findByNameContainingIgnoreCase(String name);
}
