package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    @Test
    public void newRepoShouldBeAbleToSaveNewUser_CountIsOne() {
        UserRepository userRepository = new UserRepositoryImpl();
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void newUserRepo_FindUserByID_returns_User() {
        UserRepository userRepository = new UserRepositoryImpl();
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());

        assertEquals(user, userRepository.findById(1));
    }


    @Test
    public void updateUser_findUserByid_returns_updatedUser() {
        UserRepository userRepository = new UserRepositoryImpl();
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());

        User userTwo = new User();
        userRepository.save(userTwo);
        assertEquals(2, userRepository.count());

        userRepository.save(user);
        assertEquals(user, userRepository.findById(1));
        assertEquals(2, userRepository.count());
    }


    @Test
    public void saveTwoUsers_deleteAll_findUserReturnsNull() {
        UserRepository userRepository = new UserRepositoryImpl();
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());

        User userTwo = new User();
        userRepository.save(userTwo);
        assertEquals(2, userRepository.count());

        userRepository.save(user);
        userRepository.deleteAll();
        assertEquals(null, userRepository.findById(1));
        assertEquals(0, userRepository.count());
    }

    @Test
    public void saveTwoUsers_deleteByID_findUserReturnsNull() {
        UserRepository userRepository = new UserRepositoryImpl();
        User user = new User();
        userRepository.save(user);
        assertEquals(1, userRepository.count());

        int id = user.getId();
        userRepository.delete(user);
        assertEquals(null, userRepository.findById(id));
        assertEquals(0, userRepository.count());
    }

}