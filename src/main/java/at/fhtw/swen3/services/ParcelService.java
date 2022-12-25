package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.NewParcelInfoEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;

public interface ParcelService {
    public void createParcelDelivery();

    public void createParcelHop();

   public NewParcelInfoEntity createParcel(ParcelEntity parcelEntity);

    public void getParcel();

    public void updateParcel();
}
