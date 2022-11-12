package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;

public interface ParcelService {
    public void createParcelDelivery();

    public void createParcelHop();

   public boolean createParcel(ParcelEntity parcelEntity);

    public void getParcel();

    public void updateParcel();
}
