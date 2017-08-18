package it.enricod.redux;

import io.vavr.collection.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test01 {

    @Test
    public void testFold() {
        List<Integer> values = List.of(1, 2, 5, 12);
        assertEquals( new Integer(20), values.fold(0, (i1,i2) -> i1+i2));
        assertEquals( new Integer(20), values.reduceLeft((i1,i2)->i1+i2));
    }
}
