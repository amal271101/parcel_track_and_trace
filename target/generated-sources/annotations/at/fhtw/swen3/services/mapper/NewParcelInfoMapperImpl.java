package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.NewParcelInfoEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-22T16:37:25+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
public class NewParcelInfoMapperImpl implements NewParcelInfoMapper {

    @Override
    public NewParcelInfo entityToDto(NewParcelInfoEntity newParcelInfoEntity) {
        if ( newParcelInfoEntity == null ) {
            return null;
        }

        NewParcelInfo newParcelInfo = new NewParcelInfo();

        newParcelInfo.setTrackingId( newParcelInfoEntity.getTrackingId() );

        return newParcelInfo;
    }

    @Override
    public NewParcelInfoEntity dtoToEntity(NewParcelInfo newParcelInfo) {
        if ( newParcelInfo == null ) {
            return null;
        }

        String trackingId = null;

        trackingId = newParcelInfo.getTrackingId();

        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity( trackingId );

        return newParcelInfoEntity;
    }
}
