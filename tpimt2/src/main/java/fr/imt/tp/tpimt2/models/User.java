package fr.imt.tp.tpimt2.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private String name;
    private String firstname;
}
