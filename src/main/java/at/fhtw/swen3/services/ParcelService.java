package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.*;

public interface ParcelService {

    WarehouseEntity findParent(WarehouseEntity warehouse, HopEntity truck);

    NewParcelInfoEntity submitParcel(ParcelEntity parcelEntity);

   void reportParcelDelivery(String trackingId);
  TrackingInformationEntity getParcelTrackingInformation(String trackinginfo) ;


    NewParcelInfoEntity transferParcel(String trackingId, ParcelEntity parcelEntity);
}
