package dbaccess;

import enums.Role;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDBTest {

    UserDB userDb = UserDB.getInstance();

    @Test
    public void testInsert() {
        int userId = 10008;
        String email = "user6@example.com";
        String firstName = "Jose";
        String lastName = "Loewen";
        long phone = 4039876543L;
        String address = "163 Bass St, City";
        String password = "hashed_password";
        Role role = Role.user;

        User user = new User();
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
        user.getTransactions().add(123456789003L);

        userDb.insert(user);

        User foundUser = userDb.find(user.getEmail());

        assertEquals(userId,foundUser.getUserId());
        assertEquals(user.getEmail(),foundUser.getEmail());
        assertEquals(user.getFirstName(),foundUser.getFirstName());
        assertEquals(user.getLastName(),foundUser.getLastName());
        assertEquals(user.getPhone(),foundUser.getPhone());
        assertEquals(user.getAddress(),foundUser.getAddress());
        assertEquals(user.getPassword(),foundUser.getPassword());
        assertEquals(user.getRole(),foundUser.getRole());
        assertEquals(user.getCart().size(),foundUser.getCart().size());
        assertEquals(user.getLikes().size(),foundUser.getLikes().size());
        assertEquals(user.getTransactions().size(),foundUser.getTransactions().size());
    }

    @Test
    public void testInsertMany() {
        User user0 = new User();
        User user1 = new User();
        List<User> users = new ArrayList<>();

        user0.setEmail("user9@example.com");
        user0.setFirstName("first9");
        user0.setLastName("last9");
        user0.setPhone(987654321L);
        user0.setAddress("421 Hash St, City");
        user0.setPassword("hashed_password");
        user0.setRole(Role.user);
        users.add(user0);

        user1.setEmail("user10@example.com");
        user1.setFirstName("first10");
        user1.setLastName("last10");
        user1.setPhone(987654321L);
        user1.setAddress("421 Hash St, City");
        user1.setPassword("hashed_password");
        user1.setRole(Role.user);
        users.add(user1);


        userDb.insertMany(users);

        User foundUser0 = userDb.find(users.get(0).getEmail());
        User foundUser1 = userDb.find(users.get(1).getEmail());

        assertNotNull(foundUser0);
        assertNotNull(foundUser1);
    }

    @Test
    public void testFind() {
        int userId = 10002;
        String email = "user1@example.com";
        String firstName = "Jane";
        String lastName = "Smith";
        long phone = 9876543210L;
        String address = "456 Elm St, City";
        String password = "hashed_password";
        Role role = Role.user;
        int cartLength = 4;
        int likeLength = 1;
        int historyLength = 3;

        User user = userDb.find(email);

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
        assertEquals(historyLength,user.getTransactions().size());

    }

    @Test
    public void testUpdate() {
        String email = "user8@example.com";
        String firstName = "Jose";
        String lastName = "Loewen";
        long phone = 4039876543L;
        String address = "163 Bass St, City";
        String password = "hashed_password";
        Role role = Role.user;

        User user = new User();
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
        user.getTransactions().add(123456789003L);

        userDb.insert(user);
        User foundUser = userDb.find(user.getEmail());
        assertNotNull(foundUser);

        String newFirstName = "Braedon";
        String newLastName = "Knox";
        foundUser.setFirstName(newFirstName);
        foundUser.setLastName(newLastName);

        userDb.update(foundUser);
        foundUser = userDb.find(foundUser.getEmail());
        assertNotEquals(user.getFirstName(),foundUser.getFirstName());
        assertNotEquals(user.getLastName(),foundUser.getLastName());
        assertEquals(newFirstName,foundUser.getFirstName());
        assertEquals(newLastName,foundUser.getLastName());
    }

    @Test
    public void testDelete() {
        String email = "user7@example.com";
        String firstName = "Jose";
        String lastName = "Loewen";
        long phone = 4039876543L;
        String address = "163 Bass St, City";
        String password = "hashed_password";
        Role role = Role.user;

        User user = new User();
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
        user.getTransactions().add(123456789003L);

        userDb.insert(user);
        User foundUser = userDb.find(user.getEmail());
        assertNotNull(foundUser);

        userDb.delete(foundUser);
        foundUser = userDb.find(user.getEmail());
        assertNull(foundUser);

    }

}