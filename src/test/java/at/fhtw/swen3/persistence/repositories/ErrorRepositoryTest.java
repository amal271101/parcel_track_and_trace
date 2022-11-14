package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ErrorRepositoryTest {
    @Autowired
    private ErrorRepository errorRepository;

    @Test
    void saveAndDeleteErrorEntity() {

        ErrorEntity errorEntity= new ErrorEntity();
        errorEntity.setErrorMessage("An error was made");
        errorRepository.save(errorEntity);

        assertEquals(errorRepository.findByErrorMessage("An error was made").getErrorMessage(),errorEntity.getErrorMessage());
        errorRepository.delete(errorRepository.findByErrorMessage("An error was made"));
        assertNull(errorRepository.findByErrorMessage("An error was made"));
    }


}