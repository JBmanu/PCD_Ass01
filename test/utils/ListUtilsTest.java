package utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtilsTest {

    @Test
    public void divideEquallyListWithElementsGreaterThatDivisor() {
        final List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final int divisor = 3;
        List<List<Integer>> dividedList = ListUtils.divideEqually(list, divisor);
        assertEquals(3, dividedList.size());
        assertEquals(3, dividedList.get(0).size());
        assertEquals(3, dividedList.get(1).size());
        assertEquals(3, dividedList.get(2).size());
    }

    @Test
    public void divideEquallyListWithElementsLessThatDivisor() {
        final List<Integer> list = List.of(1, 2, 3);
        final int divisor = 5;
        List<List<Integer>> dividedList = ListUtils.divideEqually(list, divisor);
        assertEquals(3, dividedList.size());
        assertEquals(1, dividedList.get(0).size());
        assertEquals(1, dividedList.get(1).size());
        assertEquals(1, dividedList.get(2).size());
    }






}
