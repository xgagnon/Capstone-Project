package services;

import dbaccess.UserDB;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserDB userDb = UserDB.getInstance();
    @Test
    public void testInsert() {

    }

    @Test
    public void testInsertExistingUser() {
    }

    @Test
    public void testInsertNullUser() {

    }

    @Test
    public void testInsertMany() {
    }

    @Test
    public void testInsertManyWithExistingUser() {
    }

    @Test
    public void testInsertManyWithNullUser() {
    }

    @Test
    public void testFind() {
    }

    @Test
    public void testFindNonExistentUser() {
    }

    @Test
    public void testFindInvalidId() {
    }

    @Test
    public void testFindNullId() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testUpdateToExistingEmail() {
    }

    @Test
    public void testUpdateNullEmail() {
    }

    @Test
    public void testUpdateId() {
    }

    @Test
    public void testUpdateNonExistentUser() {
    }

    @Test
    public void testUpdateInvalidInputs() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testDeleteNonExistentUser() {
    }

    @Test
    public void testDeleteInvalidId() {
    }
}