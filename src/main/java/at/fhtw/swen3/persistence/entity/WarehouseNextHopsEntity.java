package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer traveltimeMins;

    @OneToOne
    @JoinColumn()
    private HopEntity hop;
}
