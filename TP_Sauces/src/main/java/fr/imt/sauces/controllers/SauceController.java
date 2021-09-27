package fr.imt.sauces.controllers;

import fr.imt.sauces.exceptions.SauceNotAdded;
import fr.imt.sauces.exceptions.SaucesNotFoundException;
import fr.imt.sauces.models.Sauce;
import fr.imt.sauces.services.ISauceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class SauceController {

    @Autowired
    private ISauceService sauceService;

    @GetMapping( value = "/sauces")
    public ResponseEntity<List<Sauce>> getSauces()
    {
        return new ResponseEntity<>(sauceService.getSauces(), HttpStatus.OK);
    }

    @GetMapping( value = "/sauces/{guid}")
    public ResponseEntity<Sauce> getSauceById(@PathVariable("guid") String guid) throws SaucesNotFoundException {
        try
        {
            Sauce sauce = sauceService.getSauceById(guid);
            return new ResponseEntity<Sauce>(sauce, HttpStatus.OK);
        }
        catch (SaucesNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/sauces/search")
    public ResponseEntity<List<Sauce>> getSauceByName(@RequestParam (name = "q") String query)
    {
        List<Sauce> sauceList = sauceService.getSauceByName(query);
        return new ResponseEntity<>(sauceList, HttpStatus.OK);
    }

    @PostMapping(value = "/sauces")
    public ResponseEntity<Sauce> addSauce(@RequestBody Sauce sauce)
    {
        return new ResponseEntity<>(sauceService.addSauce(sauce), HttpStatus.CREATED);
    }

    @PutMapping(value = "/sauces/{guid}")
    public ResponseEntity<Sauce> modifySauce(@PathVariable String guid, @RequestBody Sauce sauce)
    {
        try
        {
            return new ResponseEntity<>(sauceService.modifySauce(guid, sauce), HttpStatus.OK);
        }
        catch (SaucesNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(value = "/sauces/{guid}")
    public ResponseEntity<Sauce> deteleSauce(@PathVariable String guid)
    {
        try
        {
            return new ResponseEntity<>(sauceService.deleteSauce(guid), HttpStatus.OK);
        }
        catch (SaucesNotFoundException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}