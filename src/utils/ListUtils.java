package utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListUtils {
    public static <T> List<List<T>> divideEqually(final List<T> lista, final int divisore) {
        int numSottoListe = Math.min(divisore, lista.size());
        int dimensioneSottoLista = lista.size() / numSottoListe;
        int elementiExtra = lista.size() % numSottoListe;

        return IntStream.range(0, numSottoListe).parallel()
                .mapToObj(i -> {
                    int startIndex = i * dimensioneSottoLista + Math.min(i, elementiExtra);
                    int endIndex = startIndex + dimensioneSottoLista + (i < elementiExtra ? 1 : 0);
                    return lista.subList(startIndex, endIndex);
                })
                .collect(Collectors.toList());
    }
}
