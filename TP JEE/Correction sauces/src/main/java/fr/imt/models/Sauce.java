package fr.imt.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sauce")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Sauce {

    @Id
    private String guid;

    @NotNull
    @NotBlank
    @Size(max = 25)
    private String name;

    @NotNull
    @Min(0)
    @Max(20)
    private Integer rating;

    @Size(max = 250)
    private String details;

    public Sauce() {
        guid = UUID.randomUUID().toString();
    }
}
