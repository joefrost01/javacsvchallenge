package csvchallenge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileProcessorStreams {
    private static final DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String d = ",";
    private static final String n = "\n";
    private static final LocalDate date = LocalDate.of(1972, 10, 3);

    public static void processFile(String inputFile, String outputFile) {
        // For timing only *********************************************************************************************
        List<Long> times = new ArrayList<>();
        IntStream.range(1, 50).forEach(i -> {
            long start = System.currentTimeMillis();
            // *********************************************************************************************************

            try (Stream<String> lines = (Files.newBufferedReader(Paths.get(inputFile)).lines().parallel());
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
            ) {
                writer.write("first_name,last_name,email\n");
                lines.filter(x -> x.contains("New York")).map(x -> x.split("\t")).forEach(x -> processLine(writer, x));
            } catch (Exception e) {
                //
            }

            // For timing only *****************************************************************************************
            long end = System.currentTimeMillis();
            times.add(end - start);
        });
        System.out.println("Max:    " + times.stream().mapToDouble(x -> x).max().orElse(0) / 1000D);
        System.out.println("Min:    " + times.stream().mapToDouble(x -> x).min().orElse(0) / 1000D);
        System.out.println("Average:" + times.stream().mapToDouble(x -> x).average().orElse(0) / 1000D);
        // *************************************************************************************************************
    }

    public static void processLine(Writer writer, String[] fields) {
        try {
            if (fields[8].equals("New York")) {
                LocalDate dob = LocalDate.parse(fields[3], ddMMyyyy);
                if (dob.isBefore(date)) {
                    writer.write(fields[0] + d + fields[1] + d + fields[2] + n);
                }
            }
        } catch (Exception e) {
            //
        }
    }
}
