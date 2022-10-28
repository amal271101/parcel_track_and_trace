package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column
    @Pattern(regexp = "[A-Za-z-/ öÖÜüÄä]+")
    private String name;

    @Column
    @Pattern(regexp = "[A-Z]([a-z]?ß?)+\\s([0-9])+(/?[0-9-])*([a-z])*")
    private String street;

    @Column
    @Pattern(regexp = "[A]-[0-9]{4}")
    private String postalCode;

    @Column
    @Pattern(regexp = "[A-Za-z-/ öÖÜüÄä]+")
    private String city;

    @Column
    private String country;
}
