package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecipientRepositoryTest {
    @Autowired
    private RecipientRepository recipientRepository;

    @Test
    void saveAndDeleteRecipientEntity() {

        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setStreet("Landstraße 27a");
        recipientEntity.setCity("Vienna");
        recipientEntity.setPostalCode("A-1210");
        recipientEntity.setName("Amal");
        recipientEntity.setCountry("Austria");

        recipientRepository.save(recipientEntity);

        assertEquals(recipientRepository.findByCity(recipientEntity.getCity()).getCity(), recipientEntity.getCity());

        recipientRepository.delete(recipientEntity);

        assertNull(recipientRepository.findByCity(recipientEntity.getCity()));

    }
}