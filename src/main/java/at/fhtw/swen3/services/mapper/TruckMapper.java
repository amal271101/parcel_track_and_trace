package at.fhtw.swen3.services.mapper;
import at.fhtw.swen3.persistence.entities.TruckEntity;
import at.fhtw.swen3.services.dto.Truck;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = HopMapper.class)

public interface TruckMapper {
    TruckMapper INSTANCE = Mappers.getMapper(TruckMapper.class);

    @Mapping(target = "regionGeoJson", source = "regionGeoJson", qualifiedByName = "convertGeometryObjectToString")
    Truck entityToDto(TruckEntity truckEntityEntity);

    @Mapping(target = "regionGeoJson", source = "regionGeoJson", qualifiedByName = "convertStringToGeometryObject")
    TruckEntity dtoToEntity(Truck truck);

    @Named ("convertStringToGeometryObject")

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


    @Named("convertGeometryObjectToString")
    default String convertGeometryObjectToString(Geometry geometry){
        return geometry.toString();
    }
}