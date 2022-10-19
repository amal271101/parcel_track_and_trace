package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entity.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T00:21:23+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class TransferwarehouseMapperImpl implements TransferwarehouseMapper {

    @Override
    public Transferwarehouse entityToDto(TransferwarehouseEntity transferwarehouseEntity) {
        if ( transferwarehouseEntity == null ) {
            return null;
        }

        Transferwarehouse transferwarehouse = new Transferwarehouse();

        transferwarehouse.hopType( transferwarehouseEntity.getHopType() );
        transferwarehouse.code( transferwarehouseEntity.getCode() );
        transferwarehouse.description( transferwarehouseEntity.getDescription() );
        transferwarehouse.processingDelayMins( transferwarehouseEntity.getProcessingDelayMins() );
        transferwarehouse.locationName( transferwarehouseEntity.getLocationName() );
        transferwarehouse.locationCoordinates( geoCoordinateEntityToGeoCoordinate( transferwarehouseEntity.getLocationCoordinates() ) );
        transferwarehouse.setRegionGeoJson( transferwarehouseEntity.getRegionGeoJson() );
        transferwarehouse.setLogisticsPartner( transferwarehouseEntity.getLogisticsPartner() );
        transferwarehouse.setLogisticsPartnerUrl( transferwarehouseEntity.getLogisticsPartnerUrl() );

        return transferwarehouse;
    }

    @Override
    public TransferwarehouseEntity dtoToEntity(Transferwarehouse transferwarehouse) {
        if ( transferwarehouse == null ) {
            return null;
        }

        String regionGeoJson = null;
        String logisticsPartner = null;
        String logisticsPartnerUrl = null;
        String hopType = null;
        String code = null;
        String description = null;
        Integer processingDelayMins = null;
        String locationName = null;
        GeoCoordinateEntity locationCoordinates = null;

        regionGeoJson = transferwarehouse.getRegionGeoJson();
        logisticsPartner = transferwarehouse.getLogisticsPartner();
        logisticsPartnerUrl = transferwarehouse.getLogisticsPartnerUrl();
        hopType = transferwarehouse.getHopType();
        code = transferwarehouse.getCode();
        description = transferwarehouse.getDescription();
        processingDelayMins = transferwarehouse.getProcessingDelayMins();
        locationName = transferwarehouse.getLocationName();
        locationCoordinates = geoCoordinateToGeoCoordinateEntity( transferwarehouse.getLocationCoordinates() );

        TransferwarehouseEntity transferwarehouseEntity = new TransferwarehouseEntity( regionGeoJson, logisticsPartner, logisticsPartnerUrl, hopType, code, description, processingDelayMins, locationName, locationCoordinates );

        return transferwarehouseEntity;
    }

    protected GeoCoordinate geoCoordinateEntityToGeoCoordinate(GeoCoordinateEntity geoCoordinateEntity) {
        if ( geoCoordinateEntity == null ) {
            return null;
        }

        GeoCoordinate geoCoordinate = new GeoCoordinate();

        geoCoordinate.setLat( geoCoordinateEntity.getLat() );
        geoCoordinate.setLon( geoCoordinateEntity.getLon() );

        return geoCoordinate;
    }

    protected GeoCoordinateEntity geoCoordinateToGeoCoordinateEntity(GeoCoordinate geoCoordinate) {
        if ( geoCoordinate == null ) {
            return null;
        }

        Double lat = null;
        Double lon = null;

        lat = geoCoordinate.getLat();
        lon = geoCoordinate.getLon();

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity( lat, lon );

        return geoCoordinateEntity;
    }
}
