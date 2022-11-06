package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entity.ParcelEntity;

public interface ParcelService {

    public void createParcelDelivery();

    public void createParcelHop();

   public boolean createParcel(ParcelEntity parcelEntity);

    public void getParcel();

    public void updateParcel();
}
