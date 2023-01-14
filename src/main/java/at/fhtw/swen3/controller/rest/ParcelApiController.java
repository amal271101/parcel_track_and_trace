package at.fhtw.swen3.controller.rest;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.services.BLDataNotFoundException;
import at.fhtw.swen3.services.BLException;
import at.fhtw.swen3.services.BLValidationException;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.mapper.NewParcelInfoMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.TrackingInformationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@RequestMapping("${openapi.parcelLogisticsService.base-path:}")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-24T11:01:08.846404Z[Etc/UTC]")
@Controller
@Slf4j
public class ParcelApiController implements ParcelApi {

    private ParcelService parcelService;
    private final NativeWebRequest request;

    public ParcelApiController(NativeWebRequest request, ParcelService parcelService) {
        this.request = request;
        this.parcelService= parcelService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<?> reportParcelDelivery(String trackingId) {
        try {
            parcelService.reportParcelDelivery(trackingId);

        } catch (BLException e) {
            log.error(e.getMessage());
            Error error=new Error();
            error.setErrorMessage(e.getMessage());
            if(e instanceof BLDataNotFoundException){return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);}
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> reportParcelHop(String trackingId, String code) {
        try {
            parcelService.reportParcelHop(trackingId,code);
        } catch (BLException e) {
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            if(e instanceof BLDataNotFoundException){return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);}
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<?> submitParcel(Parcel parcel) {
        ParcelEntity parcelEntity = new ParcelEntity();
        try {
           parcelEntity = ParcelMapper.INSTANCE.ParcelDtoToEntity(parcel);

        }catch (Exception e){
            log.error(e.getMessage());
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
        }

        NewParcelInfo newParcelInfo = null;
        try {
            newParcelInfo = NewParcelInfoMapper.INSTANCE.entityToDto(parcelService.submitParcel(parcelEntity));
        } catch (BLException e) {
            Error error = new Error();
            error.setErrorMessage(e.getMessage());

            if(e.getErrorEntity().getErrorMessage().equals("The address of sender or receiver was not found.")){
                return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
        }
        if(newParcelInfo!=null){
            return new ResponseEntity<NewParcelInfo>(newParcelInfo,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> trackParcel(String trackingId) {
        TrackingInformation trackingInformation= null;
        try {
            trackingInformation = TrackingInformationMapper.INSTANCE.entityToDto(parcelService.getParcelTrackingInformation(trackingId));
        } catch (BLException e) {
            log.error(e.getMessage());
            Error error=new Error();
            error.setErrorMessage(e.getMessage());
            if(e instanceof BLDataNotFoundException){return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);}
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(trackingInformation,HttpStatus.OK);
    }

    public ResponseEntity<?> transitionParcel(String trackingId, Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.ParcelDtoToEntity(parcel);

        NewParcelInfo newParcelInfo = null;
        try {
            newParcelInfo = NewParcelInfoMapper.INSTANCE.entityToDto(parcelService.transferParcel(trackingId,parcelEntity));
        } catch (BLException e) {
            Error error = new Error();
            error.setErrorMessage(e.getMessage());
            if (e.getMessage().equals("A parcel with the specified trackingID is already in the system.")){
                return new ResponseEntity<>(error,HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }

        if(newParcelInfo!=null){
            return new ResponseEntity<>(newParcelInfo,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
