package fr.imt.sauces.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

//Vient de Lombok
@Getter
@Setter
@Entity
public class Sauce {

    @Id
    private String id;
    private String name;
    private Integer rating;
    private String details;

    public Sauce(){
        id = UUID.randomUUID().toString();
    }
}
