package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "wareHouseNextHops")
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer traveltimeMins;

    @NotNull
    @OneToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name="hop_id")
    private HopEntity hop;
}
