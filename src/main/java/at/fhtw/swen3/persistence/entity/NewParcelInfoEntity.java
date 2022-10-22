package at.fhtw.swen3.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class NewParcelInfoEntity {
   @NotNull
   @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;
}
