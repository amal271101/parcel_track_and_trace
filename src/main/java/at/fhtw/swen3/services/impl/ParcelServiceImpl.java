package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.vaildation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor

@Slf4j
public class ParcelServiceImpl implements ParcelService {

   private ParcelRepository parcelRepository;
   private RecipientRepository recipientRepository;

   private Validator myValidator;

   @Override
   public void createParcelDelivery() {

   }

   @Override
   public void createParcelHop() {

   }

   @Override
   public boolean createParcel(ParcelEntity parcelEntity) {

      parcelEntity.setTrackingId("PYJRB4HZ6");
      HopArrivalEntity hop = new HopArrivalEntity();
      hop.setDateTime(OffsetDateTime.now());
      List<HopArrivalEntity> visitedHops = new ArrayList<>();

      visitedHops.add(hop);
      log.info(String.valueOf(visitedHops.get(0).getDateTime()));
      parcelEntity.setFutureHops(visitedHops);
      parcelEntity.setVisitedHops(visitedHops);
      parcelEntity.setState(TrackingInformationEntity.StateEnumEntity.PICKUP);

      if(!myValidator.validate(parcelEntity)){return false;}
      recipientRepository.save(parcelEntity.getRecipient());
      recipientRepository.save(parcelEntity.getSender());
      parcelRepository.save(parcelEntity);

      return true;
   }

   @Override
   public void getParcel() {

   }

   @Override
   public void updateParcel() {

   }
}
