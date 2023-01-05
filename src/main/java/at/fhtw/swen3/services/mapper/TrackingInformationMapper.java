package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TrackingInformationEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface TrackingInformationMapper {
    TrackingInformationMapper INSTANCE = Mappers.getMapper(TrackingInformationMapper.class);

    TrackingInformation entityToDto(TrackingInformationEntity trackingInformationEntity);

    TrackingInformationEntity dtoToEntity(TrackingInformation trackingInformation);
}