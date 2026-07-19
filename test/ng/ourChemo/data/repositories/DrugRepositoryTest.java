package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrugRepositoryTest {

    @Test
    public void newDrugRepositoryCanSavedrug() {
        DrugRepository drugRepository = new DrugRepositoryImpl();
        Drug drug = new Drug();

        assertEquals(drug, drugRepository.save(drug));
    }



    @Test
    public void newRepoShouldBeAbleToSaveNewUser_CountIsOne() {
        DrugRepository drugRepository = new DrugRepositoryImpl();
        Drug drug = new Drug();

        drugRepository.save(drug);
        assertEquals(1, drugRepository.count());
    }

    @Test
    public void newUserRepo_FindUserByID_returns_User_countIs1() {
        DrugRepository drugRepository = new DrugRepositoryImpl();
        Drug drug = new Drug();
        drugRepository.save(drug);
        assertEquals(1, drugRepository.count());

        assertEquals(drug, drugRepository.findById(1));
    }


    @Test
    public void saveTwodrugRepositpry_findUserByid_returns_updatedUser() {
        DrugRepository DrugRepository = new DrugRepositoryImpl();
        Drug drug = new Drug();
        DrugRepository.save(drug);
        assertEquals(1, DrugRepository.count());

        Drug drugTwo = new Drug();
        DrugRepository.save(drugTwo);

        int id = drugTwo.getId();

        assertEquals(2, DrugRepository.count());
        assertEquals(drugTwo, DrugRepository.findById(id));
    }


    @Test
    public void savesameDispensedDrugRepositoryTwice_UpdatesCountIsStillIOne() {
        DrugRepository DrugRepository = new DrugRepositoryImpl();
        Drug drug = new Drug();
        DrugRepository.save(drug);
        assertEquals(1, DrugRepository.count());

        DrugRepository.save(drug);

        assertEquals(1, DrugRepository.count());
    }


    @Test
    public void saveTwoUsers_deleteAll_findUserReturnsNull() {
        DrugRepository DrugRepository = new DrugRepositoryImpl();
        Drug drug = new Drug();
        DrugRepository.save(drug);
        assertEquals(1, DrugRepository.count());

        Drug userTwo = new Drug();
        DrugRepository.save(userTwo);
        assertEquals(2, DrugRepository.count());

        DrugRepository.save(drug);
        DrugRepository.deleteAll();
        assertEquals(null, DrugRepository.findById(1));
        assertEquals(0, DrugRepository.count());
    }

    @Test
    public void saveTwoUsers_deleteByID_findUserReturnsNull() {
        DrugRepository DrugRepository = new DrugRepositoryImpl();
        Drug drug = new Drug();
        DrugRepository.save(drug);
        assertEquals(1, DrugRepository.count());


        int id = drug.getId();
        DrugRepository.delete(drug);
        assertEquals(null, DrugRepository.findById(id));
        assertEquals(0, DrugRepository.count());
    }





}



