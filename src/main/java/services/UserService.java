package services;

import dbaccess.UserDB;
import models.User;

import java.util.List;

public class UserService {
    UserDB userDb = UserDB.getInstance();

    public void insert(User user) {

    }

    public void insertMany(List<User> users) {

    }

    public User find(int userId) {
        return userDb.find(userId);
    }

    public void update(User user) {

    }

    public void delete(User user) {

    }
}
