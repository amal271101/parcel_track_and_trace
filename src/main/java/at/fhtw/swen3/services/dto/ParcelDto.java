package at.fhtw.swen3.services.dto;

import at.fhtw.swen3.persistence.Recipient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParcelDto {
    private  Float weight;
    private RecipientDto recipient;
    private RecipientDto sender;
}
