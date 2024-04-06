package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
    public static <T> List<List<T>> divideEqually(List<T> lista, int dimensione) {
        return lista.parallelStream()
                .collect(Collectors.groupingByConcurrent(i -> Math.floorDiv(lista.indexOf(i), dimensione)))
                .values().stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
    }
}
