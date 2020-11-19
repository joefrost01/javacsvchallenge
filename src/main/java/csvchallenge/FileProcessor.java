package csvchallenge;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FileProcessor {
    private static final DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String d = ",";
    private static final String n = "\n";
    private static final LocalDate date = LocalDate.of(1972, 10, 3);

    public void processFile(String inputFile, String outputFile) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            File inFile = new File(inputFile);
            reader = new BufferedReader(new FileReader(inFile));
            File outFile = new File(outputFile);
            writer = new BufferedWriter(new FileWriter(outFile));
            writer.write("first_name,last_name,email\n");
            String line = reader.readLine();
            while (line != null) {
                writer.write(processLine(line));
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        if (null != reader) reader.close();
        if (null != writer) writer.close();
        long end = System.currentTimeMillis();
        System.out.println("Elapsed time: " + (end - start));
    }

    private static String processLine(String line) {
        String[] fields = line.split("\t");
        try {
            LocalDate dob = LocalDate.parse(fields[3], ddMMyyyy);
            if (dob.isBefore(date) && fields[8].toLowerCase().equals("new york")) {
                return fields[0] + d + fields[1] + d + fields[2] + n;
            } else return "";
        } catch (Exception e) {
            return "";
        }
    }
}
