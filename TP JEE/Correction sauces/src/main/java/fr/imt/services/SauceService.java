package fr.imt.services;
import fr.imt.controllers.SauceController;
import fr.imt.exceptions.SauceNotFoundException;
import fr.imt.models.Sauce;
import fr.imt.repositories.SauceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SauceService {

    @Autowired
    private SauceRepository sauceRepository;

    public Sauce getSauce(String id) throws SauceNotFoundException {
        return sauceRepository.findById(id).orElseThrow(SauceNotFoundException::new);
    }

    public List<Sauce> searchSauce(String query) {
        return sauceRepository.findByNameContainingIgnoreCase(query);
    }

    public List<Sauce> getSauces() {
        return sauceRepository.findAll();
    }

    public void addSauce(Sauce sauce) {
        sauceRepository.save(sauce);
    }

    public void deleteSauce(String guid) throws SauceNotFoundException {
        Sauce sauce = getSauce(guid);
        sauceRepository.delete(sauce);
    }

    public void editSauce(Sauce sauce, String guid) throws SauceNotFoundException {
        getSauce(guid);
        sauce.setGuid(guid);
        sauceRepository.save(sauce);
    }
}
