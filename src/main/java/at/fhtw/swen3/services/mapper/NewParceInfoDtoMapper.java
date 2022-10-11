package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.Parcel;
import at.fhtw.swen3.services.dto.NewParcelInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewParceInfoDtoMapper {
     NewParceInfoDtoMapper INSTANCE = Mappers.getMapper(NewParceInfoDtoMapper.class);

    @Mapping(source = "parcel.trackingId", target = "trackingId")
    NewParcelInfoDto entityToDto(Parcel parcel);
}
