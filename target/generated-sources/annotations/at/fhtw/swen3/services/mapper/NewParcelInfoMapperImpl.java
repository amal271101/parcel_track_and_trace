package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.NewParcelInfoEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-28T08:17:58+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
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

        NewParcelInfoEntity newParcelInfoEntity = new NewParcelInfoEntity();

        newParcelInfoEntity.setTrackingId( newParcelInfo.getTrackingId() );

        return newParcelInfoEntity;
    }
}
