package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.NewParcelInfoEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;

public interface ParcelService {


    NewParcelInfoEntity submitParcel(ParcelEntity parcelEntity);

   void reportParcelDelivery(String trackingId);
  TrackingInformationEntity getParcelTrackingInformation(String trackinginfo) ;


    NewParcelInfoEntity transferParcel(String trackingId, ParcelEntity parcelEntity);
}
