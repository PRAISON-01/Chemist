package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.repositories.DrugRepository;
import ng.ourChemo.data.repositories.DrugRepositoryImpl;
import ng.ourChemo.dtos.requests.AddDrugRequest;
import ng.ourChemo.dtos.requests.DeleteDrugRequest;
import ng.ourChemo.dtos.requests.DispenseDrugRequest;
import ng.ourChemo.dtos.requests.UpdateDrugRequest;
import ng.ourChemo.dtos.responses.AddDrugResponse;
import ng.ourChemo.dtos.responses.DispenseDrugResponse;
import ng.ourChemo.dtos.responses.UpdateDrugResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChemistServiceTest {


    private DrugRepository drugs;
    private AddDrugRequest addDrugRequest;
    private ChemistService chemistService;


    @BeforeEach
    public void setUp() {
        drugs = new DrugRepositoryImpl();
        drugs.deleteAll();;
        addDrugRequest = new AddDrugRequest();
        chemistService = new ChemistServiceImpl();

    }


    @Test
    public void newChemServices_addDrug_drugsCount_isOne() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JULY, 17));
        addDrugRequest.setQuantity(30);

        chemistService.addDrug(addDrugRequest);
        assertEquals(1, drugs.count());
    }


   @Test
    public void newChemistService_addDrug_priceLesserThanZero_throwsException_drugsCountIsZero() {
       addDrugRequest.setDrugName("Augmentin");
       addDrugRequest.setBrand("Augmentin");
       addDrugRequest.setPrice(new BigDecimal(0));
       addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
       addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JULY, 17));
       assertThrows(IllegalArgumentException.class, ()-> chemistService.addDrug(addDrugRequest));
       assertEquals(0, drugs.count());
   }
