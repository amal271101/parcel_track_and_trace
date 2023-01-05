package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
public class NewParcelInfoEntity {
    @NotNull
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
