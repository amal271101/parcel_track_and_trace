package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.mapper.TruckMapper;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;


import static org.junit.jupiter.api.Assertions.*;

class TruckMapperTest {
    @Test
    void EntitytoDto() {
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setDescription("description");
        truckEntity.setCode("code");
        truckEntity.setHopType("type");
        truckEntity.setLocationName("location");
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = new Coordinate[] {
                new Coordinate(-73.95, 40.78),
                new Coordinate(-73.95, 40.70),
                new Coordinate(-73.85, 40.70),
                new Coordinate(-73.85, 40.78),
                new Coordinate(-73.95, 40.78)
        };
        truckEntity.setRegionGeoJson(geometryFactory.createPolygon(geometryFactory.createLinearRing(coordinates)));

       Truck truck = TruckMapper.INSTANCE.entityToDto(truckEntity);

        assertEquals(truck.getCode(), truckEntity.getCode());
        assertEquals(truck.getDescription(), truckEntity.getDescription());
        assertEquals(truck.getHopType(), truckEntity.getHopType());
        assertEquals(truck.getLocationName(), truckEntity.getLocationName());

    }


    @Test
    void DtotoEntity() {
        Truck truck= new Truck();
        truck.setCode("code");
        truck.setDescription("helloooo");
        truck.setHopType("type");
        String geoJsonString="{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[16.3246041,48.1377922],[16.3210113,48.1379656],[16.3115412,48.1366437],[16.3033821,48.1368891],[16.3015078,48.1310419],[16.300673,48.1310602],[16.2997281,48.1290642],[16.2980514,48.1289528],[16.2990351,48.1268696],[16.2985547,48.1253233],[16.3081392,48.1201565],[16.312207,48.1195325],[16.3139266,48.1255899],[16.3185017,48.124562],[16.3248962,48.1359762],[16.3246041,48.1377922]]]]}}";

        truck.setRegionGeoJson(geoJsonString);
        TruckEntity truckEntity = TruckMapper.INSTANCE.dtoToEntity(truck);

        assertEquals(truck.getCode(), truckEntity.getCode());
        assertEquals(truck.getDescription(), truckEntity.getDescription());
        assertEquals(truck.getHopType(), truckEntity.getHopType());
        assertEquals(truck.getLocationName(), truckEntity.getLocationName());
    }

}