package csvchallenge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileProcessorStreams {
    private static final DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String d = ",";
    private static final String n = "\n";
    private static final LocalDate date = LocalDate.of(1972, 10, 3);

    public Boolean processFile(String inputFile, String outputFile) {
        try (Stream<String> lines = (Files.newBufferedReader(Paths.get(inputFile)).lines());
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            writer.write("first_name,last_name,email\n");
            lines.forEach(line -> processLine(writer, line));
        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return true;
    }

    private static void processLine(BufferedWriter writer, String line) {
        String[] fields = line.split("\t");
        try {
            LocalDate dob = LocalDate.parse(fields[3], ddMMyyyy);
            if (dob.isBefore(date) && fields[8].toLowerCase().equals("new york")) {
                writer.write(fields[0] + d + fields[1] + d + fields[2] + n);
            }
        } catch (Exception e) {
            //
        }
    }
}
