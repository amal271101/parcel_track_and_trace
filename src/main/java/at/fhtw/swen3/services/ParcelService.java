package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.NewParcelInfoEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;

public interface ParcelService {
     void createParcelDelivery();

     void createParcelHop();

    NewParcelInfoEntity createParcel(ParcelEntity parcelEntity);

   void reportParcelDelivery(String trackingId);
  TrackingInformationEntity getParcelTrackInformation(String trackinginfo) ;

    void updateParcel();
}
