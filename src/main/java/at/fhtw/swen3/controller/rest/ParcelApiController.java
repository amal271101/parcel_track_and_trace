package at.fhtw.swen3.controller.rest;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.controller.ParcelApi;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.*;
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
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {

        parcelService.reportParcelDelivery(trackingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        if(!parcelService.reportParcelHop(trackingId,code)){ return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.ParcelDtoToEntity(parcel);

        NewParcelInfo newParcelInfo = NewParcelInfoMapper.INSTANCE.entityToDto(parcelService.submitParcel(parcelEntity));

        if(newParcelInfo!=null){
            return new ResponseEntity<>(newParcelInfo,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<TrackingInformation> trackParcel(String trackingId
    ) {
        if(parcelService.getParcelTrackingInformation(trackingId)==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(TrackingInformationMapper.INSTANCE.entityToDto(parcelService.getParcelTrackingInformation(trackingId)), HttpStatus.OK);

    }


    public ResponseEntity<NewParcelInfo> transitionParcel(String trackingId, Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.ParcelDtoToEntity(parcel);

        NewParcelInfo newParcelInfo = NewParcelInfoMapper.INSTANCE.entityToDto(parcelService.transferParcel(trackingId,parcelEntity));

        if(newParcelInfo!=null){
            return new ResponseEntity<>(newParcelInfo,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
