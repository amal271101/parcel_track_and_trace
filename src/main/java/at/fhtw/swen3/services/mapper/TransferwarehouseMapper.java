package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
@Mapper(uses = HopMapper.class)
public interface TransferwarehouseMapper {
    TransferwarehouseMapper INSTANCE = Mappers.getMapper(TransferwarehouseMapper.class);
    @Mapping(target = "regionGeoJson", source = "regionGeoJson", qualifiedByName = "convertGeometryToString")
    Transferwarehouse entityToDto(TransferwarehouseEntity transferwarehouseEntity);

    @Mapping(target = "regionGeoJson", source = "regionGeoJson", qualifiedByName = "convertStringToGeometry")
    TransferwarehouseEntity dtoToEntity(Transferwarehouse transferwarehouse);
    @Named("convertStringToGeometry")
    default Geometry convertStringToGeometryObject(String geoJson) throws ParseException {
        JsonObject jsonObject = new JsonParser().parse(geoJson).getAsJsonObject();
        JsonObject geometryObject = jsonObject.getAsJsonObject("geometry");
        if (geometryObject != null && !geometryObject.isJsonNull()) {
            String geo = geometryObject.toString();
            return new GeoJsonReader().read(geo);
        } else {
            return null;
        }
    }


    @Named("convertGeometryToString")
    default String convertGeometryObjectToString(Geometry geometry){
        return geometry.toString();
    }

}
