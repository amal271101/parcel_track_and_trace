package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.Hop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WarehouseNextHopsEntity {

    private Integer traveltimeMins;

    private Hop hop;
}
