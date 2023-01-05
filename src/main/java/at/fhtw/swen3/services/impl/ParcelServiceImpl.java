package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.impl.GeoEncodingServiceImpl;
import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
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

   private final TruckRepository truckRepository;

   private GeoEncodingServiceImpl geoEncodingServiceImpl = new GeoEncodingServiceImpl();


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

   public void predictFuturehops(){

   }
   /* private TruckEntity getNearestHop(RecipientEntity recipientEntity) {
        GeoCoordinateEntity geoCoordinateEntity= geoEncodingServiceImpl.encodeAddress(recipientEntity);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(geoCoordinateEntity.getLon(), geoCoordinateEntity.getLat()));
        System.out.println(geoCoordinateEntity.getLon()+ "lat: "+geoCoordinateEntity.getLat());
        System.out.println(point);
        return truckRepository.findNearestHop(point);
    }*/

    @Override
   public NewParcelInfoEntity createParcel(ParcelEntity parcelEntity) {

      parcelEntity.setTrackingId(generateTrackingId());
      HopArrivalEntity hop = new HopArrivalEntity();
      hop.setDateTime(OffsetDateTime.now());
      hop.setCode("ABAB790");
      List<HopArrivalEntity> visitedHops = new ArrayList<>();

      visitedHops.add(hop);
      log.info(String.valueOf(visitedHops.get(0).getDateTime()));
      predictFuturehops();
      parcelEntity.setFutureHops(visitedHops);
        parcelEntity.setVisitedHops(visitedHops);
     parcelEntity.setState(TrackingInformationEntity.StateEnumEntity.PICKUP);

     /* if(!myValidator.validate(parcelEntity)){
         return  new NewParcelInfoEntity();
      }*/

      recipientRepository.save(parcelEntity.getRecipient());
      recipientRepository.save(parcelEntity.getSender());
     // trackingInformationRepository.save(parcelEntity.g);
    parcelRepository.save(parcelEntity);


      NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();

      newParcelInfoEntity.setTrackingId(generateTrackingId());

       // getNearestHop(parcelEntity.getRecipient());
       // System.out.println("LOCATION: "+getNearestHop(parcelEntity.getSender()).getLocationCoordinates());

        return newParcelInfoEntity;
   }

    @Override
    public void reportParcelDelivery(String trackingId) {
        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();
        newParcelInfoEntity.setTrackingId(trackingId);
        if(myValidator.validate(newParcelInfoEntity)){
            parcelRepository.setStateToDelivered(trackingId);
        }
    }


    @Override
   public TrackingInformationEntity getParcelTrackInformation(String trackingId) {
        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();
        newParcelInfoEntity.setTrackingId(trackingId);
        ParcelEntity parcelEntity;
        TrackingInformationEntity trackingInformationEntity = new TrackingInformationEntity();

        if(myValidator.validate(newParcelInfoEntity)){
            parcelEntity = parcelRepository.findByTrackingId(trackingId);
            trackingInformationEntity.setState(parcelEntity.getState());
            trackingInformationEntity.setFutureHops(parcelEntity.getFutureHops());
            trackingInformationEntity.setVisitedHops(parcelEntity.getVisitedHops());
        }

        return trackingInformationEntity;
    }

   @Override
   public void updateParcel() {

   }
}
