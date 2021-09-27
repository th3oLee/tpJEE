package fr.imt.controllers;

import fr.imt.exceptions.SauceNotFoundException;
import fr.imt.models.Sauce;
import fr.imt.services.SauceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SauceController {

    @Autowired
    private SauceService sauceService;

    @GetMapping(value = "/sauces")
    public ResponseEntity<List<Sauce>> listSauces() {
        return new ResponseEntity<>(sauceService.getSauces(), HttpStatus.OK);
    }

    @GetMapping(value = "/sauces/search")
    public ResponseEntity<List<Sauce>> searchSauce(@RequestParam("q") final String query) {
        return new ResponseEntity<>(sauceService.searchSauce(query), HttpStatus.OK);
    }

    @GetMapping(value = "/sauces/{guid}")
    public ResponseEntity<Sauce> getSauce(@PathVariable("guid") final String guid) {
        try {
            return new ResponseEntity<>(sauceService.getSauce(guid),HttpStatus.OK);
        } catch (SauceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping( value = "/sauces")
    public ResponseEntity addSauce(@Valid @RequestBody Sauce sauce){
        sauceService.addSauce(sauce);
        return new ResponseEntity(sauce, HttpStatus.CREATED);
    }

    @PutMapping( value = "/sauces/{guid}")
    public ResponseEntity<Sauce> editSauce(@PathVariable("guid") final String guid, @Valid @RequestBody Sauce sauce) {
        try {
            sauceService.editSauce(sauce, guid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(SauceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping( value = "/sauces/{guid}")
    public ResponseEntity deleteSauce(@PathVariable("guid") final String guid) {
        try {
            sauceService.deleteSauce(guid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SauceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
