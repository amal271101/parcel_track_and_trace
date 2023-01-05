package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@Entity
public class WarehouseEntity extends HopEntity{
    @Column
    private Integer level;

    @Column
    @NotNull
    @NotEmpty(message = "nextHops cannot be null")
    @OneToMany
    private List<WarehouseNextHopsEntity> nextHops;



}
