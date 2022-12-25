package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.NewParcelInfoEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.vaildation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Slf4j
public class ParcelServiceImpl implements ParcelService {

   private final ParcelRepository parcelRepository;
   private final RecipientRepository recipientRepository;
   private final Validator myValidator;

    public String generateTrackingId() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'Z')
                .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS)
                .build();
        return generator.generate(9);
    }

   @Override
   public void createParcelDelivery() {

   }

   @Override
   public void createParcelHop() {

   }

   @Override
   public NewParcelInfoEntity createParcel(ParcelEntity parcelEntity) {

      parcelEntity.setTrackingId(generateTrackingId());
      HopArrivalEntity hop = new HopArrivalEntity();
      hop.setDateTime(OffsetDateTime.now());
      hop.setCode("ABAB790");
      List<HopArrivalEntity> visitedHops = new ArrayList<>();

      visitedHops.add(hop);
      log.info(String.valueOf(visitedHops.get(0).getDateTime()));
      parcelEntity.setFutureHops(visitedHops);
        parcelEntity.setVisitedHops(visitedHops);
     parcelEntity.setState(TrackingInformationEntity.StateEnumEntity.PICKUP);

      if(!myValidator.validate(parcelEntity)){
         return  new NewParcelInfoEntity();
      }
      recipientRepository.save(parcelEntity.getRecipient());
      recipientRepository.save(parcelEntity.getSender());
      parcelRepository.save(parcelEntity);

      NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();

      newParcelInfoEntity.setTrackingId(generateTrackingId());

      return newParcelInfoEntity;
   }

   @Override
   public void getParcel() {

   }

   @Override
   public void updateParcel() {

   }
}
