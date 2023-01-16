import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.controller.rest.Integration;
import at.fhtw.swen3.persistence.repositories.TransferwarehouseRepository;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.json.JSONObject;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(classes = OpenApiGeneratorApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Category(Integration.class)

public class IntegrationTests {

        @Autowired
        private MockMvc mvc;

        @Autowired
        WarehouseRepository warehouseRepository;
        @Autowired
        WarehouseNextHopsRepository warehouseNextHopsRepository;
        @Autowired
        TruckRepository truckRepository;
        @Autowired
        TransferwarehouseRepository transferwarehouseRepository;
        String workingDirectory = System.getProperty("user.dir");

        private static String trackingId;
         public  void setUp() {

                warehouseRepository.deleteAllWarehouseEntityNextHops();
                warehouseNextHopsRepository.deleteAllWarehouseNextHops();
                warehouseRepository.deleteAllWarehouses();
                truckRepository.deleteAll();
                transferwarehouseRepository.deleteAll();
        }

        @Order(1)
        @Test
        void importWarehousesFail() throws Exception {
                setUp();
                String warehouseHierarchy = new String(Files.readAllBytes(Paths.get( workingDirectory + "/JsonData/WarehouseHierarchyFail.txt")));

                mvc.perform(post("/warehouse")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(warehouseHierarchy))
                        .andExpect(status().isBadRequest());

        }

        @Order(2)
        @Test
        void exportWarehousesFail() throws Exception {
                mvc.perform(get("/warehouse"))
                        .andExpect(status().isNotFound());
        }

        @Order(3)
        @Test
        void getWarehouseByCodeFail() throws Exception {
                mvc.perform(get("/warehouse/WENB01"))
                        .andExpect(status().isNotFound());
        }

        @Order(4)
        @Test
        void importWarehouses() throws Exception {
                String warehouseHierarchy = new String(Files.readAllBytes(Paths.get(workingDirectory +"/JsonData/WarehouseHierachy.txt")));


                mvc.perform(post("/warehouse")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(warehouseHierarchy))
                        .andExpect(status().isOk());

        }

        @Order(5)

        @Test
        void exportWarehouses() throws Exception {
                mvc.perform(get("/warehouse"))
                .andExpect(status().isOk());
        }


        @Order(6)
        @Test
        void getWarehouseByCode() throws Exception {
                mvc.perform(get("/warehouse/WENB01"))
                        .andExpect(status().isOk());
        }

        @Order(7)
        @Test
        void submitParcelFail() throws Exception {
                String parcel = new String(Files.readAllBytes(Paths.get(workingDirectory +"/JsonData/ParcelFail.txt")));


                mvc.perform(post("/parcel")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(parcel))
                        .andExpect(status().isBadRequest());

        }
        @Order(8)
        @Test
        void submitParcel() throws Exception {
                String parcel = new String(Files.readAllBytes(Paths.get(workingDirectory +"/JsonData/Parcel.txt")));


                trackingId =  mvc.perform(post("/parcel")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(parcel))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString();



                JSONObject json = new JSONObject(trackingId);
                trackingId = json.getString("trackingId");

                System.out.println(trackingId);

        }

        @Order(9)
        @Test
        void trackParcelFail() throws Exception {
                RandomStringGenerator generator = new RandomStringGenerator.Builder()
                        .withinRange('0', 'Z')
                        .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS)
                        .build();

                mvc.perform(get("/parcel/"+generator.generate(9)))
                        .andExpect(status().isNotFound());

        }
        @Order(10)
        @Test
        void trackParcel() throws Exception {
                mvc.perform(get("/parcel/"+trackingId))
                        .andExpect(status().isOk());

        }


        @Order(11)
        @Test
        void reportParcelDelivery() throws Exception {

                mvc.perform(post("/parcel/"+trackingId+"/reportDelivery/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content((byte[]) null))
                        .andExpect(status().isOk());
        }


        @Order(12)
        @Test
        void reportParcelDeliveryFail() throws Exception {

                RandomStringGenerator generator = new RandomStringGenerator.Builder()
                        .withinRange('0', 'Z')
                        .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS)
                        .build();
                mvc.perform(post("/parcel/"+generator.generate(9)+"/reportDelivery/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content((byte[]) null))
                        .andExpect(status().isNotFound());
        }


        @Order(13)
        @Test
        void transferParcel() throws Exception {
                RandomStringGenerator generator = new RandomStringGenerator.Builder()
                        .withinRange('0', 'Z')
                        .filteredBy(CharacterPredicates.DIGITS, CharacterPredicates.LETTERS)
                        .build();

                String parcel = new String(Files.readAllBytes(Paths.get(workingDirectory +"/JsonData/Parcel.txt")));

                mvc.perform(post("/parcel/"+generator.generate(9))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(parcel))
                        .andExpect(status().isOk());
        }


        @Order(14)
        @Test
        void transferParcelFail() throws Exception {

                String parcel = new String(Files.readAllBytes(Paths.get(workingDirectory +"/JsonData/Parcel.txt")));
                System.out.println(trackingId);

                mvc.perform(post("/parcel/"+trackingId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(parcel))
                        .andExpect(status().isConflict());
        }

        @Order(15)
        @Test
        void reportParcelHop() throws Exception {

                mvc.perform(post("/parcel/"+trackingId+"/reportHop/WENB01/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content((byte[]) null))
                        .andExpect(status().isOk());
        }

        @Order(16)
        @Test
        void reportParcelHopFail() throws Exception {

                mvc.perform(post("/parcel/"+trackingId+"/reportHop/UENB01/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content((byte[]) null))
                        .andExpect(status().isNotFound());
        }
    }


