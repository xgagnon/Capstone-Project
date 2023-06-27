package dbaccess;

import models.Counter;
import org.junit.Test;

import static org.junit.Assert.*;

public class CounterDBTest {

    CounterDB counterDB = CounterDB.getInstance();
    @Test
    public void testFind() {
        Counter counter = counterDB.find("userid");
        assertNotNull(counter);
        assertEquals(10007, counter.getSeq());
    }

    @Test
    public void testUpdate() {
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

}