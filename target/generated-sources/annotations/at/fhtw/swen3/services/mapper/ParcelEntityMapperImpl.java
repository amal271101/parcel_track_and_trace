package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.persistence.entity.TrackingInformationEntity.StateEnumEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.TrackingInformation.StateEnum;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T17:27:20+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class ParcelEntityMapperImpl implements ParcelEntityMapper {

    @Override
    public ParcelEntity dtosToEntity(Parcel parcelDto, TrackingInformation trackingInformationDto, NewParcelInfo newParcelInfoDto) {
        if ( parcelDto == null && trackingInformationDto == null && newParcelInfoDto == null ) {
            return null;
        }

        Float weight = null;
        RecipientEntity recipient = null;
        RecipientEntity sender = null;
        if ( parcelDto != null ) {
            weight = parcelDto.getWeight();
            recipient = recipientToRecipientEntity( parcelDto.getRecipient() );
            sender = recipientToRecipientEntity( parcelDto.getSender() );
        }
        StateEnumEntity state = null;
        List<HopArrivalEntity> futureHops = null;
        List<HopArrivalEntity> visitedHops = null;
        if ( trackingInformationDto != null ) {
            state = stateEnumToStateEnumEntity( trackingInformationDto.getState() );
            futureHops = hopArrivalListToHopArrivalEntityList( trackingInformationDto.getFutureHops() );
            visitedHops = hopArrivalListToHopArrivalEntityList( trackingInformationDto.getVisitedHops() );
        }
        String trackingId = null;
        if ( newParcelInfoDto != null ) {
            trackingId = newParcelInfoDto.getTrackingId();
        }

        ParcelEntity parcelEntity = new ParcelEntity( weight, trackingId, sender, recipient, state, futureHops, visitedHops );

        return parcelEntity;
    }

    @Override
    public ParcelEntity TrackingInformationToParcelEntity(TrackingInformation trackingInformationDto) {
        if ( trackingInformationDto == null ) {
            return null;
        }

        StateEnumEntity state = null;
        List<HopArrivalEntity> futureHops = null;
        List<HopArrivalEntity> visitedHops = null;

        state = stateEnumToStateEnumEntity( trackingInformationDto.getState() );
        futureHops = hopArrivalListToHopArrivalEntityList( trackingInformationDto.getFutureHops() );
        visitedHops = hopArrivalListToHopArrivalEntityList( trackingInformationDto.getVisitedHops() );

        Float weight = null;
        String trackingId = null;
        RecipientEntity sender = null;
        RecipientEntity recipient = null;

        ParcelEntity parcelEntity = new ParcelEntity( weight, trackingId, sender, recipient, state, futureHops, visitedHops );

        return parcelEntity;
    }

    @Override
    public ParcelEntity ParcelDtoToEntity(Parcel parcelDto) {
        if ( parcelDto == null ) {
            return null;
        }

        Float weight = null;
        RecipientEntity recipient = null;
        RecipientEntity sender = null;

        weight = parcelDto.getWeight();
        recipient = recipientToRecipientEntity( parcelDto.getRecipient() );
        sender = recipientToRecipientEntity( parcelDto.getSender() );

        String trackingId = null;
        StateEnumEntity state = null;
        List<HopArrivalEntity> futureHops = null;
        List<HopArrivalEntity> visitedHops = null;

        ParcelEntity parcelEntity = new ParcelEntity( weight, trackingId, sender, recipient, state, futureHops, visitedHops );

        return parcelEntity;
    }

    @Override
    public ParcelEntity NewParcelInfoDtoToEntity(NewParcelInfo newParcelInfoDto) {
        if ( newParcelInfoDto == null ) {
            return null;
        }

        String trackingId = null;

        trackingId = newParcelInfoDto.getTrackingId();

        Float weight = null;
        RecipientEntity sender = null;
        RecipientEntity recipient = null;
        StateEnumEntity state = null;
        List<HopArrivalEntity> futureHops = null;
        List<HopArrivalEntity> visitedHops = null;

        ParcelEntity parcelEntity = new ParcelEntity( weight, trackingId, sender, recipient, state, futureHops, visitedHops );

        return parcelEntity;
    }

    protected RecipientEntity recipientToRecipientEntity(Recipient recipient) {
        if ( recipient == null ) {
            return null;
        }

        String name = null;
        String street = null;
        String postalCode = null;
        String city = null;
        String country = null;

        name = recipient.getName();
        street = recipient.getStreet();
        postalCode = recipient.getPostalCode();
        city = recipient.getCity();
        country = recipient.getCountry();

        RecipientEntity recipientEntity = new RecipientEntity( name, street, postalCode, city, country );

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

        String code = null;
        String description = null;
        OffsetDateTime dateTime = null;

        code = hopArrival.getCode();
        description = hopArrival.getDescription();
        dateTime = hopArrival.getDateTime();

        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity( code, description, dateTime );

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
