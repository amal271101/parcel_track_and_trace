package at.fhtw.swen3.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class RecipientEntity {

    @Pattern(regexp = "[A-Za-z-/ öÖÜüÄä]+")
    private String name;

    @Pattern(regexp = "[A-Z]([a-z]?ß?)+\\s([0-9])+(/?[0-9-])*([a-z])*")
    private String street;

    @Pattern(regexp = "[A]-[0-9]{4}")
    private String postalCode;

    @Pattern(regexp = "[A-Za-z-/ öÖÜüÄä]+")
    private String city;

    private String country;
}
