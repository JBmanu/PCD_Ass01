package utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListUtils {

    public static <T> List<List<T>> divideEqually(final List<T> list, final int divisor) {
        int numSubList = Math.min(divisor, list.size());
        int dimensionSubList = list.size() / numSubList;
        int extraElements = list.size() % numSubList;

        return IntStream.range(0, numSubList).parallel()
                .mapToObj(i -> {
                    int startIndex = i * dimensionSubList + Math.min(i, extraElements);
                    int endIndex = startIndex + dimensionSubList + (i < extraElements ? 1 : 0);
                    return list.subList(startIndex, endIndex);
                })
                .collect(Collectors.toList());
    }
}
