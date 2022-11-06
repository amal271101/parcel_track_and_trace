package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.TrackingInformationEntity;
import at.fhtw.swen3.persistence.entity.TrackingInformationEntity.StateEnumEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.TrackingInformation.StateEnum;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-07T00:04:02+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class TrackingInformationMapperImpl implements TrackingInformationMapper {

    @Override
    public TrackingInformation entityToDto(TrackingInformationEntity trackingInformationEntity) {
        if ( trackingInformationEntity == null ) {
            return null;
        }

        TrackingInformation trackingInformation = new TrackingInformation();

        trackingInformation.setState( stateEnumEntityToStateEnum( trackingInformationEntity.getState() ) );
        trackingInformation.setVisitedHops( hopArrivalEntityListToHopArrivalList( trackingInformationEntity.getVisitedHops() ) );
        trackingInformation.setFutureHops( hopArrivalEntityListToHopArrivalList( trackingInformationEntity.getFutureHops() ) );

        return trackingInformation;
    }

    @Override
    public TrackingInformationEntity dtoToEntity(TrackingInformation trackingInformation) {
        if ( trackingInformation == null ) {
            return null;
        }

        TrackingInformationEntity trackingInformationEntity = new TrackingInformationEntity();

        trackingInformationEntity.setState( stateEnumToStateEnumEntity( trackingInformation.getState() ) );
        trackingInformationEntity.setFutureHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getFutureHops() ) );
        trackingInformationEntity.setVisitedHops( hopArrivalListToHopArrivalEntityList( trackingInformation.getVisitedHops() ) );

        return trackingInformationEntity;
    }

    protected StateEnum stateEnumEntityToStateEnum(StateEnumEntity stateEnumEntity) {
        if ( stateEnumEntity == null ) {
            return null;
        }

        StateEnum stateEnum;

        switch ( stateEnumEntity ) {
            case PICKUP: stateEnum = StateEnum.PICKUP;
            break;
            case INTRANSPORT: stateEnum = StateEnum.INTRANSPORT;
            break;
            case INTRUCKDELIVERY: stateEnum = StateEnum.INTRUCKDELIVERY;
            break;
            case TRANSFERRED: stateEnum = StateEnum.TRANSFERRED;
            break;
            case DELIVERED: stateEnum = StateEnum.DELIVERED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + stateEnumEntity );
        }

        return stateEnum;
    }

    protected HopArrival hopArrivalEntityToHopArrival(HopArrivalEntity hopArrivalEntity) {
        if ( hopArrivalEntity == null ) {
            return null;
        }

        HopArrival hopArrival = new HopArrival();

        hopArrival.setCode( hopArrivalEntity.getCode() );
        hopArrival.setDescription( hopArrivalEntity.getDescription() );
        hopArrival.setDateTime( hopArrivalEntity.getDateTime() );

        return hopArrival;
    }

    protected List<HopArrival> hopArrivalEntityListToHopArrivalList(List<HopArrivalEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<HopArrival> list1 = new ArrayList<HopArrival>( list.size() );
        for ( HopArrivalEntity hopArrivalEntity : list ) {
            list1.add( hopArrivalEntityToHopArrival( hopArrivalEntity ) );
        }

        return list1;
    }

    protected StateEnumEntity stateEnumToStateEnumEntity(StateEnum stateEnum) {
        if ( stateEnum == null ) {
            return null;
        }

        StateEnumEntity stateEnumEntity;

        switch ( stateEnum ) {
            case PICKUP: stateEnumEntity = StateEnumEntity.PICKUP;
            break;
            case INTRANSPORT: stateEnumEntity = StateEnumEntity.INTRANSPORT;
            break;
            case INTRUCKDELIVERY: stateEnumEntity = StateEnumEntity.INTRUCKDELIVERY;
            break;
            case TRANSFERRED: stateEnumEntity = StateEnumEntity.TRANSFERRED;
            break;
            case DELIVERED: stateEnumEntity = StateEnumEntity.DELIVERED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + stateEnum );
        }

        return stateEnumEntity;
    }

    protected HopArrivalEntity hopArrivalToHopArrivalEntity(HopArrival hopArrival) {
        if ( hopArrival == null ) {
            return null;
        }

        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();

        hopArrivalEntity.setCode( hopArrival.getCode() );
        hopArrivalEntity.setDescription( hopArrival.getDescription() );
        hopArrivalEntity.setDateTime( hopArrival.getDateTime() );

        return hopArrivalEntity;
    }

    protected List<HopArrivalEntity> hopArrivalListToHopArrivalEntityList(List<HopArrival> list) {
        if ( list == null ) {
            return null;
        }

        List<HopArrivalEntity> list1 = new ArrayList<HopArrivalEntity>( list.size() );
        for ( HopArrival hopArrival : list ) {
            list1.add( hopArrivalToHopArrivalEntity( hopArrival ) );
        }

        return list1;
    }
}
