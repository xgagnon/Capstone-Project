package dbaccess;

import com.google.firebase.auth.FirebaseAuthException;
import enums.Role;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDBTest {

    UserDB userDb = UserDB.getInstance();

    @Test
    public void testInsert() throws FirebaseAuthException, IOException {
        String email = "user7@example.com";
        String firstName = "Jose";
        String lastName = "Loewen";
        long phone = 4039876543L;
        String address = "163 Bass St, City";
        Role role = Role.user;
        String password = "password";

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setAddress(address);
        user.setRole(role);
        user.setPassword(password);

        user.getCart().add(123456789012L);
        user.getCart().add(678901234567L);
        user.getLikes().add(345678901234L);
        user.getLikes().add(234567890123L);
        user.getLikes().add(987654321098L);
        user.getTransactions().add(123456789003L);

        userDb.insert(user);

        User foundUser = userDb.find(user.getEmail());

        assertNotNull(foundUser.getUserId());
        assertNotNull(foundUser.getUid());
        assertEquals(user.getEmail(),foundUser.getEmail());
        assertEquals(user.getFirstName(),foundUser.getFirstName());
        assertEquals(user.getLastName(),foundUser.getLastName());
        assertEquals(user.getPhone(),foundUser.getPhone());
        assertEquals(user.getAddress(),foundUser.getAddress());
        assertEquals(user.getRole(),foundUser.getRole());
        assertEquals(user.getCart().size(),foundUser.getCart().size());
        assertEquals(user.getLikes().size(),foundUser.getLikes().size());
        assertEquals(user.getTransactions().size(),foundUser.getTransactions().size());
    }

    @Test
    public void testFind() {
        int userId = 10002;
        String uid = "HO7oPmssUzSDtpx1MC4ZkxduUlj1";
        String email = "user1@example.com";
        String firstName = "Jane";
        String lastName = "Smith";
        long phone = 9876543210L;
        String address = "456 Elm St, City";
        Role role = Role.user;
        int cartLength = 4;
        int likeLength = 1;
        int historyLength = 3;

        User user = userDb.find(email);

        assertEquals(userId,user.getUserId());
        assertEquals(uid,user.getUid());
        assertEquals(email,user.getEmail());
        assertEquals(firstName,user.getFirstName());
        assertEquals(lastName,user.getLastName());
        assertEquals(phone,user.getPhone());
        assertEquals(address,user.getAddress());
        assertEquals(role,user.getRole());
        assertEquals(cartLength,user.getCart().size());
        assertEquals(likeLength,user.getLikes().size());
        assertEquals(historyLength,user.getTransactions().size());

    }

    @Test
    public void testUpdate() throws FirebaseAuthException, IOException {
        String email = "user8@example.com";
        String firstName = "Jose";
        String lastName = "Loewen";
        long phone = 4039876543L;
        String address = "163 Bass St, City";
        Role role = Role.user;

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setAddress(address);
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
    public void testDelete() throws FirebaseAuthException, IOException {
        String email = "user9@example.com";
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