@Test
    public void newChemistService_addDrug_quantityIsLesserOne_throwsException_drugsCountIsZero() {
       addDrugRequest.setDrugName("Augmentin");
       addDrugRequest.setBrand("Augmentin");
       addDrugRequest.setPrice(new BigDecimal(23_000));
       addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
       addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JULY, 17));
       addDrugRequest.setQuantity(-1);
       assertThrows(IllegalArgumentException.class, ()-> chemistService.addDrug(addDrugRequest));
       assertEquals(0, drugs.count());
   }

    @Test
    public void newChemistService_addDrug_expiryDateHasPassedTodaysDate_throwException_drugCountIsZero() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2026, Month.JANUARY, 17));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.addDrug(addDrugRequest));
        assertEquals(0, drugs.count());
    }

    @Test
    public void newChemistService_addDrug_drugNameIsNull_throwException_drugCountIsZero() {
        addDrugRequest.setDrugName(null);
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.addDrug(addDrugRequest));
        assertEquals(0, drugs.count());
    }

    @Test
    public void newChemistService_addDrug_drugPriceIsNull_throwException_drugCountIsZero() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(null);
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.addDrug(addDrugRequest));
        assertEquals(0, drugs.count());
    }

    @Test
    public void newChemistService_addDrug_drugBrandIsNull_throwException_drugCountIsZero() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand(null);
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.addDrug(addDrugRequest));
        assertEquals(0, drugs.count());
    }

    @Test
    public void newChemistService_addDrug_drugDescriptionIsNull_throwException_drugCountIsZero() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription(null);
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.addDrug(addDrugRequest));
        assertEquals(0, drugs.count());
    }

    @Test
    public void newChemistService_addDrug_countIsOne_UpdateDrug_drugDataChanged_countIsStillOne() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        addDrugRequest.setQuantity(30);

        AddDrugResponse response = chemistService.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setId(response.getId());
        updateDrugRequest.setDrugName("Paracetamol");
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setPrice(new BigDecimal(600));
        updateDrugRequest.setDescription("Paracetamol is a pain reliever");
        updateDrugRequest.setExpiryDate(LocalDate.of(2027, Month.AUGUST, 20));
        UpdateDrugResponse updateDrugResponse = chemistService.updateDrug(updateDrugRequest);

        assertEquals("Paracetamol", updateDrugResponse.getDrugName() );
        assertEquals("Paracetamol", updateDrugResponse.getBrand());
        assertEquals(new BigDecimal(600), updateDrugResponse.getPrice());
        assertEquals(1, drugs.count());
    }

    @Test
    public void newChemistService_addDrug_countIsOne_UpdateDrug_noUpdatedDrugName_ThrowsException() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        addDrugRequest.setQuantity(30);

        AddDrugResponse response = chemistService.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setId(response.getId());
        updateDrugRequest.setDrugName(null);
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setPrice(new BigDecimal(600));
        updateDrugRequest.setDescription("Paracetamol is a pain reliever");
        updateDrugRequest.setExpiryDate(LocalDate.of(2027, Month.AUGUST, 20));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.updateDrug(updateDrugRequest));


    }

    @Test
    public void newChemistService_addDrug_countIsOne_UpdateDrug_noUpdatedDrugBrand_ThrowsException() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        addDrugRequest.setQuantity(30);

        AddDrugResponse response = chemistService.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setId(response.getId());
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setBrand(null);
        updateDrugRequest.setPrice(new BigDecimal(600));
        updateDrugRequest.setDescription("Paracetamol is a pain reliever");
        updateDrugRequest.setExpiryDate(LocalDate.of(2027, Month.AUGUST, 20));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.updateDrug(updateDrugRequest));


    }
    @Test
    public void newChemistService_addDrug_countIsOne_UpdateDrug_noUpdatedDrugPrice_ThrowsException() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        addDrugRequest.setQuantity(30);

        AddDrugResponse response = chemistService.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setId(response.getId());
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setPrice(null);
        updateDrugRequest.setDescription("Paracetamol is a pain reliever");
        updateDrugRequest.setExpiryDate(LocalDate.of(2027, Month.AUGUST, 20));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.updateDrug(updateDrugRequest));


    }
    @Test
    public void newChemistService_addDrug_countIsOne_UpdateDrug_noUpdatedDrugDescription_ThrowsException() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        addDrugRequest.setQuantity(30);

        AddDrugResponse response = chemistService.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setId(response.getId());
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setPrice(new BigDecimal(600));
        updateDrugRequest.setDescription(null);
        updateDrugRequest.setExpiryDate(LocalDate.of(2027, Month.AUGUST, 20));
        assertThrows(IllegalArgumentException.class, ()-> chemistService.updateDrug(updateDrugRequest));


    }

    @Test
    public void newChemistService_addDrug_countIsOne_UpdateDrug_noUpdatedDrugExpiryDate_ThrowsException() {
        addDrugRequest.setDrugName("Augmentin");
        addDrugRequest.setBrand("Augmentin");
        addDrugRequest.setPrice(new BigDecimal(23000));
        addDrugRequest.setDescription("Augmentin is a prescription antibiotic that treats bacterial infections");
        addDrugRequest.setExpiryDate(LocalDate.of(2030, Month.JANUARY, 17));
        addDrugRequest.setQuantity(30);

        AddDrugResponse response = chemistService.addDrug(addDrugRequest);

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setId(response.getId());
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setBrand("Paracetamol");
        updateDrugRequest.setPrice(new BigDecimal(600));
        updateDrugRequest.setDescription("Paracetamol is a pain killer");
        updateDrugRequest.setExpiryDate(null);
        assertThrows(IllegalArgumentException.class, ()-> chemistService.updateDrug(updateDrugRequest));

    }


    @Test
    public void newChemistService_addDrug_drugsCountIsOne_DeleteDrug_drugsCountIsZero() {
        addDrugRequest.setDrugName("Paracetamol");
        addDrugRequest.setBrand("Paracetamol");
        addDrugRequest.setPrice(new BigDecimal(600));
        addDrugRequest.setDescription("Paracetamol is a pain killer");
        addDrugRequest.setExpiryDate(LocalDate.of(2030 , Month.JANUARY, 13));
        addDrugRequest.setQuantity(30);

        AddDrugResponse addDrugResponse = chemistService.addDrug(addDrugRequest);
        assertEquals(1, drugs.count());

        DeleteDrugRequest deleteDrugRequest = new DeleteDrugRequest();
        deleteDrugRequest.setId(addDrugResponse.getId());
        chemistService.deleteDrug(deleteDrugRequest);
        assertEquals(0, drugs.count());
    }

    @Test
    public void newChemistService_addDrug_drugsCountIsOne_DeleteDrug_wrongId_throwsException_drugsCountIsOne() {
        addDrugRequest.setDrugName("Paracetamol");
        addDrugRequest.setBrand("Paracetamol");
        addDrugRequest.setPrice(new BigDecimal(600));
        addDrugRequest.setDescription("Paracetamol is a pain killer");
        addDrugRequest.setExpiryDate(LocalDate.of(2030 , Month.JANUARY, 13));
        addDrugRequest.setQuantity(30);

        AddDrugResponse addDrugResponse = chemistService.addDrug(addDrugRequest);
        assertEquals(1, drugs.count());

        DeleteDrugRequest deleteDrugRequest = new DeleteDrugRequest();
        deleteDrugRequest.setId(10);

        assertThrows(IllegalArgumentException.class, ()-> chemistService.deleteDrug(deleteDrugRequest));
        assertEquals(1, drugs.count());
    }


    @Test
    public void newChemistService_addDrug_dispenseDrug_DrugQuantityReduces() {
        addDrugRequest.setQuantity(50);
        addDrugRequest.setDrugName("Paracetamol");
        addDrugRequest.setBrand("Emcap");
        addDrugRequest.setPrice(new BigDecimal(300));
        addDrugRequest.setDescription("Pain reliever");
        addDrugRequest.setExpiryDate(LocalDate.of(2027, Month.AUGUST, 4));

        AddDrugResponse addDrugResponse = chemistService.addDrug(addDrugRequest);
        assertEquals(1, drugs.count());


        DispenseDrugRequest dispenseDrugRequest = new DispenseDrugRequest();
        dispenseDrugRequest.setId(addDrugResponse.getId());
//        IO.println(addDrugResponse.getId());
        dispenseDrugRequest.setQuantity(1);
        dispenseDrugRequest.setBrand(addDrugResponse.getBrand());
        dispenseDrugRequest.setDrugName(addDrugResponse.getDrugName());
        dispenseDrugRequest.setDescription(addDrugResponse.getDescription());
        dispenseDrugRequest.setPrice(addDrugResponse.getPrice());


        DispenseDrugResponse dispenseDrugResponse = chemistService.dispenseDrug(dispenseDrugRequest);

        assertEquals(49, dispenseDrugResponse.getQuantity());



        DispenseDrugRequest dispenseDrugRequestTwo = new DispenseDrugRequest();
        dispenseDrugRequestTwo.setId(dispenseDrugResponse.getId());
//        IO.println(addDrugResponse.getId());
        dispenseDrugRequestTwo.setQuantity(5);
        dispenseDrugRequestTwo.setBrand(dispenseDrugResponse.getBrand());
        dispenseDrugRequestTwo.setDrugName(dispenseDrugResponse.getDrugName());
        dispenseDrugRequestTwo.setDescription(dispenseDrugResponse.getDescription());
        dispenseDrugRequestTwo.setPrice(dispenseDrugResponse.getPrice());


        DispenseDrugResponse dispenseDrugResponseTwo = chemistService.dispenseDrug(dispenseDrugRequestTwo);

        assertEquals(44, dispenseDrugResponseTwo.getQuantity());


    }

    @Test
    public void newChemistService_addDrug_dispenseDrug_wrongId_throwException() {
        addDrugRequest.setQuantity(50);
        addDrugRequest.setDrugName("Paracetamol");
        addDrugRequest.setBrand("Emcap");
        addDrugRequest.setPrice(new BigDecimal(300));
        addDrugRequest.setDescription("Pain reliever");
        addDrugRequest.setExpiryDate(LocalDate.of(2027, Month.AUGUST, 4));

        AddDrugResponse addDrugResponse = chemistService.addDrug(addDrugRequest);
        assertEquals(1, drugs.count());


        DispenseDrugRequest dispenseDrugRequest = new DispenseDrugRequest();
        dispenseDrugRequest.setId(addDrugResponse.getId());
//        IO.println(addDrugResponse.getId());
        dispenseDrugRequest.setQuantity(1);
        dispenseDrugRequest.setBrand(addDrugResponse.getBrand());
        dispenseDrugRequest.setDrugName(addDrugResponse.getDrugName());
        dispenseDrugRequest.setDescription(addDrugResponse.getDescription());
        dispenseDrugRequest.setPrice(addDrugResponse.getPrice());


        DispenseDrugResponse dispenseDrugResponse = chemistService.dispenseDrug(dispenseDrugRequest);

        assertEquals(49, dispenseDrugResponse.getQuantity());



        DispenseDrugRequest dispenseDrugRequestTwo = new DispenseDrugRequest();
        dispenseDrugRequestTwo.setId(1000);
//        IO.println(addDrugResponse.getId());
        dispenseDrugRequestTwo.setQuantity(5);
        dispenseDrugRequestTwo.setBrand(dispenseDrugResponse.getBrand());
        dispenseDrugRequestTwo.setDrugName(dispenseDrugResponse.getDrugName());
        dispenseDrugRequestTwo.setDescription(dispenseDrugResponse.getDescription());
        dispenseDrugRequestTwo.setPrice(dispenseDrugResponse.getPrice());


        assertThrows(IllegalArgumentException.class, ()-> chemistService.dispenseDrug(dispenseDrugRequestTwo));



    }







}
