package at.fhtw.swen3.services.mapper;

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
    date = "2022-10-20T00:21:23+0200",
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
        List<HopArrival> list = trackingInformationEntity.getVisitedHops();
        if ( list != null ) {
            trackingInformation.setVisitedHops( new ArrayList<HopArrival>( list ) );
        }
        List<HopArrival> list1 = trackingInformationEntity.getFutureHops();
        if ( list1 != null ) {
            trackingInformation.setFutureHops( new ArrayList<HopArrival>( list1 ) );
        }

        return trackingInformation;
    }

    @Override
    public TrackingInformationEntity dtoToEntity(TrackingInformation trackingInformation) {
        if ( trackingInformation == null ) {
            return null;
        }

        StateEnumEntity state = null;
        List<HopArrival> futureHops = null;
        List<HopArrival> visitedHops = null;

        state = stateEnumToStateEnumEntity( trackingInformation.getState() );
        List<HopArrival> list = trackingInformation.getFutureHops();
        if ( list != null ) {
            futureHops = new ArrayList<HopArrival>( list );
        }
        List<HopArrival> list1 = trackingInformation.getVisitedHops();
        if ( list1 != null ) {
            visitedHops = new ArrayList<HopArrival>( list1 );
        }

        TrackingInformationEntity trackingInformationEntity = new TrackingInformationEntity( state, futureHops, visitedHops );

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
}
