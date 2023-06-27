package dbaccess;

import models.Counter;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CounterDBTest {

    CounterDB counterDB = CounterDB.getInstance();

    @Test
    public void testFindUserId() {
        Counter counter = counterDB.find("userid");
        assertNotNull(counter);
        assertEquals(10007, counter.getSeq());
    }

    @Test
    public void testFindImageId() {
        Counter counter = counterDB.find("imageId");
        assertNotNull(counter);
        assertEquals(1000000010, counter.getSeq());
    }

    @Test
    public void testFindTransactionId() {
        Counter counter = counterDB.find("transactionId");
        assertNotNull(counter);
        assertEquals(1000000006, counter.getSeq());
    }

    @Test
    public void testUpdateUserId() {
        Counter counter = counterDB.find("userid");
        assertNotNull(counter);
        assertEquals(10007, counter.getSeq());

        counter.incrementSeq();
        counterDB.update(counter);

        Counter updatedCounter = counterDB.find("userid");
        assertNotNull(counter);
        assertEquals(10008, updatedCounter.getSeq());

        //After Update test, make sure to manually change counter
        //back to original value (10007)
    }

    @Test
    public void testUpdateImageId() {
        Counter counter = counterDB.find("imageId");
        assertNotNull(counter);
        assertEquals(1000000010, counter.getSeq());

        counter.incrementSeq();
        counterDB.update(counter);

        Counter updatedCounter = counterDB.find("imageId");
        assertNotNull(counter);
        assertEquals(1000000011, updatedCounter.getSeq());

        //After Update test, make sure to manually change counter
        //back to original value (10007)
    }

    @Test
    public void testUpdateTransactionId() {
        Counter counter = counterDB.find("transactionId");
        assertNotNull(counter);
        assertEquals(1000000006, counter.getSeq());

        counter.incrementSeq();
        counterDB.update(counter);

        Counter updatedCounter = counterDB.find("transactionId");
        assertNotNull(counter);
        assertEquals(1000000007, updatedCounter.getSeq());

        //After Update test, make sure to manually change counter
        //back to original value (10007)
    }

}