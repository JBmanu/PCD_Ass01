package utils;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ListUtilsTest {

    @Test
    public void divideEquallyListWithElementsGreaterThatDivisor() {
        final List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final int divisor = 3;
        List<List<Integer>> dividedList = ListUtils.divideEqually(list, divisor);
        assert dividedList.size() == 3;
        assert dividedList.get(0).size() == 3;
        assert dividedList.get(1).size() == 3;
        assert dividedList.get(2).size() == 3;
    }

}
