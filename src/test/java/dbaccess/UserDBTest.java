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
        int userId = 10001003;
        String email = "user3@example.com";
        String firstName = "Sarah";
        String lastName = "Johnson";
        long phone = 9876543210L;
        String address = "789 Oak St, City";
        String password = "hashed_password";
        Role role = Role.user;
        int cartLength = 3;
        int likeLength = 4 ;
        int historyLength = 3;

        User user = userDb.find(userId);

        assertEquals(userId,user.getUserId());
        assertEquals(email,user.getEmail());
        assertEquals(firstName,user.getFirstName());
        assertEquals(lastName,user.getLastName());
        assertEquals(phone,user.getPhone());
        assertEquals(address,user.getAddress());
        assertEquals(password,user.getPassword());
        assertEquals(role,user.getRole());
        assertEquals(cartLength,user.getCart().size());
        assertEquals(likeLength,user.getLikes().size());
        assertEquals(historyLength,user.getTransaction().size());

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

}