package services;

import dbaccess.UserDB;
import exceptions.UserException;
import models.User;

import java.util.List;

public class UserService {
    UserDB userDb = UserDB.getInstance();

    public void insert(User user) throws UserException {

//        if(userDb.find(user.getEmail()) != null) {
//            throw new UserException("A user with the email " +user.getEmail()+ " already exists");
//        }
        userDb.insert(user);
    }

    public void insertMany(List<User> users) throws UserException {

//        for(User user : users) {
//            if(userDb.find(user.getEmail()) != null) {
//                throw new UserException("A user with the email " +user.getEmail()+ " already exists");
//            }
//        }
        userDb.insertMany(users);
    }

    public User find(String email) {
        return userDb.find(email);
    }

    public void update(User user) {
        userDb.update(user);
    }

    public void delete(User user) {
        userDb.delete(user);
    }
}
