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

}