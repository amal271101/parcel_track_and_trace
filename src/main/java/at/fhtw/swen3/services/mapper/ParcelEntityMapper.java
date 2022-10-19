package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
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
    ParcelEntity dtosToEntity(Parcel parcelDto, TrackingInformation trackingInformationDto, NewParcelInfo newParcelInfoDto);

    @Mapping(source = "trackingInformationDto.state", target = "state")
    @Mapping(source = "trackingInformationDto.futureHops", target = "futureHops")
    @Mapping(source = "trackingInformationDto.visitedHops", target = "visitedHops")
    ParcelEntity TrackingInformationToParcelEntity(TrackingInformation trackingInformationDto);


    @Mapping(source = "parcelDto.weight", target = "weight")
    @Mapping(source = "parcelDto.recipient", target = "recipient")
    @Mapping(source = "parcelDto.sender", target = "sender")
    ParcelEntity ParcelDtoToEntity(Parcel parcelDto);


    @Mapping(source = "newParcelInfoDto.trackingId", target = "trackingId")
    ParcelEntity NewParcelInfoDtoToEntity( NewParcelInfo newParcelInfoDto);


}