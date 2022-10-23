package Mapper;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GeoCoordinateMapper {
    @Test
    void EntitytoDto() {

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();

        geoCoordinateEntity.setLat(2.334423);
        geoCoordinateEntity.setLon(3.556);
        GeoCoordinate geoCoordinate = at.fhtw.swen3.services.mapper.GeoCoordinateMapper.INSTANCE.entityToDto(geoCoordinateEntity);

        assertEquals(geoCoordinateEntity.getLat(), geoCoordinate.getLat());
        assertEquals(geoCoordinateEntity.getLon(), geoCoordinate.getLon());

    }

    @Test
    void DtotoEntity() {
        GeoCoordinate geoCoordinate = new GeoCoordinate();
        geoCoordinate.setLat(2.45);
        geoCoordinate.setLon(4.35);

        GeoCoordinateEntity geoCoordinateEntity = at.fhtw.swen3.services.mapper.GeoCoordinateMapper.INSTANCE.dtoToEntity(geoCoordinate);

        assertEquals(geoCoordinate.getLat(), geoCoordinateEntity.getLat());
        assertEquals(geoCoordinate.getLon(), geoCoordinateEntity.getLon());

    }
}

