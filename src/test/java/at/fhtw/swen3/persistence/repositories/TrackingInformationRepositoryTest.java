package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

public class TrackingInformationRepositoryTest {
 /*   @Autowired
    private TrackingInformationRepository trackingInformationRepository;

    @Test
    void saveAndDeleteTrackingInformation() {


        TrackingInformationEntity trackingInformationEntity = new TrackingInformationEntity();
        trackingInformationEntity.setState(TrackingInformationEntity.StateEnumEntity.PICKUP);
        HopArrivalEntity hop = new HopArrivalEntity();
        hop.setDateTime(OffsetDateTime.now());
        List<HopArrivalEntity> visitedHops = new ArrayList<>();
        trackingInformationEntity.setVisitedHops(visitedHops);
        trackingInformationEntity.setFutureHops(visitedHops);

        trackingInformationRepository.save(trackingInformationEntity);
        assertEquals(trackingInformationRepository.findByState(TrackingInformationEntity.StateEnumEntity.PICKUP).getState(),trackingInformationEntity.getState());
        trackingInformationRepository.delete(trackingInformationEntity);
        assertNull(trackingInformationRepository.findByState(trackingInformationEntity.getState()));

    }
*/
}