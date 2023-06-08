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
    public void testInsert() {
        int userId = 10001006;
        String email = "user6@example.com";
        String firstName = "Jose";
        String lastName = "Loewen";
        long phone = 4039876543L;
        String address = "163 Bass St, City";
        String password = "hashed_password";
        Role role = Role.user;

        User user = new User();
        user.setUserId(userId);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setAddress(address);
        user.setPassword(password);
        user.setRole(role);

        user.getCart().add(123456789012L);
        user.getCart().add(678901234567L);
        user.getLikes().add(345678901234L);
        user.getLikes().add(234567890123L);
        user.getLikes().add(987654321098L);
        user.getTransaction().add(123456789003L);

        userDb.insert(user);

        User foundUser = userDb.find(user.getUserId());

        assertEquals(user.getUserId(),foundUser.getUserId());
        assertEquals(user.getEmail(),foundUser.getEmail());
        assertEquals(user.getFirstName(),foundUser.getFirstName());
        assertEquals(user.getLastName(),foundUser.getLastName());
        assertEquals(user.getPhone(),foundUser.getPhone());
        assertEquals(user.getAddress(),foundUser.getAddress());
        assertEquals(user.getPassword(),foundUser.getPassword());
        assertEquals(user.getRole(),foundUser.getRole());
        assertEquals(user.getCart().size(),foundUser.getCart().size());
        assertEquals(user.getLikes().size(),foundUser.getLikes().size());
        assertEquals(user.getTransaction().size(),foundUser.getTransaction().size());
    }

    @Test
    public void testInsertMany() {
    }

    @Test
    public void testFind() {
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
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }

}