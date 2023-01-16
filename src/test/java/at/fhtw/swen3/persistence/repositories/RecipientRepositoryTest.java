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
        recipientEntity.setStreet("Landstraße 28a");
        recipientEntity.setCity("Vienna");
        recipientEntity.setPostalCode("A-1210");
        recipientEntity.setName("Aloha Ahola");
        recipientEntity.setCountry("Austria");

        recipientRepository.save(recipientEntity);

        assertEquals(recipientRepository.findByName(recipientEntity.getName()).getName(), recipientEntity.getName());

        recipientRepository.delete(recipientRepository.findByName(recipientEntity.getName()));

        assertNull(recipientRepository.findByCity(recipientEntity.getName()));

    }
}