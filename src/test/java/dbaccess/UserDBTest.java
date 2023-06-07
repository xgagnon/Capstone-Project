package dbaccess;

import enums.Role;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        String email = "user0@example.com";
        String firstName = "Alex";
        String lastName = "Ferit";
        long phone = 7538642910L;
        String address = "65 Wser St, City";
        String password = "hashed_password";
        Role role = Role.admin;
        int cartLength = 0;
        int likeLength = 0 ;
        int historyLength = 0;

        User user = userDb.find(userId);

        assertEquals(userId,user.getUser_id());
        assertEquals(email,user.getEmail());
        assertEquals(firstName,user.getFirst_name());
        assertEquals(lastName,user.getLast_name());
        assertEquals(phone,user.getPhone());
        assertEquals(address,user.getAddress());
        assertEquals(password,user.getPassword());
        assertEquals(role,user.getRole());
        assertEquals(cartLength,user.getCart().size());
        assertEquals(likeLength,user.getLikes().size());
        assertEquals(historyLength,user.getTransactionHistory().size());

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

}