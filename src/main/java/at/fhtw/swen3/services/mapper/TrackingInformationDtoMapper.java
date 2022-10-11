package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrackingInformationDtoMapper {
    TrackingInformationDtoMapper INSTANCE = Mappers.getMapper(TrackingInformationDtoMapper.class);

    @Mapping(source = "parcel.state", target = "state")
    @Mapping(source = "parcel.visitedHops", target = "visitedHops")
    @Mapping(source = "parcel.futureHops", target = "futureHops")
    TrackingInformationDto entityToDto(Parcel parcel);

}
