package at.fhtw.swen3.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecipientDto {
    private String name;
    private String street;
    private String postalCode;
    private String city;
    private String country;
}
