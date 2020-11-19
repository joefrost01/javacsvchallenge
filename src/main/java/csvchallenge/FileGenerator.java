package csvchallenge;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FileGenerator {

    Fairy fairy = Fairy.create();
    String d = "\t";
    String n = "\n";
    DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    int frequencyOfBadDates = 10;

    public void generateFile(String fileName, int rows) {
        try {
            File file = new File(fileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(getHeaders());
            for (int i = 0; i < rows; i++) {
                writer.write(getPersonAsString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    private String getHeaders() {
        StringBuilder s = new StringBuilder();
        s.append("first_name").append(d)
                .append("last_name").append(d)
                .append("email").append(d)
                .append("dob").append(d)
                .append("telephone").append(d)
                .append("username").append(d)
                .append("address_1").append(d)
                .append("address_2").append(d)
                .append("city").append(d)
                .append("postal_code").append(n);
        return s.toString();
    }

    private String getPersonAsString() {
        StringBuilder s = new StringBuilder();
        Person person = fairy.person();
        s.append(person.getFirstName()).append(d)
                .append(person.getLastName()).append(d)
                .append(person.getEmail()).append(d)
                .append(mangleDate(person.getDateOfBirth())).append(d)
                .append(person.getTelephoneNumber()).append(d)
                .append(person.getUsername()).append(d)
                .append(person.getAddress().getAddressLine1()).append(d)
                .append(person.getAddress().getAddressLine2()).append(d)
                .append(person.getAddress().getCity()).append(d)
                .append(person.getAddress().getPostalCode()).append(n);
        return s.toString();
    }

    private String mangleDate(LocalDate date) {
        if (getRandomDoubleBetweenRange(1, frequencyOfBadDates) == frequencyOfBadDates - 1) {
            return "bad_data";
        } else {
            return ddMMyyyy.format(date);
        }
    }

    private double getRandomDoubleBetweenRange(double min, double max) {
        return Math.round((Math.random() * ((max - min) + 1))) + min;
    }

}
