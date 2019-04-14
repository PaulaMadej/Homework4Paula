package com.infoshareacademy.homeworks;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.averagingDouble;

public class GradeService {

    public String[][] calculateAverage(String[][] data) {

        if (data == null || data.length == 0) {
            return new String[][]{{}};
        } else {

            Map<String, Double> avg = Arrays.stream(data)
                    .collect(Collectors.groupingBy(s -> s[0],
                            averagingDouble(s -> parseInt(s[1]))));

            String[] strings1 = avg.keySet().toArray(String[]::new);
            String[] strings2 = avg.values().stream().map(m -> m.toString()).toArray(String[]::new);
            String[][] nowe = new String[strings1.length][2];

            for (int i = 0; i < nowe.length; i++) {
                nowe[i][0] = strings1[i];
                nowe[i][1] = strings2[i];
            }

            for (int i = 0; i < nowe.length; i++) {
                DecimalFormat df = new DecimalFormat("##.00");
                nowe[i][1] = df.format(Double.parseDouble(nowe[i][1])).replace(",", ".");
            }

            Arrays.sort(nowe, new Comparator<String[]>() {
                @Override
                public int compare(String[] array1, String[] array2) {
                    // get the second element of each array, andtransform it into a Double
                    String d1 = array1[0];
                    String d2 = array2[0];
                    return d1.compareTo(d2);
                }
            });

            return nowe;

        }
    }
}







