package validation;


import dbaccess.UserDB;
import org.junit.Test;

public class userValidationTest {

    UserDB userDb = UserDB.getInstance();

    @Test
    public void testUserId(){
        int expectedInt = 0000001;
    }

    @Test
    public void testEmail(){

    }

    @Test
    public void testFirstName(){

    }

    @Test
    public void testLastName(){

    }

    @Test
    public void testPhone(){
        Long expectedLong = 0000000001L;
    }

    @Test
    public void testAddress(){

    }

    @Test
    public void testPassword(){

    }

    @Test
    public void testRole(){

    }

    @Test
    public void testStatus(){

    }

    @Test
    public void testCart(){

    }

    @Test
    public void testLikes(){

    }

    @Test
    public void testTransactions(){

    }
}