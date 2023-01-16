package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.TrackingInformationEntity.StateEnumEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.TrackingInformation.StateEnum;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-16T01:44:57+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
public class ParcelMapperImpl implements ParcelMapper {

    @Override
    public ParcelEntity dtosToEntity(Parcel parcelDto, TrackingInformation trackingInformationDto, NewParcelInfo newParcelInfoDto) {
        if ( parcelDto == null && trackingInformationDto == null && newParcelInfoDto == null ) {
            return null;
        }

        ParcelEntity parcelEntity = new ParcelEntity();

        if ( parcelDto != null ) {
            parcelEntity.setWeight( parcelDto.getWeight() );
            parcelEntity.setRecipient( recipientToRecipientEntity( parcelDto.getRecipient() ) );
            parcelEntity.setSender( recipientToRecipientEntity( parcelDto.getSender() ) );
        }
        if ( trackingInformationDto != null ) {
            parcelEntity.setState( stateEnumToStateEnumEntity( trackingInformationDto.getState() ) );
            parcelEntity.setFutureHops( hopArrivalListToHopArrivalEntityList( trackingInformationDto.getFutureHops() ) );
            parcelEntity.setVisitedHops( hopArrivalListToHopArrivalEntityList( trackingInformationDto.getVisitedHops() ) );
        }
        if ( newParcelInfoDto != null ) {
            parcelEntity.setTrackingId( newParcelInfoDto.getTrackingId() );
        }

        return parcelEntity;
    }

    @Override
    public ParcelEntity TrackingInformationToParcelEntity(TrackingInformation trackingInformationDto) {
        if ( trackingInformationDto == null ) {
            return null;
        }

        ParcelEntity parcelEntity = new ParcelEntity();

        parcelEntity.setState( stateEnumToStateEnumEntity( trackingInformationDto.getState() ) );
        parcelEntity.setFutureHops( hopArrivalListToHopArrivalEntityList( trackingInformationDto.getFutureHops() ) );
        parcelEntity.setVisitedHops( hopArrivalListToHopArrivalEntityList( trackingInformationDto.getVisitedHops() ) );

        return parcelEntity;
    }

    @Override
    public ParcelEntity ParcelDtoToEntity(Parcel parcelDto) {
        if ( parcelDto == null ) {
            return null;
        }

        ParcelEntity parcelEntity = new ParcelEntity();

        parcelEntity.setWeight( parcelDto.getWeight() );
        parcelEntity.setRecipient( recipientToRecipientEntity( parcelDto.getRecipient() ) );
        parcelEntity.setSender( recipientToRecipientEntity( parcelDto.getSender() ) );

        return parcelEntity;
    }

    @Override
    public ParcelEntity NewParcelInfoDtoToEntity(NewParcelInfo newParcelInfoDto) {
        if ( newParcelInfoDto == null ) {
            return null;
        }

        ParcelEntity parcelEntity = new ParcelEntity();

        parcelEntity.setTrackingId( newParcelInfoDto.getTrackingId() );

        return parcelEntity;
    }

    @Override
    public ParcelEntity ParcelNewParcelinfoDtoToEntity(Parcel parcelDto, NewParcelInfo newParcelInfoDto) {
        if ( parcelDto == null && newParcelInfoDto == null ) {
            return null;
        }

        ParcelEntity parcelEntity = new ParcelEntity();

        if ( parcelDto != null ) {
            parcelEntity.setWeight( parcelDto.getWeight() );
            parcelEntity.setRecipient( recipientToRecipientEntity( parcelDto.getRecipient() ) );
            parcelEntity.setSender( recipientToRecipientEntity( parcelDto.getSender() ) );
        }
        if ( newParcelInfoDto != null ) {
            parcelEntity.setTrackingId( newParcelInfoDto.getTrackingId() );
        }

        return parcelEntity;
    }

    protected RecipientEntity recipientToRecipientEntity(Recipient recipient) {
        if ( recipient == null ) {
            return null;
        }

        RecipientEntity recipientEntity = new RecipientEntity();

        recipientEntity.setName( recipient.getName() );
        recipientEntity.setStreet( recipient.getStreet() );
        recipientEntity.setPostalCode( recipient.getPostalCode() );
        recipientEntity.setCity( recipient.getCity() );
        recipientEntity.setCountry( recipient.getCountry() );

        return recipientEntity;
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
