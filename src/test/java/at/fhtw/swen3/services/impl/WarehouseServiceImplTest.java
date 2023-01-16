package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.BLDataNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class WarehouseServiceImplTest {
    @Autowired
    WarehouseServiceImpl warehouseService;
    @Test
    public void getWarehouseByCodeThrowsBLException() {
        Throwable exception = assertThrows(BLDataNotFoundException.class, () -> warehouseService.getWarehousebyCode("sfddf"));
        assertEquals("No hop with the specified id could be found." , exception.getMessage());
    }


}