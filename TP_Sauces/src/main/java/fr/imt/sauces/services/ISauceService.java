package fr.imt.sauces.services;

import fr.imt.sauces.exceptions.SaucesNotFoundException;
import fr.imt.sauces.models.Sauce;
import java.util.List;

public interface ISauceService {
    List<Sauce> getSauces();
    Sauce getSauceById(String guid) throws SaucesNotFoundException;
    List<Sauce> getSauceByName(String name);
    Sauce addSauce(Sauce sauce);
    Sauce modifySauce(String guid, Sauce sauce) throws SaucesNotFoundException;
    Sauce deleteSauce(String guid) throws SaucesNotFoundException;
}
