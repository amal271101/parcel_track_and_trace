package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.NewParcelInfoEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-20T17:27:19+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
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
