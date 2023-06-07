package dbaccess;

import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDBTest {

    UserDB userDb;
    @Before
    public void setUp() {
        userDb = new UserDB();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void insert() {
//        User user = new User();
//        user.setId(6474);
    }

    @Test
    public void insertMany() {
    }

    @Test
    public void find() {
        int userId = 10001000;
        User user = userDb.find(userId);

        assertEquals(userId,user.getUser_id());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

}