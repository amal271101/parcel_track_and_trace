package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.*;

public interface ParcelService {

    WarehouseEntity findParent(WarehouseEntity warehouse, HopEntity truck);

    NewParcelInfoEntity submitParcel(ParcelEntity parcelEntity) throws BLException;

    void reportParcelDelivery(String trackingId) throws BLException;
    TrackingInformationEntity getParcelTrackingInformation(String trackinginfo) throws BLDataNotFoundException, BLException;
    NewParcelInfoEntity transferParcel(String trackingId, ParcelEntity parcelEntity) throws BLException;

    void reportParcelHop(String trackingId, String code) throws BLException;
}

