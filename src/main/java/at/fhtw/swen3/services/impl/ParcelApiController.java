package at.fhtw.swen3.services.impl;


import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.NewParcelInfoEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.ParcelApi;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.mapper.NewParcelInfoMapper;
import at.fhtw.swen3.services.mapper.ParcelEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Generated;
import javax.validation.*;
@RequestMapping("${openapi.parcelLogisticsService.base-path:}")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-24T11:01:08.846404Z[Etc/UTC]")
@Controller
public class ParcelApiController implements ParcelApi {
    private static final Logger log = LoggerFactory.getLogger(ParcelApiController.class);

    private final NativeWebRequest request;

    @Autowired
    public ParcelApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        NewParcelInfo newParcelInfodto = new NewParcelInfo();
        newParcelInfodto.setTrackingId(trackingId);

        NewParcelInfoEntity newParcelInfoEntity = NewParcelInfoMapper.INSTANCE.dtoToEntity(newParcelInfodto);

        Set<ConstraintViolation<NewParcelInfoEntity>> violations = validator.validate(newParcelInfoEntity);
        if (violations.size() != 0) {
            for (ConstraintViolation<NewParcelInfoEntity> violation : violations) {
                log.error(violation.getMessage());

            }
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        NewParcelInfo newParcelInfodto = new NewParcelInfo();
        newParcelInfodto.setTrackingId(trackingId);
        HopArrival hop = new HopArrival();
        hop.setCode(code);
        hop.setDateTime(OffsetDateTime.now());

        ParcelEntity parcelEntity = ParcelEntityMapper.INSTANCE.NewParcelInfoDtoToEntity(newParcelInfodto);
        HopArrivalEntity hopArrivalEntity = HopArrivalMapper.INSTANCE.dtoToEntity(hop);

        Set<ConstraintViolation<HopArrivalEntity>> hopArrivalViolations = validator.validate(hopArrivalEntity);

        if (hopArrivalViolations.size() != 0) {
            for (ConstraintViolation<HopArrivalEntity> violation : hopArrivalViolations) {
                log.error(violation.getMessage());

            }
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }


        List<HopArrivalEntity> visitedHops = new ArrayList<>();

        visitedHops.add(hopArrivalEntity);

        parcelEntity.setVisitedHops(visitedHops);

        Set<ConstraintViolation<ParcelEntity>> parcelViolations = validator.validate(parcelEntity);

        if (parcelViolations.size() != 0) {
            for (ConstraintViolation<ParcelEntity> violation : parcelViolations) {
                log.error(violation.getMessage());

            }
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel
    ) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ParcelEntity parcelEntity = ParcelEntityMapper.INSTANCE.ParcelDtoToEntity(parcel);

        Set<ConstraintViolation<ParcelEntity>> parcelViolations = validator.validate(parcelEntity);

        if (parcelViolations.size() != 0) {
            for (ConstraintViolation<ParcelEntity> violation : parcelViolations) {
                log.error(violation.getMessage());
            }
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<TrackingInformation> trackParcel(String trackingId
    ) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        NewParcelInfo newParcelInfodto = new NewParcelInfo();
        newParcelInfodto.setTrackingId(trackingId);

        NewParcelInfoEntity newParcelInfoEntity = NewParcelInfoMapper.INSTANCE.dtoToEntity(newParcelInfodto);

        Set<ConstraintViolation<NewParcelInfoEntity>> violations = validator.validate(newParcelInfoEntity);
        if (violations.size() != 0) {
            for (ConstraintViolation<NewParcelInfoEntity> violation : violations) {
                log.error(violation.getMessage());

            }
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        NewParcelInfo newParcelInfodto = new NewParcelInfo();
        newParcelInfodto.setTrackingId(trackingId);
        ParcelEntity parcelEntity = ParcelEntityMapper.INSTANCE.ParcelNewParcelinfoDtoToEntity(parcel, newParcelInfodto);


        Set<ConstraintViolation<ParcelEntity>> violations = validator.validate(parcelEntity);
        if (violations.size() != 0) {
            for (ConstraintViolation<ParcelEntity> violation : violations) {
                log.error(violation.getMessage());
            }
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
