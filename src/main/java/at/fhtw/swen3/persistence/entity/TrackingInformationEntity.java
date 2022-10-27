package at.fhtw.swen3.persistence.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class TrackingInformationEntity {
    public enum StateEnumEntity {
        PICKUP("Pickup"),

        INTRANSPORT("InTransport"),

        INTRUCKDELIVERY("InTruckDelivery"),

        TRANSFERRED("Transferred"),

        DELIVERED("Delivered");

        private String value;

        StateEnumEntity(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }


    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private Long id;

    @Column()
    private StateEnumEntity state;

    @Column()
    @OneToMany
    @NotNull(message = "futureHops cannot be null")
    private List<HopArrivalEntity> futureHops;

    @Column()
    @OneToMany
    @NotNull(message = "visitedHops cannot be null")
    private List<HopArrivalEntity> visitedHops;

}
