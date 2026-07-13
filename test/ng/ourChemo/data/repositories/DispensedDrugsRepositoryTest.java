package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrugs;
import ng.ourChemo.data.models.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DispensedDrugsRepositoryTest {

    @Test
    public void newDispensedDrugsRepositoryCanSaveDispensedDrugs() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();

        assertEquals(dispensedDrugs, dispensedDrugsRepository.save(dispensedDrugs));
    }



    @Test
    public void newRepoShouldBeAbleToSaveNewUser_CountIsOne() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrugs);
        assertEquals(1, dispensedDrugsRepository.count());
    }

    @Test
    public void newUserRepo_FindUserByID_returns_User_countIs1() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrugs);
        assertEquals(1, dispensedDrugsRepository.count());

        assertEquals(dispensedDrugs, dispensedDrugsRepository.findById(1));
    }


    @Test
    public void saveTwodispensedDrugsRepositpry_findUserByid_returns_updatedUser() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrugs);
        assertEquals(1, dispensedDrugsRepository.count());

        DispensedDrugs dispensedDrugsTwo = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrugsTwo);

        int id = dispensedDrugsTwo.getId();

        assertEquals(2, dispensedDrugsRepository.count());
        assertEquals(dispensedDrugsTwo, dispensedDrugsRepository.findById(id));
    }


    @Test
    public void savesameDispensedDrugRepositoryTwice_UpdatesCountIsStillIOne() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrugs);
        assertEquals(1, dispensedDrugsRepository.count());

        dispensedDrugsRepository.save(dispensedDrugs);

        assertEquals(1, dispensedDrugsRepository.count());
    }


    @Test
    public void saveTwoUsers_deleteAll_findUserReturnsNull() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrugs);
        assertEquals(1, dispensedDrugsRepository.count());

        DispensedDrugs userTwo = new DispensedDrugs();
        dispensedDrugsRepository.save(userTwo);
        assertEquals(2, dispensedDrugsRepository.count());

        dispensedDrugsRepository.save(dispensedDrugs);
        dispensedDrugsRepository.deleteAll();
        assertEquals(null, dispensedDrugsRepository.findById(1));
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void saveTwoUsers_deleteByID_findUserReturnsNull() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        dispensedDrugsRepository.save(dispensedDrugs);
        assertEquals(1, dispensedDrugsRepository.count());


        int id = dispensedDrugs.getId();
        dispensedDrugsRepository.delete(dispensedDrugs);
        assertEquals(null, dispensedDrugsRepository.findById(id));
        assertEquals(0, dispensedDrugsRepository.count());
    }

    @Test
    public void newDrugsRepository_setDispensedByUser_getDispensedByUser_returns_DispensedByUser() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        User kobe = new User();
        dispensedDrugs.setDispensedBy(kobe);
        assertEquals(kobe, dispensedDrugs.getDispensedBy());
    }

    @Test
    public void newDrugRepository_setTime_getTime_returnsDate() {
        DispensedDrugsRepository dispensedDrugsRepository = new DispensedDrugsRepositoryImpl();
        DispensedDrugs dispensedDrugs = new DispensedDrugs();
        LocalDate date = LocalDate.now();
        dispensedDrugs.setDate(date);
        assertEquals(date, dispensedDrugs.getDate());
    }




}