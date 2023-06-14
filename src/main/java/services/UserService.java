package services;

import dbaccess.UserDB;
import exceptions.UserException;
import models.User;

import java.util.List;

public class UserService {
    UserDB userDb = UserDB.getInstance();

    public void insert(User user) throws UserException {
        User searchedUser = userDb.find(user.getEmail());

        if(searchedUser != null) {
            throw new UserException("A user with this email already exists");
        }
        else {
            userDb.insert(user);
        }
    }

    public void insertMany(List<User> users) {

    }

    public User find(String email) {
        return userDb.find(email);
    }

    public void update(User user) {

    }

    public void delete(User user) {

    }
}
