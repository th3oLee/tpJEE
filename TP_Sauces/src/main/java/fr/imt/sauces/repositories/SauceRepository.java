package fr.imt.sauces.repositories;

import fr.imt.sauces.models.Sauce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SauceRepository extends JpaRepository<Sauce, String> {
    List<Sauce> findByNameContains(String query);
}
