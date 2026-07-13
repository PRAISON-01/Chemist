package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrug;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DispensedDrugRepositoryTest {

    @Test
    public void newDispensedDrugRepositoryCanSaveDispensedDrug() {
        DispensedDrugRepository DispensedDrugRepository = new DispensedDrugRepositoryImpl();
        DispensedDrug DispensedDrug = new DispensedDrug();

        assertEquals(DispensedDrug, DispensedDrugRepository.save(DispensedDrug));
    }

    @Test
    public void newRepoShouldBeAbleToSaveNewUser_CountIsOne() {
        DispensedDrugRepository dispensedDrugRepository = new DispensedDrugRepositoryImpl();
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrug);
        assertEquals(1, dispensedDrugRepository.count());
    }

    @Test
    public void newUserRepo_FindUserByID_returns_User_countIs1() {
        DispensedDrugRepository dispensedDrugRepository = new DispensedDrugRepositoryImpl();
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrug);
        int id = dispensedDrug.getId();
        assertEquals(1, dispensedDrugRepository.count());
        assertEquals(dispensedDrug, dispensedDrugRepository.findById(id));
    }


    @Test
    public void saveTwoDispensedDrugRepositpry_findUserByid_returns_updatedUser() {
        DispensedDrugRepository dispensedDrugRepository = new DispensedDrugRepositoryImpl();
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrug);
        assertEquals(1, dispensedDrugRepository.count());

        DispensedDrug dispensedDrugTwo = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrugTwo);

        int id = dispensedDrugTwo.getId();

        assertEquals(2, dispensedDrugRepository.count());
        assertEquals(dispensedDrugTwo, dispensedDrugRepository.findById(id));
    }


    @Test
    public void savesameDispensedDrugRepositoryTwice_UpdatesCountIsStillIOne() {
        DispensedDrugRepository dispensedDrugRepository = new DispensedDrugRepositoryImpl();
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrug);
        assertEquals(1, dispensedDrugRepository.count());

        dispensedDrugRepository.save(dispensedDrug);

        assertEquals(1, dispensedDrugRepository.count());
    }


    @Test
    public void saveTwoUsers_deleteAll_findUserReturnsNull() {
        DispensedDrugRepository dispensedDrugRepository = new DispensedDrugRepositoryImpl();
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrug);
        assertEquals(1, dispensedDrugRepository.count());

        DispensedDrug dispensedDrugTwo = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrugTwo);
        assertEquals(2, dispensedDrugRepository.count());

        dispensedDrugRepository.save(dispensedDrug);
        dispensedDrugRepository.deleteAll();

        assertEquals(null, dispensedDrugRepository.findById(1));
        assertEquals(0, dispensedDrugRepository.count());
    }

    @Test
    public void saveTwoUsers_deleteByID_findUserReturnsNull() {
        DispensedDrugRepository dispensedDrugRepository = new DispensedDrugRepositoryImpl();
        DispensedDrug dispensedDrug = new DispensedDrug();
        dispensedDrugRepository.save(dispensedDrug);
        assertEquals(1, dispensedDrugRepository.count());


        int id = dispensedDrug.getId();
        dispensedDrugRepository.delete(dispensedDrug);
        assertEquals(null, dispensedDrugRepository.findById(id));
        assertEquals(0, dispensedDrugRepository.count());
    }

}