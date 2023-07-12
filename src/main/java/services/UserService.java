package services;

import com.google.firebase.auth.FirebaseAuthException;
import dbaccess.UserDB;
import exceptions.UserException;
import models.User;

import java.io.IOException;
import java.util.List;

public class UserService {
    UserDB userDb = UserDB.getInstance();

    public User insert(User user) throws UserException, FirebaseAuthException, IOException {

//        if(userDb.find(user.getEmail()) != null) {
//            throw new UserException("A user with the email " +user.getEmail()+ " already exists");
//        }
        userDb.insert(user);
        return user;
    }

    public User find(String email) {
        return userDb.find(email);
    }

    public User update(User user) {
        userDb.update(user);
        return user;
    }

    public User delete(User user) {
        userDb.delete(user);
        return user;
    }
}
