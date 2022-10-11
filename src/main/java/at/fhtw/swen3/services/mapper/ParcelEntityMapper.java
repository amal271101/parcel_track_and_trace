package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.Parcel;
import at.fhtw.swen3.services.dto.NewParcelInfoDto;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParcelEntityMapper {
    ParcelEntityMapper INSTANCE = Mappers.getMapper(ParcelEntityMapper.class);

    @Mapping(source = "parcelDto.weight", target = "weight")
    @Mapping(source = "parcelDto.recipient", target = "recipient")
    @Mapping(source = "parcelDto.sender", target = "sender")
    @Mapping(source = "trackingInformationDto.state", target = "state")
    @Mapping(source = "trackingInformationDto.futureHops", target = "futureHops")
    @Mapping(source = "trackingInformationDto.visitedHops", target = "visitedHops")
    @Mapping(source = "newParcelInfoDto.trackingId", target = "trackingId")

    Parcel dtosToEntity(ParcelDto parcelDto, TrackingInformationDto trackingInformationDto, NewParcelInfoDto newParcelInfoDto);
}