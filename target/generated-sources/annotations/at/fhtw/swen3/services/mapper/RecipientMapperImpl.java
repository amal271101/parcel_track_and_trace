package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T17:27:20+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class RecipientMapperImpl implements RecipientMapper {

    @Override
    public Recipient entityToDto(RecipientEntity recipientEntity) {
        if ( recipientEntity == null ) {
            return null;
        }

        Recipient recipient = new Recipient();

        recipient.setName( recipientEntity.getName() );
        recipient.setStreet( recipientEntity.getStreet() );
        recipient.setPostalCode( recipientEntity.getPostalCode() );
        recipient.setCity( recipientEntity.getCity() );
        recipient.setCountry( recipientEntity.getCountry() );

        return recipient;
    }

    @Override
    public RecipientEntity dtoToEntity(Recipient recipient) {
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
}
