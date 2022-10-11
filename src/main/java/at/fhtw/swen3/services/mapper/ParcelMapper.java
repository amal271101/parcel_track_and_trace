package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.Parcel;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParcelMapper extends Parcel <ParcelDto,TrackingInformationDto> {
@Mapping(source = "parcelDto.weight", target = "weight")
@Mapping(source = "parcelDto.recipient", target = "recipient")
@Mapping(source = "parcelDto.sender", target = "sender")
@Mapping(source = "trackingInformationDto.state", target = "state")

 Parcel from( ParcelDto parcelDto, TrackingInformationDto trackingInformationDto);

}
