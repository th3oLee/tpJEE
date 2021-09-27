package fr.imt.sauces.services;

import fr.imt.sauces.exceptions.SauceNotAdded;
import fr.imt.sauces.exceptions.SaucesNotFoundException;
import fr.imt.sauces.models.Sauce;
import fr.imt.sauces.repositories.SauceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SauceService implements ISauceService {
    //private List<Sauce> sauces;

    @Autowired
    private SauceRepository sauceRepository;

    public List<Sauce> getSauces()
    {
        return sauceRepository.findAll();

        /*
        sauces = new ArrayList<Sauce>();

        Sauce moutarde = new Sauce();
        moutarde.setName("Moutarde");
        moutarde.setRating(15);
        moutarde.setDescription("Extra forte");
        sauces.add(moutarde);

        Sauce ketchup = new Sauce();
        ketchup.setName("Ketchup");
        ketchup.setRating(11);
        sauces.add(ketchup);

        Sauce auPoivre = new Sauce();
        auPoivre.setName("Sauce au poivre");
        auPoivre.setRating(11);
        auPoivre.setDescription("On aime la sauce au poivre");
        sauces.add(auPoivre);

        return sauces;
         */
    }

    public Sauce getSauceById(String guid) throws SaucesNotFoundException {
        return sauceRepository.findById(guid).orElseThrow(SaucesNotFoundException::new);

        /*
        for(Sauce sauce : sauces)
        {
            if(sauce.getGuid().equals(guid)) return sauce;
        }

        throw new SaucesNotFoundException();
         */
    }

    public List<Sauce> getSauceByName(String name) {
        return sauceRepository.findByNameContains(name);

        /*
        List<Sauce> sauceList = new ArrayList<>();

        for(Sauce sauce : sauces)
        {
            if(sauce.getName().contains(name)) sauceList.add(sauce);
        }

        return sauceList;
         */
    }

    public Sauce addSauce(Sauce sauce) {
        return sauceRepository.save(sauce);
    }

    public Sauce modifySauce(String guid, Sauce sauceNew) throws SaucesNotFoundException {
        Sauce sauce = sauceRepository.findById(guid).orElseThrow(SaucesNotFoundException::new);

        sauce.setName(sauceNew.getName());
        sauce.setDetails(sauceNew.getDetails());
        sauce.setRating(sauceNew.getRating());

        Sauce updatedSauce = sauceRepository.save(sauce);

        return updatedSauce;
    }

    public Sauce deleteSauce(String guid) throws SaucesNotFoundException {
        Sauce sauceToDelete = sauceRepository.findById(guid).orElseThrow(SaucesNotFoundException::new);
        sauceRepository.deleteById(guid);

        return sauceToDelete;
    }
}
