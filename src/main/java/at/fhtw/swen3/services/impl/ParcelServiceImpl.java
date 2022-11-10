package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.controller.rest.ParcelApiController;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.vaildation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
//@Slf4j
public class ParcelServiceImpl implements ParcelService {

   private static final Logger log = LoggerFactory.getLogger(ParcelApiController.class);

   @Autowired
   protected ParcelRepository parcelRepository;

   @Autowired
   protected RecipientRepository recipientRepository;

   @Autowired
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
