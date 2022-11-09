package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-08T12:26:57+0100",
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

        TransferwarehouseEntity transferwarehouseEntity = new TransferwarehouseEntity();

        transferwarehouseEntity.setRegionGeoJson( transferwarehouse.getRegionGeoJson() );
        transferwarehouseEntity.setLogisticsPartner( transferwarehouse.getLogisticsPartner() );
        transferwarehouseEntity.setLogisticsPartnerUrl( transferwarehouse.getLogisticsPartnerUrl() );
        transferwarehouseEntity.setHopType( transferwarehouse.getHopType() );
        transferwarehouseEntity.setCode( transferwarehouse.getCode() );
        transferwarehouseEntity.setDescription( transferwarehouse.getDescription() );
        transferwarehouseEntity.setProcessingDelayMins( transferwarehouse.getProcessingDelayMins() );
        transferwarehouseEntity.setLocationName( transferwarehouse.getLocationName() );
        transferwarehouseEntity.setLocationCoordinates( geoCoordinateToGeoCoordinateEntity( transferwarehouse.getLocationCoordinates() ) );

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

        GeoCoordinateEntity geoCoordinateEntity = new GeoCoordinateEntity();

        geoCoordinateEntity.setLat( geoCoordinate.getLat() );
        geoCoordinateEntity.setLon( geoCoordinate.getLon() );

        return geoCoordinateEntity;
    }
}
