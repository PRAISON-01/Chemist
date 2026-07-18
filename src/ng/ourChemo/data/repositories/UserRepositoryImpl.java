package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static List<User> usersList = new ArrayList<>();

    private static int count;

    @Override
    public User save(User user) {
        if(isNew(user))
            saveNew(user);
        else updateUser(user);
        return user;
    }

    private void updateUser(User user) {
        deleteById(user.getId());
        usersList.add(user);
    }

    private void saveNew(User user) {
        user.setId(++count);
        usersList.add(user);
    }

    private boolean isNew(User user) {
        if (user.getId() == 0) {
            return true;
        }
        return false;
    }

    public void deleteById(int id) {
        User userToDelete = findById( id);
        delete(userToDelete);
//        usersList.remove(userToDelete);
    }

    @Override
    public void delete(User user) {
        usersList.remove(user);
    }

    @Override
    public void deleteAll() {
        usersList.clear();
    }

    @Override
    public long count() {
        return usersList.size();
    }

    @Override
    public User findById(int id) {
        for(User user : usersList) {
            if( user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User findUserName(String userName) {
        for(var user : usersList) if(user.getUserName().equalsIgnoreCase(userName)) return user;
        return null;
    }
}
