package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.Parcel;
import at.fhtw.swen3.services.dto.ParcelDto;
import at.fhtw.swen3.services.dto.TrackingInformationDto;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-10T23:39:10+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class ParcelMapperImpl implements ParcelMapper {

    @Override
    public Parcel from(ParcelDto parcelDto, TrackingInformationDto trackingInformationDto) {
        if ( parcelDto == null && trackingInformationDto == null ) {
            return null;
        }

        Parcel parcel = new Parcel();

        if ( parcelDto != null ) {
            parcel.setWeight( parcelDto.getWeight() );
            parcel.setRecipient( parcelDto.getRecipient() );
            parcel.setSender( parcelDto.getSender() );
        }

        return parcel;
    }
}
