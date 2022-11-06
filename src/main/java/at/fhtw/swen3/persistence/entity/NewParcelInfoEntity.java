package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "newParcelinfo")
public class NewParcelInfoEntity {
    @Column
    @NotNull
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;
    @Id
    private Long id;
}
