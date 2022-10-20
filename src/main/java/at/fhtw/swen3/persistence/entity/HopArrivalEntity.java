package at.fhtw.swen3.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;
@Getter
@Setter
@AllArgsConstructor
public class HopArrivalEntity {

    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;

    private String description;
    private OffsetDateTime dateTime;
}
