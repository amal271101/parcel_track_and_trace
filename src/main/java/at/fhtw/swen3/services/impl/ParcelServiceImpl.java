package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.vaildation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParcelServiceImpl implements ParcelService{
   @Autowired
   protected ParcelRepository parcelRepository;

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
      myValidator.validate(parcelEntity);
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
